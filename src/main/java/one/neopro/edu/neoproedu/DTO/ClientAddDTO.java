package one.neopro.edu.neoproedu.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ClientAddDTO {
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Zа-яА-Я- 'А-ЩЬЮЯҐЄІЇа-щьюяґєії]*$")
    @Size(min = 2, max = 40)
    private String name;

    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClientAddDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

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
