package kopo.poly.service;

public interface IApiService {

    // 실종 경고 API 1건만 가져오기
    WarnDTO getWarnApi()throws Exception;

    // 실종 경고 API 전체 DB 저장
    int insertWarnApi() throws Exception;

}
