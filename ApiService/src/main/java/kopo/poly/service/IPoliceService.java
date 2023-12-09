package kopo.poly.service;

import kopo.poly.dto.PoliceDTO;

import java.util.List;


public interface IPoliceService {

    // 경찰청 API 전체 DB 저장
    int insertPoliceApi() throws Exception;

    // 경찰청 API 저장 정보 전체 조회
    List<PoliceDTO> getPoliceInfoList(String order) throws Exception;

    // 경찰청 API 저장 정보 상세 조회
    PoliceDTO getPoliceInfo(PoliceDTO policeDTO) throws Exception;

    // 경찰청 API 저장 정보 삭제
    int policeDelete(PoliceDTO policeDTO) throws Exception;


    // 실종 경고 API 1건만 가져오기 : 테스트용
    PoliceDTO getPoliceAPIOne()throws Exception;

    // 실종 검색 API 전체 DB 저장
    // int insertFind()throws Exception;
}
