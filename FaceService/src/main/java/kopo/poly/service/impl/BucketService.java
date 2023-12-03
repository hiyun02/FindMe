package kopo.poly.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import kopo.poly.dto.FaceDTO;
import kopo.poly.service.IBucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

// Aws Bucket에 MultipartFile 객체를 이미지로 업로드하는 서비스
@Service
@RequiredArgsConstructor
public class BucketService implements IBucketService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    @Override
    public List<FaceDTO> uploadFile(List<FaceDTO> pList) {

        List<FaceDTO> rList = new ArrayList<>();

        // forEach 구문을 통해 multipartFiles 리스트로 넘어온 파일들을 순차적으로 fileNameList 에 추가
        for (int i = 0; i < pList.size(); i++) {
            FaceDTO faceDTO = pList.get(i);
            String fileName = faceDTO.imageName();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(faceDTO.image().getSize());
            objectMetadata.setContentType(faceDTO.image().getContentType());

            try (InputStream inputStream = faceDTO.image().getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));

                String imageUrl = amazonS3.getUrl(bucket, fileName).toString();
                //업로드 성공 시 faceDTO에 파일명, URL 경로 저장 // faceService에서는 fileName을 face_name으로 활용
                FaceDTO pDTO = FaceDTO.builder().imageName(fileName).imageUrl(imageUrl).build();
                //faceDTO를 결과 List 객체에 추가
                rList.add(pDTO);

            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
            }

        }

        return rList;
    }

    // file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며, 파일 타입과 상관없이 업로드할 수 있게 하기위해, "."의 존재 유무만 판단하였습니다.
    public String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일" + fileName + ") 입니다.");
        }
    }


    public void deleteFile(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
        System.out.println(bucket);
    }
}
