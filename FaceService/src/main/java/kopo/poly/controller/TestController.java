package kopo.poly.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @GetMapping("/facecan/test")
    public String test1() throws Exception {
        log.info(getClass().getName() + "Test 시작");

        String result = "라우팅 성공";

        log.info(getClass().getName() + "Test 종료");

        return result;
    }

    @GetMapping("/SAM/test")
    public String test2() throws Exception {
        log.info(getClass().getName() + "Test 시작");

        String result = "라우팅 성공";

        log.info(getClass().getName() + "Test 종료");

        return result;
    }
}
