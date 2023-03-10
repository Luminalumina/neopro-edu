package one.neopro.edu.neoproedu;

public class ClientDTO {
    private Long id;
    private String name;

    public ClientDTO(String name) {
        this.name = name;
    }

    public ClientDTO() {
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
