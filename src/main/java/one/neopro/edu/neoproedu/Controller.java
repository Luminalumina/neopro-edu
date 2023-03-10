package one.neopro.edu.neoproedu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/client")
public class Controller {

    @Autowired
    private ClientService clientService;

    public Controller(ClientService clientService) {
        this.clientService = clientService;
    }

    public Controller() {
    }

    @PostMapping("/add")
    public Client registerNewClient(@RequestBody Client client) {
        clientService.save(client);
        return client;
    }

    @GetMapping("/get-by-id/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteClientById(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
    }

//    @PutMapping (path = "/update/{id}")
//    public void updateClient(
//            @PathVariable("id") Long id,
//            @RequestBody String name) {
//        clientService.updateClient(id, name);
//    }
}
