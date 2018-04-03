package ru.dorofeev22.caregiving.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dorofeev22.caregiving.dtos.UserDto;
import ru.dorofeev22.caregiving.entities.User;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class MapperServiceTest {

    @Configuration
    static class MapperServiceTestContextConfiguration {
        @Bean
        public MapperService mapperService() {
            return new MapperService();
        }
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

    @Autowired
    private MapperService mapperService;

    @Test
    public void fromDtoTest() {
        UserDto ud = createUserDto("Password");
        User u = mapperService.fromDto(ud, User.class);
        compare(u, ud);
    }

    @Test
    public void toDtoTest() {
        User u = createUser();
        UserDto ud = mapperService.toDto(u, UserDto.class);
        compare(u, ud);
    }

    private void compare(User u, UserDto ud) {
        Assert.assertEquals (Long.valueOf(u.getId()), ud.getId());
        Assert.assertEquals(u.getName(), ud.getName());
        Assert.assertEquals(u.getLogin(), ud.getLogin());
        Assert.assertEquals(u.getType(), ud.getType());
    }

    @Test
    public void fromUserDtoTest() {
        UserDto ud = createUserDto("123");
        User u = mapperService.fromUserDto(ud);
        compare(u, ud);
        Assert.assertNotNull("Mistake of password encrypted: ", u.getPassword());
        Assert.assertNotEquals("Unencrypted password ", "123", u.getPassword());
    }

    @Test
    public void toUserDtoTest() {
        User u = createUser();
        UserDto ud = mapperService.toUserDto(u);
        Assert.assertNull("User DTO password must be null after transforming from user", ud.getPassword());
    }

    private User createUser() {
        return new User(1l, "UserName", "UserLogin", "qwerty", User.Type.admin);
    }

    private UserDto createUserDto(String password) {
        return new UserDto(1l, "UserName", "UserLogin", password, User.Type.user);
    }

}
