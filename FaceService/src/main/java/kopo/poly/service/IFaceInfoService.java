package kopo.poly.service;

import kopo.poly.dto.FaceDTO;

import java.util.List;

// Face는 Subjcet에 속하며, Subject는 Group에 속함
public interface IFaceInfoService {

    // Subject 속 Face 전체 조회
    List<FaceDTO> getFaceList(FaceDTO pDTO) throws Exception;

    // Face 생성
    FaceDTO createFace(FaceDTO pDTO) throws Exception;

    // Face 삭제
    void deleteFace(FaceDTO pDTO) throws Exception;

}
