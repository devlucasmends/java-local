package com.example.controller;

import com.example.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebClientController {

    @Autowired
    private WebClientService service;

    @GetMapping("/teste")
    public String call() {
        return service.fetch();
    }
}
