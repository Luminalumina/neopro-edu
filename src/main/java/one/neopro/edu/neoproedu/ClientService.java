package one.neopro.edu.neoproedu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

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

    public void save(ClientEntity clientEntity) {
        repo.save(clientEntity);
    }

    public void deleteClient(Long id) {
        repo.deleteById(id);
    }

//    public void updateClient(Long id, String name) {
//        Client client = repo.findClientById(id);
//
//        client.setName(name);
//    }
}
