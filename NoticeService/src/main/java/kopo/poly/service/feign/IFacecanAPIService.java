package kopo.poly.service.feign;

import kopo.poly.dto.FaceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "FacecanAPIService", url = "http://localhost:17000/face/nugu/")
public interface IFacecanAPIService {

    /**
     * FaceService의 Subject Create API 호출
     *
     * @param group_id     : 생성할 Subject의 소속 Group
     * @param subject_name : 생성할 Subject
     * @return 생성 결과 : group_id, group_name, subject_id, subject_name, transaction_id
     */
    @PostMapping(value = "/subject/create",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    FaceDTO createSubject(
            @RequestParam("group_id") String group_id,
            @RequestParam("subject_name") String subject_name
    );

    /**
     * FaceService의 Subject Delete API 호출
     *
     * @param group_id   : 삭제할 Subject의 소속 Group id
     * @param subject_id : 삭제할 Subject id
     */
    @PostMapping(value = "/subject/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteSubject(
            @RequestParam("group_id") String group_id,
            @RequestParam("subject_id") String subject_id
    );

    /**
     * FaceService의 Face Create API 호출
     *
     * @param group_id     : 생성할 Face의 소속 Group
     * @param subject_id   : 생성할 Face의 소속 Subject
     * @param face_name    : 생성할 Face 정보의 이름 지정
     * @param image: 생성할 Face image
     * @return 생성 결과 : group_id, group_name, subject_id, subject_name, transaction_id
     */
    @PostMapping(value = "/faceInfo/create",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    FaceDTO createFace(
            @RequestPart("group_id") String group_id,
            @RequestPart("subject_id") String subject_id,
            @RequestPart("face_name") String face_name,
            @RequestPart("image") MultipartFile image
            );

    /**
     * FaceService의 Face Delete API 호출
     *
     * @param group_id     : 삭제할 Face의 소속 Group
     * @param subject_id   : 삭제할 Face의 소속 Subject
     * @param face_id    : 삭제할 Face
     */
    @PostMapping(value = "/faceInfo/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteFace(
            @RequestParam("group_id") String group_id,
            @RequestParam("subject_id") String subject_id,
            @RequestParam("face_id") String face_id
    );

    /**
     * FaceService의 Face Recognize API 호출
     *
     * @param group_id : 유사도를 비교할 대상 Group
     * @param image    : 비교 대상 이미지
     */
    @PostMapping(value = "/faceRec",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    FaceDTO recognizeFace(
            @RequestPart("group_id") String group_id,
            @RequestPart("image") MultipartFile image
    );

}
