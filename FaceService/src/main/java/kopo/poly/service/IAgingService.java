package kopo.poly.service;

import kopo.poly.dto.AgingDTO;

// Face는 Subjcet에 속하며, Subject는 Group에 속함
public interface IAgingService {

    // Face Aging 결과 반환
    AgingDTO getAgedFaceImage(AgingDTO pDTO) throws Exception;

}
