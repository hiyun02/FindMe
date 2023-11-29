package kopo.poly.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/alarm")
public class TestController {

    @GetMapping("test")
    public String test() throws Exception {
        log.info(getClass().getName() + "Test 시작");

        log.info(getClass().getName() + "Test 종료");

        return "라우팅 성공";
    }
}
