package one.neopro.edu.neoproedu;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/client")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ConverterService converterService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    public ClientController() {
    }

    @PostMapping("/add")
    public ClientDTO registerNewClient(@RequestBody ClientDTO dto) {
        clientService.save(converterService.convertToEntity(dto));
        return dto;
    }

    @GetMapping("/get-by-id/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        return converterService.convertToDTO(clientService.getClientById(id));
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
