package ru.dorofeev22.caregiving.services;

import ru.dorofeev22.caregiving.dtos.UserDto;
import ru.dorofeev22.caregiving.entities.User;
import ru.dorofeev22.caregiving.entities.UserRole;

public abstract class TestUtils {

    public static User createUser(Long id) {
        UserRole userRole = new UserRole(1L, "role", "Great role");
        return new User(id, "UserName", "UserLogin", "qwerty", userRole, User.Type.admin);
    }

    public static UserDto createUserDto(Long id) {
        return new UserDto(id, "UserName", "UserLogin", "qwerty", User.Type.user, 1L, "role");
    }


}
