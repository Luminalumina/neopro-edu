package one.neopro.edu.neoproedu;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/client")
public class Controller {

    public Controller() {
    }

    @PostMapping("")
    public String registerNewClient(@RequestBody Long id) {
        Client client = new Client(id);
        return String.valueOf(id);
    }
}
