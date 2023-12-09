package kopo.poly.service;

import kopo.poly.dto.UserInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "UserService", url = "http://localhost:13000/user")
public interface UserService {

    /* Profile 회원정보 가져오기 */
    @PostMapping(value = "userInfo")
    UserInfoDTO userInfo(@RequestBody UserInfoDTO userInfoDTO);

    /* 회원정보 수정하기*/
    @PostMapping(value = "userUpdate")
    int userUpdate(@RequestBody UserInfoDTO userInfoDTO);

    

}
