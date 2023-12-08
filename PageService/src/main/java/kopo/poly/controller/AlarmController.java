package kopo.poly.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/alarm")
@Controller
@CrossOrigin(origins = {"http://localhost:13000", "http://localhost:14000"}, allowedHeaders = {"POST, GET"}, allowCredentials = "true")
public class AlarmController {


    private final String HEADER_PREFIX = "Bearer ";


    @GetMapping("alarm")
    public String alarm() {
        return "alarm/alarm";
    }
}
