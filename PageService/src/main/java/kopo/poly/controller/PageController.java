package kopo.poly.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class PageController {

    /* 로그인 페이지 */
    @GetMapping("/user/login")
    public String userLogin() {
        return "/user/userLogin";
    }

    /* 회원가입 페이지 */
    @GetMapping("/user/userReg")
    public String userReg() {
        return "/user/userReg";
    }

    /* 아이디 찾기 페이지 */
    @GetMapping("/user/findUserId")
    public String findUserId() {
        return "/user/findUserId";
    }

    /* 비밀번호 찾기(변경) 페이지*/
    @GetMapping("/user/findUserPwd")
    public String finUserPwd() {
        return "/user/findUserPwd";
    }
}
