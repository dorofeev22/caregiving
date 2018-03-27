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
        UserDto ud = new UserDto(1l, "UserName", "UserLogin");
        User u = mapperService.fromDto(ud, User.class);
        compare(u, ud);
    }

    @Test
    public void toDtoTest() {
        User u = new User(1l, "UserName", "UserLogin");
        UserDto ud = mapperService.toDto(u, UserDto.class);
        compare(u, ud);
    }

    private void compare(User u, UserDto ud) {
        Assert.assertEquals(u.getId(), ud.getId());
        Assert.assertEquals(u.getName(), ud.getName());
        Assert.assertEquals(u.getLogin(), ud.getLogin());
    }

}
