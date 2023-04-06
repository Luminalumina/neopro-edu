package one.neopro.edu.neoproedu;

public class ClientAddDTO {
    private String name;

    public ClientAddDTO(String name) {
        this.name = name;
    }

    public ClientAddDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
