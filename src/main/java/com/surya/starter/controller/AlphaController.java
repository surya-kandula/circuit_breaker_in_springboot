package com.surya.starter.controller;

import com.surya.starter.service.AlphaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alpha")
@AllArgsConstructor
public class AlphaController {

    AlphaService alphaService;

    @GetMapping("/dependencies")
    public String dependentClients() {
        return alphaService.getNameOfDependentService();
    }
}
