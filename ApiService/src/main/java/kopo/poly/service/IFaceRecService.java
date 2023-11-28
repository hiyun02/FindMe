package kopo.poly.service;

import kopo.poly.dto.FaceDTO;

import java.util.List;

// Group 내의 Subject들을 대상으로 유사도를 분석
public interface IFaceRecService {

    // 분석 결과 리스트 반환
    List<FaceDTO> getRecognizedList(FaceDTO pDTO) throws Exception;

}
