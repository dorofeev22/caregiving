package ru.dorofeev22.caregiving.services;

import ru.dorofeev22.caregiving.dtos.UserDto;
import ru.dorofeev22.caregiving.entities.User;

public abstract class TestUtils {

    public static User createUser(Long id) {
        return new User(id, "UserName", "UserLogin", "qwerty", User.Type.admin);
    }

    public static UserDto createUserDto(Long id) {
        return new UserDto(id, "UserName", "UserLogin", "qwerty", User.Type.user);
    }


}
