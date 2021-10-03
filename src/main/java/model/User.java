package model;

public class User {
    private final String name;
    private final String lastName;
    private final String email;
    private final String password;
    private final String birthDate;

    public User(String name, String lastName, String email, String password, String birthDate) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthDate() {
        return birthDate;
    }
}
