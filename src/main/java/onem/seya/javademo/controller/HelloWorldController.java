package onem.seya.javademo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(HelloWorldController.PATH)
public class HelloWorldController {
    public static final String PATH = "/api";

    @GetMapping(value = "/welcome")
    public String printHelloWord() {
        String welcomeMessage = "Hello, this is BTP SAP JAVA Simple Demo";
        return welcomeMessage;
    }
}
