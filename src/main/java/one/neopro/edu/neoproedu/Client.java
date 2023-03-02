package one.neopro.edu.neoproedu;

import jakarta.validation.constraints.Email;

import javax.persistence.*;

@Entity
@Table(name = "Clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "email")
    @Email
    private String email;


    public Client(Long id) {
    }

    public Client(String name) {
        this.name = name;
    }

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Client() {
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
