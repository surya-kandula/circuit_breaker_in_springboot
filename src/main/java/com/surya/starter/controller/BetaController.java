package com.surya.starter.controller;

import com.surya.starter.service.AlphaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/beta")
@AllArgsConstructor
public class BetaController {

    AlphaService alphaService;

    @GetMapping("/name")
    public String selfName() {
        return "beta";
    }
}
