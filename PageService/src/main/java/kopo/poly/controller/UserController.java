package kopo.poly.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
@CrossOrigin(origins = {"http://localhost:13000", "http://localhost:14000"}, allowedHeaders = {"POST, GET"}, allowCredentials = "true")
public class UserController {

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
    public String profile() {
        return "user/profile";
    }

    @GetMapping("changeUserPwd")
    public String changeUserPwd(){
        return "user/changeUserPwd";

    }
}
