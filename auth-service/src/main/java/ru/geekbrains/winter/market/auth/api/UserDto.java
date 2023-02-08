package ru.geekbrains.winter.market.auth.api;

public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private boolean isActive;

    public UserDto() {
    }

    public UserDto(Long id, String username, String password, String firstName, boolean isActive) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.isActive = isActive;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
