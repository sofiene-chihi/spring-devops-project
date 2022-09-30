package com.example.devopsproject.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner")
@CrossOrigin(origins = "*")

public class OwnerController {

    @GetMapping("/all")
    public String getPets()
    {
        return "these are your pets";
    }
}
