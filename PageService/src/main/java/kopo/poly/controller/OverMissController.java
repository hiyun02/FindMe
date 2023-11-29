package kopo.poly.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/overmiss")
@CrossOrigin(origins = {"http://localhost:13000", "http://localhost:14000"}, allowedHeaders = {"POST, GET"}, allowCredentials = "true")
/* 해외 실종자 확인 */
public class OverMissController {

    @GetMapping("missList")
    public String overMissList() {

        log.info("실행");
        return "overmiss/overMissList";}
}
