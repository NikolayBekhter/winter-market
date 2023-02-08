package ru.geekbrains.winter.api;

public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String firstName;

    public UserDto() {
    }

    public UserDto(Long id, String username, String password, String firstName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
