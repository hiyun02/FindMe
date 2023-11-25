package kopo.poly.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/police")
@Controller
public class PoliceController {

    /* 경찰청 실종자 확인 */
    @GetMapping("missList")
    public String policeMissList() {return "police/policeMissList";}
}
