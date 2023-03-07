package one.neopro.edu.neoproedu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/client")
public class Controller {

    private ClientService clientService;

    @Autowired
    public Controller(ClientService clientService) {
        this.clientService = clientService;
    }

    public Controller() {
    }

    @PostMapping("/add")
    @ResponseBody
    public Client registerNewClient(@RequestBody Client client) {
        clientService.save(client);
        return client;
    }

    @GetMapping("/get-by-name")
    public Client getClientByName(@RequestBody String name) {
        return clientService.getClientByName(name);
    }
}
