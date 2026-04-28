package ch.samt.esercizio1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {

    // Questo metodo risponde all'URL: http://localhost:8088/
    @GetMapping("/") //quando si apre la pagina (8088) si va in hello.html
    public String hello(Model model) {
        model.addAttribute("message", "Hello");     // Passiamo alla pagina una variabile chiamata "message" e una "titolo"
        return "hello"; //nome html da aprire
    }

    @GetMapping("/{name}") //quando si va dentro es.../pippo si vede un'altra pagina + mette pippo dentro name
    public String helloName(@PathVariable String name, Model model) {
        model.addAttribute("message", "Hello " + name);   // name viene preso dall'URL automaticamente
        return "hello";
    }
}
