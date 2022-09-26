package com.kakaopay.membership.global.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
