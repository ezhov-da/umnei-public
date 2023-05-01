package ru.ezhov.umnei.mathematics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class ApplicationController {
    @GetMapping()
    String index() {
        return "index.html";
    }
}
