package com.example.pricewise.controller;

import com.example.pricewise.model.Listing;
import com.example.pricewise.service.ComparisonService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    private final ComparisonService service;

    public ProductController(ComparisonService service) {
        this.service = service;
    }

    @GetMapping("/compare")
    public List<Listing> compare(@RequestParam("q") String query) {
        return service.compare(query);
    }

    @GetMapping("/health")
    public String health() {
        return "PriceWise is running";
    }
}
