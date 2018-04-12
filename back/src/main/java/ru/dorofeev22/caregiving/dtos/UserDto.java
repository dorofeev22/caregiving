package ru.dorofeev22.caregiving.dtos;

import ru.dorofeev22.caregiving.entities.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

    public interface Existing {
    }

    public interface New {
    }

    public UserDto() {
    }

    public UserDto(Long id, String name, String login, String password, User.Type type, Long userRoleId, String userRoleName) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.type = type;
        this.userRoleId = userRoleId;
        this.userRoleName = userRoleName;
    }


    @NotNull(groups = Existing.class)
    private Long id;
    @NotBlank(groups = {New.class, Existing.class})
    @Size(max = 100)
    private String name;
    @NotBlank(groups = {New.class, Existing.class})
    @Size(max = 50)
    private String login;
    @NotBlank(groups = New.class)
    private String password;
    @NotNull(groups = {New.class, Existing.class})
    private User.Type type;
    @NotNull(groups = {New.class, Existing.class})
    private Long userRoleId; // that's how to compose the field's name: Entity.field + SubEntity.field (for successful mapping)
    private String userRoleName;

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

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }
}
