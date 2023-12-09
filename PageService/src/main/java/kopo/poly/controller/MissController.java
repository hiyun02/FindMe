package kopo.poly.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/miss")
@Controller
@CrossOrigin(origins = {"http://localhost:13000", "http://localhost:14000"}, allowedHeaders = {"POST, GET"}, allowCredentials = "true")
/* 국내 실종자*/
public class MissController {

    /* 실종자 리스트*/
    @GetMapping("missList")
    public String missList() {return "miss/missList";}

    /*실종자 정보 수정하기*/
    @GetMapping("missChg")
    public String missChg() {return "miss/missChg";}
    
    /*실종자 정보 등록하기 사진 하나만*/
    @GetMapping("missReg")
    public String missReg() {return "miss/missReg";}

    /* 실종자 정보 등록하기 사진 두개 */
    @GetMapping("missReg2")
    public String missReg2() {return "miss/missReg2";}

    @GetMapping("missDetail")
    public String missDetail(){return "miss/missDetail";}
}
