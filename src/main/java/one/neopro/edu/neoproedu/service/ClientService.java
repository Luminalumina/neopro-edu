package one.neopro.edu.neoproedu.service;

import one.neopro.edu.neoproedu.model.ClientDTO;
import one.neopro.edu.neoproedu.model.ClientEntity;
import one.neopro.edu.neoproedu.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ConverterService converterService;
    private final ClientRepo repo;

    @Autowired
    public ClientService(ClientRepo repo) {
        this.repo = repo;
    }

    public ClientEntity getClientByName(String name) {

        return repo.findClientByName(name);
    }

    public ClientEntity getClientById(Long id) {

        return repo.findClientById(id);
    }

    public ClientEntity save(ClientEntity clientEntity) {
        repo.save(clientEntity);
        return clientEntity;
    }

    public void deleteClient(Long id) {
        repo.deleteById(id);
    }

    public ClientDTO updateClient(Long id, String name) {
        ClientEntity client = getClientById(id);
        client.setName(name);
        repo.save(client);

        return converterService.convertToDTO(client);
    }

    public List<ClientDTO> findAll() {
        List<ClientDTO> dtoList = new ArrayList<>();

        for (ClientEntity entity : repo.findAll()) {
            dtoList.add(converterService.convertToDTO(entity));
        }
        return dtoList;
    }
}
