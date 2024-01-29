package med.voll.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/hello")

public class HelloController {

    @GetMapping
    public String helloWorld() {
        return "Hello world from Europe!";
    }

}
