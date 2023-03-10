package one.neopro.edu.neoproedu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepo extends JpaRepository<TestClient, Long> {
}
