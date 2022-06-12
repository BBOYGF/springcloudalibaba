package com.southwind.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author guofan
 * @Create 2022/6/12
 */
@RestController
public class ProviderController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/index")
    public String index() {
        return port;
    }
}
