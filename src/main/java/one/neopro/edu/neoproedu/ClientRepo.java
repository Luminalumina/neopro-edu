package one.neopro.edu.neoproedu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    Client findClientByName(String name);
    Client findClientById(Long id);

}
