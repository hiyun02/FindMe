package kopo.poly.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kopo.poly.dto.PoliceDTO;
import kopo.poly.repository.PoliceRepository;
import kopo.poly.repository.entity.PoliceEntity;
import kopo.poly.service.IPoliceService;
import kopo.poly.service.feign.IPoliceApiService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class PoliceService implements IPoliceService {

    @Value("${policeApi.esntlId}")
    private String esntlId;
    @Value("${policeApi.authKey}")
    private String authKey;
    private final IPoliceApiService policeApiService;
    private final PoliceRepository policeRepository;

    @Override
    public List<PoliceDTO> getPoliceInfoList(String order) throws Exception {
        log.info(this.getClass().getName() + ".getPoliceInfoList Start!");
        List<PoliceEntity> entityList;

        if (order.equals("readCnt")) {
            log.info(this.getClass().getName()+".조회수 순 조회 시작");
            entityList = Optional.ofNullable(
                    policeRepository.findAllByOrderByReadCntDesc()).orElseGet(() -> new ArrayList<PoliceEntity>());
        } else {
            log.info(this.getClass().getName()+".최신등록 순 조회 시작");
            entityList = Optional.ofNullable(
                    policeRepository.findAllByOrderByPoliceSeqDesc()).orElseGet(() -> new ArrayList<PoliceEntity>());
        }
        List<PoliceDTO> rList = new ObjectMapper().convertValue(entityList, new TypeReference<List<PoliceDTO>>(){});

        log.info("조회된 경찰청 경보 데이터 개수 : {} ", rList.size());
        log.info(this.getClass().getName() + ".getPoliceInfoList End!");
        return rList;
    }

    @Override
    public PoliceDTO getPoliceInfo(PoliceDTO policeDTO) throws Exception {
        log.info(this.getClass().getName() + ".getPoliceInfoList Start!");

        Optional<PoliceEntity> rEntity = policeRepository.findById(policeDTO.policeSeq());
        PoliceDTO rDTO = new ObjectMapper().convertValue(rEntity, new TypeReference<PoliceDTO>(){});

        log.info("조회된 경찰청 API 상세 정보 : {} ", rDTO.toString());
        log.info(this.getClass().getName() + ".getPoliceInfoList End!");
        return rDTO;
    }

    @Override
    public int policeDelete(PoliceDTO policeDTO) throws Exception {
        log.info(this.getClass().getName() + ".policeDelete Start!");
        policeRepository.deleteById(policeDTO.policeSeq());
        log.info(this.getClass().getName() + ".policeDelete End!");
        return 1;
    }

    @Override
    public int insertPoliceApi() throws Exception {
        log.info(this.getClass().getName() + ".실종 경보 API DB 저장 시작");

        //결과 값 선언
        int res = 0;

        // 전역 변수 설정
        Long total = 0L;

        int i = 1;
        boolean repeating = true;

        while (repeating) {

            String result = policeApiService.getPoliceAPI(esntlId, authKey, 100, i);

            JSONParser parser = new JSONParser();
            JSONObject jsonObj = (JSONObject) parser.parse(result);

            Long totalCount = (Long) jsonObj.get("totalCount");
            JSONArray list = (JSONArray) jsonObj.get("list");

            for (Object contentElementObject : list) {

                JSONObject contentElement = (JSONObject) contentElementObject;

                PoliceEntity rEntity = PoliceEntity.builder()
                        .occrde(CmmUtil.nvl((String) contentElement.get("occrde"))) // 발생 일시
                        .alldressingDscd(CmmUtil.nvl((String) contentElement.get("alldressingDscd"))) // 착의 사항
                        .ageNow(CmmUtil.nvl((String) contentElement.get("ageNow"))) //현재 나이
                        .age(CmmUtil.nvl(((Long) contentElement.get("age")).toString())) // 당시 나이
                        .writngTrgetDscd(CmmUtil.nvl((String) contentElement.get("writngTrgetDscd"))) // 대상 구분
                        .sexdstnDscd(CmmUtil.nvl((String) contentElement.get("sexdstnDscd"))) // 성별구분
                        .occrAdres(CmmUtil.nvl((String) contentElement.get("occrAdres"))) // 발생장소
                        .nm(CmmUtil.nvl((String) contentElement.get("nm"))) // 성명
                        .height(CmmUtil.nvl(String.valueOf((Long) contentElement.get("height"))))// 키
                        .bdwgh(CmmUtil.nvl(String.valueOf((Long) contentElement.get("bdwgh")))) // 몸무게
                        .frmDscd(CmmUtil.nvl((String) contentElement.get("frmDscd"))) // 체격
                        .faceshpeDscd(CmmUtil.nvl((String) contentElement.get("faceshpeDscd"))) // 얼굴형
                        .hairshpeDscd(CmmUtil.nvl((String) contentElement.get("hairshpeDscd"))) //  두발 형태
                        .haircolrDscd(CmmUtil.nvl((String) contentElement.get("haircolrDscd"))) //두발 형태
                        .msspsnIdntfccd(CmmUtil.nvl(((Long) contentElement.get("msspsnIdntfccd")).toString()))//사진
                        .readCnt(0L) // 조회수
                        .build();

                policeRepository.save(rEntity);
            }

            if (i++ == 1) {
                total = totalCount;
            }

            // 총 갯수가 100개 넘으면 나머지 갯수 가져올 수 있도록 남은 갯수 써주기
            // 100개 안 넘으면 false while문 종료
            if (total > 100) {
                total = total - 100;
            } else {
                repeating = false;
            }
        }

        res = 1;

        log.info(this.getClass().getName() + ".실종 경보 API DB 저장 종료");
        return res;
    }

    @Override
    public PoliceDTO getPoliceAPIOne() throws Exception {

        log.info("실종 경보 API 가져오기 시작 ");
        String result = policeApiService.getPoliceAPI(esntlId, authKey, 1, 1);

        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(result);

        log.info("json으로 만든 객체 : " + jsonObj);

        JSONArray list = (JSONArray) jsonObj.get("list");
        PoliceDTO rDTO = PoliceDTO.builder().build();

        for (Object contentElementObject : list) {

            JSONObject contentElement = (JSONObject) contentElementObject;

            rDTO = PoliceDTO.builder()
                    .occrde(CmmUtil.nvl((String) contentElement.get("occrde"))) // 발생 일시
                    .alldressingDscd(CmmUtil.nvl((String) contentElement.get("alldressingDscd"))) // 착의 사항
                    .ageNow(CmmUtil.nvl((String) contentElement.get("ageNow"))) //현재 나이
                    .age(CmmUtil.nvl(((Long) contentElement.get("age")).toString())) // 당시 나이
                    .writngTrgetDscd(CmmUtil.nvl((String) contentElement.get("writngTrgetDscd"))) // 대상 구분
                    .sexdstnDscd(CmmUtil.nvl((String) contentElement.get("sexdstnDscd"))) // 성별구분
                    .occrAdres(CmmUtil.nvl((String) contentElement.get("occrAdres"))) // 발생장소
                    .nm(CmmUtil.nvl((String) contentElement.get("nm"))) // 성명
                    .height(CmmUtil.nvl(((Long) contentElement.get("height")).toString()))// 키
                    .bdwgh(CmmUtil.nvl(((Long) contentElement.get("bdwgh")).toString())) // 몸무게
                    .frmDscd(CmmUtil.nvl((String) contentElement.get("frmDscd"))) // 체격
                    .faceshpeDscd(CmmUtil.nvl((String) contentElement.get("faceshpeDscd"))) // 얼굴형
                    .hairshpeDscd(CmmUtil.nvl((String) contentElement.get("hairshpeDscd"))) //  두발 형태
                    .haircolrDscd(CmmUtil.nvl((String) contentElement.get("haircolrDscd"))) //두발 형태
                    .msspsnIdntfccd(CmmUtil.nvl(((Long) contentElement.get("msspsnIdntfccd")).toString()))//사진
                    .build();
        }

        log.info("실종 경보 API 가져오기 종료 ");
        return rDTO;
    }

//    private final FindRepository findRepository;
//    @Override
//    public int insertFind() throws Exception {
//        log.info(getClass().getName() + "실종 검색 DB에 저장 시작 ");
//
//        //결과 값 선언
//        int res = 0;
//
//        // 전역 변수 설정
//        Long total = Long.valueOf(0);
//        int i = 1;
//        boolean repeating  = true;
//
//        while (repeating) {
//
//            String result = policeApiService.findAPI(esntlId, authKey, 100, i);
//
//
//            JSONParser parser = new JSONParser();
//            JSONObject jsonObj = (JSONObject) parser.parse(result);
//
//            Long totalCount = (Long) jsonObj.get("totalCount");
//            JSONArray list = (JSONArray) jsonObj.get("list");
//
//
//
//            for (Object contentElementObject : list) {
//
//
//                JSONObject contentElement = (JSONObject) contentElementObject;
//
//                FindApiEntity rEntity = FindApiEntity.builder()
//                        .occrde(CmmUtil.nvl((String) contentElement.get("occrde"))) // 발생 일시
//                        .alldressingDscd(CmmUtil.nvl((String) contentElement.get("alldressingDscd"))) // 착의 사항
//                        .ageNow(CmmUtil.nvl((String) contentElement.get("ageNow"))) //현재 나이
//                        .age(CmmUtil.nvl(((Long)contentElement.get("age")).toString())) // 당시 나이
//                        .writngTrgetDscd(CmmUtil.nvl((String) contentElement.get("writngTrgetDscd"))) // 대상 구분
//                        .sexdstnDscd(CmmUtil.nvl((String) contentElement.get("sexdstnDscd"))) // 성별구분
//                        .occrAdres(CmmUtil.nvl((String) contentElement.get("occrAdres"))) // 발생장소
//                        .nm(CmmUtil.nvl((String) contentElement.get("nm"))) // 성명
//                        .msspsnIdntfccd(CmmUtil.nvl(((Long)contentElement.get("msspsnIdntfccd")).toString()))//사진
//                        .build();
//
//
//                findRepository.save(rEntity);
//
//
//            }
//
//            if (i == 1) {
//                total = totalCount;
//            }
//
//            i++;
//
//
//            // 총 갯수가 100개 넘으면 나머지 갯수 가져올 수 있도록 남은 갯수 써주기
//            // 100개 안 넘으면 false while문 종료
//            if (total > 100) {
//                total = total - 100;
//            } else {
//                repeating = false;
//            }
//
//        }
//
//        res = 1;
//
//
//        log.info(getClass().getName() + "실종 검색 DB에 저장 종료 ");
//        return res;
//    }
}