package ru.dorofeev22.caregiving.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class MainController {

    @GetMapping
    public String index() {
        return "Hello word!";
    }

}
