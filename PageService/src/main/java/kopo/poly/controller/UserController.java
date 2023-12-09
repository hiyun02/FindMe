package kopo.poly.controller;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.IUnwovenClassFile;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
@CrossOrigin(origins = {"http://localhost:13000", "http://localhost:14000"}, allowedHeaders = {"POST, GET"}, allowCredentials = "true")
public class UserController {

    private final UserService userService;

    /* 로그인 페이지 */
    @GetMapping("login")
    public String userLogin() {
        return "user/userLogin";
    }

    /* 회원가입 페이지 */
    @GetMapping("userReg")
    public String userReg() {
        return "user/userReg";
    }

    /* 아이디 찾기 페이지 */
    @GetMapping("findUserId")
    public String findUserId() {
        return "user/findUserId";
    }

    /* 비밀번호 찾기(변경) 페이지*/
    @GetMapping("findUserPwd")
    public String finUserPwd() {
        return "user/findUserPwd";
    }

    /* 회원정보 수정 페이지*/
    @GetMapping("profile")
    public String profile(Model model) {

        UserInfoDTO userInfoDTO = userService.userInfo();

        model.addAttribute("userInfoDTO", userInfoDTO);

        return "user/profile";
    }

    /* 회원정보 수정 로직*/
    @PostMapping("updateProfile")
    public String updateProfile(UserInfoDTO userInfoDTO) {

        int res = userService.userUpdate(userInfoDTO);

        return "";
    }
    /* 비밀번호 변경 페이지*/
    @GetMapping("changeUserPwd")
    public String changeUserPwd() {
        return "user/changeUserPwd";

    }

}
