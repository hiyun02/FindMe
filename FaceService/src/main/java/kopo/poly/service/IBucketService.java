package kopo.poly.service;

import kopo.poly.dto.FaceDTO;

import java.util.List;

// Face는 Subjcet에 속하며, Subject는 Group에 속함
public interface IBucketService {

    List<FaceDTO> uploadFile(List<FaceDTO> faceDTOList);

    String getFileExtension(String fileName);

    void deleteFile(String fileName);
}
