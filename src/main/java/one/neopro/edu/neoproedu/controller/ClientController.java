package one.neopro.edu.neoproedu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import one.neopro.edu.neoproedu.exception.NotFoundException;
import one.neopro.edu.neoproedu.model.ClientAddDTO;
import one.neopro.edu.neoproedu.model.ClientDTO;
import one.neopro.edu.neoproedu.model.ClientEntity;
import one.neopro.edu.neoproedu.service.ClientService;
import one.neopro.edu.neoproedu.service.ConverterService;
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
    public ClientDTO registerNewClient(@RequestBody @Valid ClientAddDTO addDto) throws MethodArgumentNotValidException {
        ClientDTO dto = new ClientDTO();
        dto = converterService.convertAddDTOtoDTO(addDto);
        ClientEntity entity = new ClientEntity();
        entity = clientService.save(converterService.convertToEntity(dto));
        return converterService.convertToDTO(entity);
    }

    @GetMapping("/get-by-id/{id}")
    @Operation(summary = "Поиск клиента по id")
    public ClientDTO getClientById(@PathVariable @Valid Long id) throws MethodArgumentNotValidException, NotFoundException, NullPointerException, IllegalArgumentException, EmptyResultDataAccessException {
        try {
            if (clientService.getClientById(id) == null)
                throw new NotFoundException();
            else {
                return (converterService.convertToDTO(clientService.getClientById(id)));
            }
        } catch (IllegalArgumentException c) {
            throw new IllegalArgumentException();
        } catch (EmptyResultDataAccessException | NullPointerException c) {
            throw new NotFoundException();
        }

    }

    @DeleteMapping(path = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удаление клиента")
    public void deleteClientById(@PathVariable("id") @Valid Long id) {
        try { if (clientService.getClientById(id) != null)
             clientService.deleteClient(id);
            else throw new NotFoundException();
        } catch (NullPointerException | EmptyResultDataAccessException n) {
            throw new NotFoundException();
        }
    }

    @PatchMapping(path = "/update/{id}")
    @Operation(summary = "Обновление данных клиента")
    public ClientDTO updateClient(
            @PathVariable("id") @Valid Long id,
            @RequestParam String name) throws MethodArgumentNotValidException, ConstraintViolationException {

        try {
            if (clientService.getClientById(id) != null) {
                ClientDTO dto = clientService.updateClient(id, name);
                clientService.save(converterService.convertToEntity(dto));
                return dto;
            } else throw new NotFoundException();
        } catch (NullPointerException | EmptyResultDataAccessException n) {
            throw new NotFoundException();
        } catch (IllegalArgumentException c) {
            throw new IllegalArgumentException();
        }
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

