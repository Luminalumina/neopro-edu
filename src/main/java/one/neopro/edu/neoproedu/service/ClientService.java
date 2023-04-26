package one.neopro.edu.neoproedu.service;

import one.neopro.edu.neoproedu.DTO.ClientDTO;
import one.neopro.edu.neoproedu.exception.ArgumentNotValidException;
import one.neopro.edu.neoproedu.exception.NotFoundException;
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

    public ClientEntity getClientById(Long id) {
        if (repo.findClientById(id) != null) {
            return (repo.findClientById(id));
        } else
            throw new NotFoundException();
    }

    public ClientEntity save(ClientEntity clientEntity) {
        repo.save(clientEntity);
        return clientEntity;
    }

    public void deleteClient(Long id) {
        getClientById(id);
        repo.deleteById(id);
    }

    public ClientDTO updateClient(Long id, String name) throws ArgumentNotValidException {
        ClientEntity client = getClientById(id);
        client.setName(name);
        if (name.matches("^[a-zA-Zа-яА-Я- 'А-ЩЬЮЯҐЄІЇа-щьюяґєії]*$")) {
            repo.save(client);
        return converterService.convertToDTO(client);
    }
        else throw new ArgumentNotValidException();

    }

    public List<ClientDTO> findAll() {
        List<ClientDTO> dtoList = new ArrayList<>();

        for (ClientEntity entity : repo.findAll()) {
            dtoList.add(converterService.convertToDTO(entity));
        }
        return dtoList;
    }
}
