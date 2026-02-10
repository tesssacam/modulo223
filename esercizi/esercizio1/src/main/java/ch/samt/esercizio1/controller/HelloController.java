package ch.samt.esercizio1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {

    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("message", "Hello");
        return "hello";
    }

    @GetMapping("/{name}")
    public String helloName(@PathVariable String name, Model model) {
        model.addAttribute("message", "Hello " + name);
        return "hello";
    }
}
