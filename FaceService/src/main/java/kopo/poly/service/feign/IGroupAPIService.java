package kopo.poly.service.feign;

import kopo.poly.dto.FaceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "GroupAPI", url = "https://apis.openapi.sk.com/nugufacecan/v1/group")
public interface IGroupAPIService {

    /**
     * @return 생성된 Group 리스트
     */
      @GetMapping (value = "/",
              consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
      )
      List<FaceDTO> getGroupList();

    /**
     * @param group_name  생성할 그룹 이름
     * @return group 생성
     */
      @PostMapping(value = "/",
              consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
      )
      FaceDTO createGroup(
                @RequestHeader("group-name")  String group_name
    );

    /**
     * @pathParam group_id  삭제할 그룹 아이디
     * Group 삭제할 Delete 요청
     */
      @DeleteMapping (value = "/{group_id}",
               consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
      )
        void deleteGroup(
                @PathVariable("group_id") String group_id
      );

}
