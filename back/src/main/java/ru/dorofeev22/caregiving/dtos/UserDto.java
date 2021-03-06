package ru.dorofeev22.caregiving.dtos;

import org.springframework.format.annotation.DateTimeFormat;
import ru.dorofeev22.caregiving.entities.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

public class UserDto {

    public interface Existing {
    }

    public interface New {
    }

    public UserDto() {
    }

    public UserDto(Long id, String name, String login, String password, User.Type type, UserRoleDto roleDto) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.type = type;
        this.role = roleDto;
    }


    @NotNull(groups = Existing.class)
    private Long id;
    @NotBlank(groups = {New.class, Existing.class})
    @Size(max = 100, groups = {New.class, Existing.class})
    private String name;
    @NotBlank(groups = {New.class, Existing.class})
    @Size(max = 50, groups = {New.class, Existing.class})
    private String login;
    @NotBlank(groups = New.class)
    private String password;
    @NotNull(groups = {New.class, Existing.class})
    private User.Type type;
    @NotNull(groups = {New.class, Existing.class})
    // TODO validate roleId
    private UserRoleDto role;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime lockDateTime;

    public UserRoleDto getRole() {
        return role;
    }

    public void setRole(UserRoleDto role) {
        this.role = role;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User.Type getType() {
        return type;
    }

    public void setType(User.Type type) {
        this.type = type;
    }

    public ZonedDateTime getLockDateTime() {
        return lockDateTime;
    }

    public void setLockDateTime(ZonedDateTime lockDateTime) {
        this.lockDateTime = lockDateTime;
    }
}
