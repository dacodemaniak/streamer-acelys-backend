package fr.aelion.streamer.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class Landing {
    @GetMapping
    public String hello() {
        return "Hello SpringBoot";
    }
}
