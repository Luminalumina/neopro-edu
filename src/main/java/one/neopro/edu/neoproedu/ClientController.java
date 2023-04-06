package one.neopro.edu.neoproedu;

import jakarta.persistence.Index;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public ClientDTO registerNewClient(@RequestBody ClientAddDTO addDto) {
        ClientDTO dto = new ClientDTO();
        dto = converterService.convertAddDTOtoDTO(addDto);
        ClientEntity entity = new ClientEntity();
        entity = clientService.save(converterService.convertToEntity(dto));
        return converterService.convertToDTO(entity);
    }

    @GetMapping("/get-by-id/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        return converterService.convertToDTO(clientService.getClientById(id));
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteClientById(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
    }

    @PatchMapping(path = "/update/{id}")
    public ClientDTO updateClient(
            @PathVariable("id") Long id,
            @RequestParam String name) {

        ClientDTO dto = clientService.updateClient(id, name);
        clientService.save(converterService.convertToEntity(dto));
        return dto;
    }

//    @GetMapping(path = "/all-clients")
//    ResponseEntity<List<ClientDTO>> listAllClients() {
//        List<ClientDTO> dtoList = clientService.findAll();
//        return ResponseEntity.ok().body(dtoList);
//    }
//
//    @GetMapping(path = "/count")
//    Long countOfClients() {
//        return Long.valueOf(listAllClients().getBody().size());
//    }
}
