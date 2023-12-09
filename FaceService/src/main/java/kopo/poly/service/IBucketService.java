package kopo.poly.service;

import kopo.poly.dto.FaceDTO;

// Face는 Subjcet에 속하며, Subject는 Group에 속함
public interface IBucketService {

    FaceDTO uploadFile(FaceDTO faceDTO);

    String getFileExtension(String fileName);

    void deleteFile(String fileName);
}
