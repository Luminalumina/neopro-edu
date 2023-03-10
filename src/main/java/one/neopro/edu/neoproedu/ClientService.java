package one.neopro.edu.neoproedu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepo repo;

    @Autowired
    public ClientService(ClientRepo repo) {
        this.repo = repo;
    }

    public Client getClientByName(String name) {

        return repo.findClientByName(name);
    }

    public Client getClientById(Long id) {
        return repo.findClientById(id);
    }

    public void save(Client client) {
        repo.save(client);
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
