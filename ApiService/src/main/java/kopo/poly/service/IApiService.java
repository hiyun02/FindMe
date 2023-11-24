package kopo.poly.service;

import kopo.poly.dto.WarnDTO;

import java.util.List;

public interface IApiService {

    // 실종 경고 API 1건만 가져오기
    WarnDTO getWarnApi()throws Exception;

    // 실종 경고 API 전체 DB 저장
    List<WarnDTO> insetWarnApi() throws Exception;
}
