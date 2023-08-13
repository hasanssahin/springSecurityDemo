package com.hasansahin.springsecuritydemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @GetMapping
    public ResponseEntity<String> seller() {
        return ResponseEntity.ok("Seller page");
    }
}
