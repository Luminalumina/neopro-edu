package one.neopro.edu.neoproedu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ClientRepo extends JpaRepository<ClientEntity, Long> {
    ClientEntity findClientByName(String name);
    ClientEntity findClientById(Long id);

}
