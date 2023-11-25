package kopo.poly.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class PageController {

    /*메인 페이지*/
    @GetMapping("/exception/main")
    public String main() {return "main";}


}
