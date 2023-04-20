package one.neopro.edu.neoproedu.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ClientAddDTO {
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Zа-яА-Я- 'А-ЩЬЮЯҐЄІЇа-щьюяґєії]*$")
    @Size(min = 2, max = 40)
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
