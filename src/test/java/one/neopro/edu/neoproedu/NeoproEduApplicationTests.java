package one.neopro.edu.neoproedu;

import one.neopro.edu.neoproedu.model.ClientAddDTO;
import one.neopro.edu.neoproedu.model.ClientDTO;
import one.neopro.edu.neoproedu.model.ClientEntity;
import one.neopro.edu.neoproedu.repository.ClientRepo;
import one.neopro.edu.neoproedu.service.ClientService;
import one.neopro.edu.neoproedu.service.ConverterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@SpringBootTest(classes = NeoproEduApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NeoproEduApplicationTests {

    @LocalServerPort
    private int port;
    @Autowired
    TestRestTemplate testRestTemplate = new TestRestTemplate();
    @Autowired
    private ClientRepo repo;
    @Autowired
    private ConverterService converterService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ClientService clientService;


    @Test
    void contextLoads() {
    }


    @Test
    public void whenRegisterNewClient() {

        ClientDTO max = converterService.convertAddDTOtoDTO(new ClientAddDTO("Max"));
        ResponseEntity<ClientDTO> response = testRestTemplate.postForEntity("http://localhost:" + port + "/client/add", max, ClientDTO.class);

        assertEquals(max.getName(), response.getBody().getName());


    }

    @Test
    public void whenGetClientById() {
        ClientAddDTO clientAddDTO = new ClientAddDTO("Bob");
        ClientDTO bob = converterService.convertAddDTOtoDTO(clientAddDTO);
        ResponseEntity<ClientDTO> responseBob = testRestTemplate.postForEntity("http://localhost:" + port + "/client/add", bob, ClientDTO.class);

        ResponseEntity<ClientDTO> response1 = testRestTemplate.getForEntity("http://localhost:" + port + "/client/get-by-id/{id}", ClientDTO.class, responseBob.getBody().getId());
        assertEquals(responseBob.getBody().getName(), response1.getBody().getName());
    }
    ResponseEntity<List<ClientDTO>> listAllClients() {
        List<ClientDTO> dtoList = clientService.findAll();
        return ResponseEntity.ok().body(dtoList);
    }
    Long countOfClients() {
        return Long.valueOf(listAllClients().getBody().size());
    }

    @Test
    public void whenDeleteClientById() {
        ClientAddDTO clientAddDTO = new ClientAddDTO("Karl");
        ClientDTO karl = converterService.convertAddDTOtoDTO(clientAddDTO);
        ResponseEntity<ClientDTO> responseKarl = testRestTemplate.postForEntity("http://localhost:" + port + "/client/add", karl, ClientDTO.class);
        Long id = responseKarl.getBody().getId();
        Long countBefore = countOfClients();
        testRestTemplate.delete("http://localhost:" + port + "/client/delete/{id}", id);
        Long countAfter = countOfClients();
        Assertions.assertTrue(countBefore > countAfter);
    }

    @Test
    public void whenUpdatePerson() {
        ClientAddDTO clientAddDTO = new ClientAddDTO("Phil");
        ClientDTO phil = converterService.convertAddDTOtoDTO(clientAddDTO);
        String newName = "PhilNew";
        ResponseEntity<ClientDTO> responsePhil = testRestTemplate.postForEntity("http://localhost:" + port + "/client/add", phil, ClientDTO.class);
        Long id = responsePhil.getBody().getId();
        ClientDTO editedPhilDTO = clientService.updateClient(id, newName);
        ResponseEntity<ClientDTO> responseEditedPhil = testRestTemplate.getForEntity("http://localhost:" + port + "/client/get-by-id/{id}", ClientDTO.class, id);

        assertEquals(newName, clientService.getClientById(id).getName());
        assertEquals(responseEditedPhil.getBody().getId(), responsePhil.getBody().getId());

    }
}

class ClientModelMapperTest {
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void testModelMapperToEntity() {
        ClientDTO dto = new ClientDTO("Alex");

        ClientEntity entity = modelMapper.map(dto, ClientEntity.class);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());

    }

    @Test
    public void testModelMapperToDto() {
        ClientEntity entity = new ClientEntity("Jack");

        ClientDTO dto = modelMapper.map(entity, ClientDTO.class);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());

    }
}
