package kopo.poly.service;

import kopo.poly.dto.FaceAgingDTO;
import kopo.poly.dto.FaceDTO;

import java.util.List;

// Face는 Subjcet에 속하며, Subject는 Group에 속함
public interface IFaceAgingService {

    // Face Aging 결과 반환
    FaceAgingDTO getAgedFaceImage(FaceAgingDTO pDTO) throws Exception;

}
