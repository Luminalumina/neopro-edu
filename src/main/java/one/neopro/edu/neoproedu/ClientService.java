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

    public Client getClient(String name) {
        Client client = repo.findClientByName(name);
        if (client == null) {
            throw new IllegalStateException("Client with name " + name + " does not exist.");
        }
        return client;
    }
}
