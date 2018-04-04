package pl.justsend.api.client.model;

/**
 * Created with IntelliJ IDEA.
 * User: posiadacz
 * Date: 28.03.18
 * Time: 16:50
 */
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

    @Override
    public String toString() {
        return "SubAccountRaw{" + "email='" + email + '\'' + ", password='" + password + '\'' + ", firstname='" + firstname + '\'' + ", surname='" + surname + '\'' + ", description='" + description + '\'' + ", points=" + points + '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
