package ru.dorofeev22.caregiving.services;

import ru.dorofeev22.caregiving.dtos.UserDto;
import ru.dorofeev22.caregiving.dtos.UserRoleDto;
import ru.dorofeev22.caregiving.entities.User;
import ru.dorofeev22.caregiving.entities.UserRole;

public abstract class TestUtils {

    public static User createUser(Long id, UserRole userRole) {
        return new User(id, "UserName", "UserLogin", "qwerty", userRole, User.Type.admin);
    }

    public static UserRole createUserRole(Long id) {
        return new UserRole(id, "role", "Great role");
    }

    public static UserDto createUserDto(Long id, UserRoleDto roleDto) {
        return new UserDto(id, "UserName", "UserLogin", "qwerty", User.Type.user, roleDto);
    }

    public static UserRoleDto createUserRoleDto(Long id) {
        return new UserRoleDto(id, "role", "Great role");
    }

}
