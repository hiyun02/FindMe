package kopo.poly.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import kopo.poly.dto.FaceDTO;
import kopo.poly.service.IBucketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;

// Aws Bucket에 MultipartFile 객체를 이미지로 업로드하는 서비스
@Service
@Slf4j
@RequiredArgsConstructor
public class BucketService implements IBucketService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    @Override
    public FaceDTO uploadFile(FaceDTO faceDTO) {
        log.info(this.getClass().getName()+".uploadFile Start!");

        FaceDTO rDTO;
        String fileName = faceDTO.imageName(); // subject_name을 업로드 파일명으로 사용

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(faceDTO.image().getSize());
        objectMetadata.setContentType(faceDTO.image().getContentType());

        try (InputStream inputStream = faceDTO.image().getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            String imageUrl = amazonS3.getUrl(bucket, fileName).toString();

            //업로드 성공 시 faceDTO에 파일명, URL 경로 저장 // faceService에서는 fileName을 face_name으로 활용
            rDTO = FaceDTO.builder().imageName(fileName).imageUrl(imageUrl).build();
            log.info("Object Storage 업로드 결과 : " + rDTO.toString());

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
        }

        log.info(this.getClass().getName()+".uploadFile End!");
        return rDTO;
    }

    // file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며, 파일 타입과 상관없이 업로드할 수 있게 하기위해, "."의 존재 유무만 판단
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
