package one.neopro.edu.neoproedu;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/client")
public class Controller {


    public Controller() {
    }

    @PostMapping("/add")
    @ResponseBody
    public Client registerNewClient(@RequestBody Client client) {
        return client;
    }
}
