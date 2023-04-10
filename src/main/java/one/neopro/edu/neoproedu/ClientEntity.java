package one.neopro.edu.neoproedu;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "clients")
@Schema (description = "Информация о клиенте")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(name = "id")
    private Long id;

    @Column(name = "name")
    @Schema(name = "Имя клиента")
    private String name;

    public ClientEntity(String name) {
        this.name = name;
    }

    public ClientEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
