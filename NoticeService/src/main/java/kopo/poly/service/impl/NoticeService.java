package kopo.poly.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import kopo.poly.dto.NoticeDTO;
import kopo.poly.repository.NoticeRepository;
import kopo.poly.repository.entity.NoticeEntity;
import kopo.poly.service.INoticeService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class NoticeService implements INoticeService {

    // RequiredArgsConstructor 어노테이션으로 생성자를 자동 생성함
    // noticeRepository 변수에 이미 메모리에 올라간 NoticeRepository 객체를 넣어줌
    // 예전에는 autowired 어노테이션를 통해 설정했었지만, 이젠 생성자를 통해 객체 주입함
    private final NoticeRepository noticeRepository;


    @Override
    public void insertNoticeInfo(NoticeDTO pDTO) {

        log.info(this.getClass().getName() + ".insertNoticeInfo Start!");

        String title = CmmUtil.nvl(pDTO.title());

        log.info("title : " + title);

        // 공지사항 저장을 위해서는 PK 값은 빌더에 추가하지 않는다.
        // JPA에 자동 증가 설정을 해놨음
        NoticeEntity pEntity = NoticeEntity.builder()
                .title(title).readCnt(0L)
                .regId(userId).regDt(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"))
                .chgId(userId).chgDt(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"))
                .build();

        // 공지사항 저장하기
        noticeRepository.save(pEntity);

        log.info(this.getClass().getName() + ".insertNoticeInfo End!");

    }

    @Override
    public List<NoticeDTO> getNoticeList(String category) {

        log.info(this.getClass().getName() + ".getNoticeList Start!");

        NoticeEntity pEntity = NoticeEntity.builder().writngTrgetDscd(category).build();

        // 실종자 테이블에 카테고리 별 전체조획 시작
        List<NoticeEntity> rList = noticeRepository.findAllByWritngTrgetDscdOrderByNoticeSeqDesc(pEntity);

        // 엔티티의 값들을 DTO에 맞게 넣어주기
        List<NoticeDTO> nList = new ObjectMapper().convertValue(rList,
                new TypeReference<List<NoticeDTO>>() {
                });

        log.info(this.getClass().getName() + ".getNoticeList End!");

        return nList;
    }

    @Transactional
    @Override
    public NoticeDTO getNoticeInfo(NoticeDTO pDTO, boolean type) {

        log.info(this.getClass().getName() + ".getNoticeInfo Start!");

        if (type) {
            // 조회수 증가하기
            int res = noticeRepository.updateReadCnt(pDTO.noticeSeq());

            // 조회수 증가 성공여부 체크
            log.info("res : " + res);
        }

        // 공지사항 상세내역 가져오기
        NoticeEntity rEntity = noticeRepository.findByNoticeSeq(pDTO.noticeSeq());

        // 엔티티의 값들을 DTO에 맞게 넣어주기
        NoticeDTO rDTO = new ObjectMapper().convertValue(rEntity, NoticeDTO.class);

        log.info(this.getClass().getName() + ".getNoticeInfo End!");

        return rDTO;
    }

    @Transactional
    @Override
    public void updateNoticeInfo(NoticeDTO pDTO) {

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
    public void deleteNoticeInfo(NoticeDTO pDTO) {

        log.info(this.getClass().getName() + ".deleteNoticeInfo Start!");

        Long noticeSeq = pDTO.noticeSeq();

        log.info("noticeSeq : " + noticeSeq);

        // 데이터 수정하기
        noticeRepository.deleteById(noticeSeq);


        log.info(this.getClass().getName() + ".deleteNoticeInfo End!");
    }
}