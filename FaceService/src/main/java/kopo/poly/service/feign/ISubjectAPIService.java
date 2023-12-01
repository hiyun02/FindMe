package kopo.poly.service.feign;

import kopo.poly.dto.FaceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "SubjectAPI", url = "https://apis.openapi.sk.com/nugufacecan/v1/subject")
public interface ISubjectAPIService {

    /**
     * @param group_id : 조회할 Subject의 소속 Group
     * @return Subject 리스트 조회
     */
    @GetMapping (value = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<FaceDTO> getSubjectList(
            @RequestHeader("group-id") String group_id
    );

    /**
     * @param group_id : 조회할 Subject의 소속 Group
     * @param subject_name : 조회할 Subject의 이름
     * @return Subject 상세 조회
     */
    @GetMapping (value = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<FaceDTO> getSubject(
            @RequestHeader("group-id") String group_id,
            @RequestHeader("subject-name") String subject_name
    );


    /**
     * @param group_id  생성할 그룹 이름
     * @return group 생성
     */
      @PostMapping(value = "/",
              consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
      )
      FaceDTO createSubject(
                @RequestHeader("group-id")  String group_id,
                @RequestHeader("subject-name")  String subject_name
    );

    /**
     * @param group_id  삭제할 subject의 소속 group
     * @pathparam subject_id  삭제할 subject 아이디
     * Group 삭제할 Delete 요청
     */
      @DeleteMapping (value = "/{subject_id}",
               consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
      )
        void deleteSubject(
                @RequestHeader("group-id") String group_id,
                @PathVariable("subject_id") String subject_id
      );

}
