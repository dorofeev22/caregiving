package ru.dorofeev22.caregiving.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

    public UserDto() {
    }

    public UserDto(long id, String name, String login) {
        this.id = id;
        this.name = name;
        this.login = login;
    }

    private long id;
    @NotNull
    @Size(min = 1, max = 100)
    private String name;
    @NotNull
    @Size(min = 1, max = 50)
    private String login;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
