package one.neopro.edu.neoproedu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import one.neopro.edu.neoproedu.DTO.ClientAddDTO;
import one.neopro.edu.neoproedu.DTO.ClientDTO;
import one.neopro.edu.neoproedu.exception.ArgumentNotValidException;
import one.neopro.edu.neoproedu.exception.NotFoundException;
import one.neopro.edu.neoproedu.model.ClientEntity;
import one.neopro.edu.neoproedu.service.ClientService;
import one.neopro.edu.neoproedu.service.ConverterService;
import org.intellij.lang.annotations.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/client")
@Tag(name = "Клиенты", description = "Все операции с клиентами")
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
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Регистрация нового клиента")
    @Parameter(name = "name", example = "Sam Smith")

    public ClientDTO registerNewClient(@RequestBody @Valid ClientAddDTO addDto) throws ArgumentNotValidException, MethodArgumentNotValidException {
        ClientDTO dto = new ClientDTO();
        dto = converterService.convertAddDTOtoDTO(addDto);
        ClientEntity entity = new ClientEntity();
        entity = clientService.save(converterService.convertToEntity(dto));
        return converterService.convertToDTO(entity);
    }

    @GetMapping("/get-by-id/{id}")
    @Operation(summary = "Поиск клиента по id")
    @Parameter(name = "id", example = "36")
    public ClientDTO getClientById(@PathVariable @Valid Long id) throws NotFoundException, NullPointerException, IllegalArgumentException, EmptyResultDataAccessException, ArgumentNotValidException {
        return (converterService.convertToDTO(clientService.getClientById(id)));
    }

    @DeleteMapping(path = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удаление клиента")
    @Parameter(name = "id", example = "41")
    public void deleteClientById(@PathVariable("id") @Valid Long id) {
        clientService.deleteClient(id);
    }

    @PatchMapping(path = "/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Обновление данных клиента")
    @Parameter(name = "id", example = "36")
    @Parameter(name = "name", example = "Helga")
    public ClientDTO updateClient(
            @PathVariable(name = "id") @Valid Long id,
            @Valid @Pattern (value = "^[a-zA-Zа-яА-Я- 'А-ЩЬЮЯҐЄІЇа-щьюяґєії]*$")
            @RequestParam String name) throws ConstraintViolationException, ArgumentNotValidException {

        ClientDTO dto = clientService.updateClient(id, name);
        clientService.save(converterService.convertToEntity(dto));
        return dto;
    }
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

