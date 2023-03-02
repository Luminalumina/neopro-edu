package one.neopro.edu.neoproedu;

import jakarta.validation.constraints.Email;

import javax.persistence.*;

@Entity
@Table(name = "Clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "Name")
    private String name;



//    public Client(Long id) {
//    }

    public Client(String name) {
        this.name = name;
    }



    public Client() {
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
