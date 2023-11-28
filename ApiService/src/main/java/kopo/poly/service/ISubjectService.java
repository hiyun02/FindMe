package kopo.poly.service;

import kopo.poly.dto.FaceDTO;

import java.util.List;

// Subject는 '사람'에 해당하는 개념임. 여러 얼굴이미지(Face)를 가질 수 있고
// 집단(Group)에 소속되어 있음
public interface ISubjectService {

    // Subject 전체 조회
    List<FaceDTO> getSubjectList(FaceDTO pDTO) throws Exception;

    // Subject 상세 조회
    FaceDTO getSubject(FaceDTO pDTO) throws Exception;

    // Subject 생성
    FaceDTO createSubject(FaceDTO pDTO) throws Exception;

    // Subject 삭제
    void deleteSubject(FaceDTO pDTO) throws Exception;

}
