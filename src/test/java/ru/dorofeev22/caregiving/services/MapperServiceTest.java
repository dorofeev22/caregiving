package ru.dorofeev22.caregiving.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    }

    @Autowired
    private MapperService mapperService;

    @Test
    public void fromDtoTest() {
        UserDto ud = createUserDto();
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

    private User createUser() {
        return new User(1l, "UserName", "UserLogin", "qwerty", User.Type.admin);
    }

    private UserDto createUserDto() {
        return new UserDto(1l, "UserName", "UserLogin", "qwerty", User.Type.user);
    }

}
