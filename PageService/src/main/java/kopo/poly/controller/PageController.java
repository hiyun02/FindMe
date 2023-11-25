package kopo.poly.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class PageController {

    /*----------------------------회원 페이지 시작-----------------------------------*/
    /* 로그인 페이지 */
    @GetMapping("/user/login")
    public String userLogin() {
        return "user/userLogin";
    }

    /* 회원가입 페이지 */
    @GetMapping("/user/userReg")
    public String userReg() {
        return "user/userReg";
    }

    /* 아이디 찾기 페이지 */
    @GetMapping("/user/findUserId")
    public String findUserId() {
        return "user/findUserId";
    }

    /* 비밀번호 찾기(변경) 페이지*/
    @GetMapping("/user/findUserPwd")
    public String finUserPwd() {
        return "user/findUserPwd";
    }

    /*----------------------------회원 페이지 종료-----------------------------------*/
    /*메인 페이지*/
    @GetMapping("/exception/main")
    public String main() {return "main";}

    /*----------------------------실종자 페이지 시작-----------------------------------*/
    /*실종자 정보 등록하기*/
    @GetMapping("/miss/missReg")
    public String missReg() {return "miss/missReg";}

    /* 실종자 리스트*/
    @GetMapping("/miss/missList")
    public String missList() {return "miss/missList";}

    /*실종자 정보 수정하기*/
    @GetMapping("/miss/missChg")
    public String missChg() {return "miss/missChg";}

    /* 해외 실종자 확인 */
    @GetMapping("/overmiss/overMissList")
    public String overMissList() {return "overmiss/overMissList";}

    /* 경찰청 실종자 확인 */
    @GetMapping("/police/policeMissList")
    public String policeMissList() {return "police/policeMissList";}
    /*----------------------------실종자 페이지 종료-----------------------------------*/


}
