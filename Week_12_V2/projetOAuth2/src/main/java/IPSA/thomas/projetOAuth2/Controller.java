package IPSA.thomas.projetOAuth2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/hello")
    public String helloWorld(){
        return "hello world";
    }

    @GetMapping("/restricted")
    public String restricted(){
        return "<h1> welcome on the secret page </h1>";
    }
}
