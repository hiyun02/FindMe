package kopo.poly.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import kopo.poly.dto.FaceDTO;
import kopo.poly.dto.NoticeDTO;
import kopo.poly.repository.NoticeRepository;
import kopo.poly.repository.entity.NoticeEntity;
import kopo.poly.service.INoticeService;
import kopo.poly.service.feign.IBucketApiService;
import kopo.poly.service.feign.IFacecanAPIService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class NoticeService implements INoticeService {
    private final NoticeRepository noticeRepository;
    private final IBucketApiService bucketApiService;
    private final IFacecanAPIService facecanAPIService;

    //해외입양자 그룹의 group_id
    private final String groupAdopters = "13FSA6TU11";

    //국내실종자 그룹의 group_id
    private final String groupMissing = "YNLYTWV533";

    @Override
    public void insertNoticeInfo(NoticeDTO pDTO, String userId) throws Exception {

        log.info(this.getClass().getName() + ".insertNoticeInfo Start!");

        // 업로드된 이미지 개수
        int uploadedImgLength = pDTO.multipartFiles().size();

        // 1. 실종자 얼굴 이미지 업로드
        String subjectName = userId + "_" + pDTO.nm(); // "등록자Id + 실종자명"으로 지정
        List<FaceDTO> storageResultList = new ArrayList<>(); // 이미지 url 담을 List 선언
        for (int i = 0; i < uploadedImgLength; i++) {
            MultipartFile mf = pDTO.multipartFiles().get(i);
            String fileName = "FindMe/" + subjectName + i + ".jpg"; // 복수의 이미지를 저장하므로 번호를 붙혀 구분
            log.info("업로드할 이미지 파일 명 : {}", fileName);
            FaceDTO faceDTO = bucketApiService.uploadFile(mf, fileName); // 업로드할 파일과 파일명을 버킷으로 전달
            storageResultList.add(faceDTO);
        }
        String faceImgUrl1 = storageResultList.get(0).imageUrl();
        String faceImgUrl2 = storageResultList.get(1).imageUrl();
        log.info("업로드된 이미지 파일 경로1 : {}", faceImgUrl1);
        log.info("업로드된 이미지 파일 경로2 : {}", faceImgUrl2);

        // 2. 해당 실종자의 얼굴 분석 정보를 저장할 Subject 생성
        // 실종 대분류에 따라 적절한 group_id를 할당함
        String group_id = (pDTO.writngTrgetDscd().equals("국내실종")) ? groupMissing : groupAdopters;
        // subject 생성 : subject_id, subject_name, group_name 반환
        FaceDTO subjectDTO = facecanAPIService.createSubject(group_id, subjectName);
        log.info("실종자 이미지 분석 정보를 저장할 Subject 생성 결과 : {}", subjectDTO.toString());

        // 3. subject id를 가지고 face 생성 (이미지의 개수만큼 생성)
        String subject_id = subjectDTO.subject_id();
        for (int i = 0; i < uploadedImgLength; i++) {
            String face_name = subjectName + i;
            facecanAPIService.createFace(group_id, subject_id, face_name, pDTO.multipartFiles().get(i));
            log.info("실종자 이미지 정보 저장 성공 : {}", i);
        }

        // 4. 사용자가 입력한 실종자 정보에 업로드이미지 url, subject_id, subject_name 등 추가하여 Insert
        NoticeEntity pEntity = NoticeEntity.builder()
                .writngTrgetDscd(pDTO.writngTrgetDscd())
                .detailTrgetDscd(pDTO.detailTrgetDscd())
                .occrde(pDTO.occrde()).nm(pDTO.nm())
                .sexdstnDscd(pDTO.sexdstnDscd()).faceshpeDscd(pDTO.faceshpeDscd())
                .birthDt(pDTO.birthDt()).age(pDTO.age()).height(pDTO.height())
                .frmDscd(pDTO.frmDscd()).hairshpeDscd(pDTO.hairshpeDscd()).haircolrDscd(pDTO.haircolrDscd())
                .alldressingDscd(pDTO.alldressingDscd()).occrAdres(pDTO.occrAdres())
                // Storage에 등록한 이미지 URL 정보
                .faceImgUrl1(faceImgUrl1).faceImgUrl2(faceImgUrl2)
                // NUGU Facecan API에 등록한 Subject 정보
                .subjectId(subject_id).subjectName(subjectName)
                .title(pDTO.title()).readCnt(0L)
                .regId(userId).regDt(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"))
                .chgId(userId).chgDt(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"))
                .build();

        // 공지사항 저장하기
        noticeRepository.save(pEntity);

        log.info(this.getClass().getName() + ".insertNoticeInfo End!");

    }

    @Override
    public List<NoticeDTO> getNoticeList(String category) throws Exception {

        log.info(this.getClass().getName() + ".getNoticeList Start!");

        NoticeEntity pEntity = NoticeEntity.builder().writngTrgetDscd(category).build();

        List<NoticeEntity> rList = noticeRepository.findAllByWritngTrgetDscdOrderByNoticeSeqDesc(pEntity);

        List<NoticeDTO> nList = new ObjectMapper().convertValue(rList,
                new TypeReference<List<NoticeDTO>>() {
                });

        log.info(this.getClass().getName() + ".getNoticeList End!");

        return nList;
    }

    @Transactional
    @Override
    public NoticeDTO getNoticeInfo(NoticeDTO pDTO, boolean type) throws Exception {

        log.info(this.getClass().getName() + ".getNoticeInfo Start!");

        if (type) {
            int res = noticeRepository.updateReadCnt(pDTO.noticeSeq());
            log.info("조회수 증가 결과 : {}", res);
        }

        NoticeEntity rEntity = noticeRepository.findByNoticeSeq(pDTO.noticeSeq());
        NoticeDTO rDTO = new ObjectMapper().convertValue(rEntity, NoticeDTO.class);

        log.info(this.getClass().getName() + ".getNoticeInfo End!");

        return rDTO;
    }

    @Transactional
    @Override
    public void updateNoticeInfo(NoticeDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".updateNoticeInfo Start!");

        Long noticeSeq = pDTO.noticeSeq();
        String title = CmmUtil.nvl(pDTO.title());

        log.info("noticeSeq : " + noticeSeq);
        log.info("title : " + title);

        // 현재 공지사항 조회수 가져오기
        NoticeEntity rEntity = noticeRepository.findByNoticeSeq(noticeSeq);

        // 수정할 값들을 빌더를 통해 엔티티에 저장하기
        NoticeEntity pEntity = NoticeEntity.builder()
                .noticeSeq(noticeSeq).title(title)
                .readCnt(rEntity.getReadCnt())
                .build();

        // 데이터 수정하기
        noticeRepository.save(pEntity);

        log.info(this.getClass().getName() + ".updateNoticeInfo End!");

    }

    @Override
    public void deleteNoticeInfo(NoticeDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".deleteNoticeInfo Start!");

        Long noticeSeq = pDTO.noticeSeq();
        log.info("삭제할 실종자 정보의 noticeSeq : {}", noticeSeq);

        String group_id = (pDTO.writngTrgetDscd().equals("국내실종")) ? groupMissing : groupAdopters;
        log.info("삭제할 실종자 정보의 group_id : {}", group_id);

        // 실종자 Subject_id 검색
        NoticeEntity pEntity = noticeRepository.findByNoticeSeq(noticeSeq);
        String subject_id = pEntity.getSubjectId();
        log.info("삭제할 실종자 정보의 subject_id : {}", subject_id);

        // 실종자 얼굴 정보 Subject 삭제하기
        facecanAPIService.deleteSubject(group_id,subject_id);

        String subjectName = pEntity.getSubjectName();

        // storage의 이미지 삭제하기
        for (int i = 0; i < 2; i++) {
            String fileName = "FindMe/" + subjectName + i + ".jpg"; // 복수의 이미지를 저장하므로 번호를 붙힘
            log.info("업로드할 이미지 파일 명 : {}", fileName);
            String result = bucketApiService.deleteFile(fileName); // 업로드할 파일과 파일명을 버킷으로 전달
            log.info("storage에 저장된 얼굴 이미지 삭제 결과 : {}", result);
        }

        // 실종자 데이터 삭제하기
        noticeRepository.deleteById(noticeSeq);

        log.info(this.getClass().getName() + ".deleteNoticeInfo End!");
    }
}