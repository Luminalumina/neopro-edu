package one.neopro.edu.neoproedu;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/client")
//@Tag(name = "Клиенты", description = "Все операции с клиентами")
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
//    @Operation(summary = "Регистрация нового клиента")
    public ClientDTO registerNewClient(@RequestBody ClientAddDTO addDto) {
        ClientDTO dto = new ClientDTO();
        dto = converterService.convertAddDTOtoDTO(addDto);
        ClientEntity entity = new ClientEntity();
        entity = clientService.save(converterService.convertToEntity(dto));
        return converterService.convertToDTO(entity);
    }

    @GetMapping("/get-by-id/{id}")
//    @Operation(summary = "Поиск клиента по id")
    public ClientDTO getClientById(@PathVariable Long id) {
        return converterService.convertToDTO(clientService.getClientById(id));
    }

    @DeleteMapping(path = "/delete/{id}")
//    @Operation(summary = "Удаление клиента")
            public void deleteClientById(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
    }

    @PatchMapping(path = "/update/{id}")
//    @Operation(summary = "Обновление данных клиента")
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
