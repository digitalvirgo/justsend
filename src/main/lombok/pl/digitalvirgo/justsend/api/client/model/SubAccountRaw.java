package pl.digitalvirgo.justsend.api.client.model;

import lombok.Data;

@Data
public class SubAccountRaw {

    private String email;
    private String password;
    private String firstname;
    private String surname;
    private String description;
    private Integer points;


    public SubAccountRaw() {
    }

    public SubAccountRaw(String email, String password, String firstname, String surname, String description, Integer points) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.description = description;
        this.points = points;
    }
}
