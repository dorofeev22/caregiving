package ru.dorofeev22.caregiving.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dorofeev22.caregiving.dtos.UserDto;
import ru.dorofeev22.caregiving.entities.User;

@RunWith(SpringRunner.class)
public class MapperServiceTest {

    private MapperService mapperService;

    @Before
    public void before() {
        this.mapperService = new MapperService();
    }


    @Test
    public void fromDtoTest() {
        UserDto ud = TestUtils.createUserDto(1l, null);
        User u = mapperService.fromDto(ud, User.class);
        compare(u, ud);
    }

    @Test
    public void toDtoTest() {
        User u = TestUtils.createUser(1L, null);
        UserDto ud = mapperService.toDto(u, UserDto.class);
        compare(u, ud);
    }

    private void compare(User u, UserDto ud) {
        Assert.assertEquals (Long.valueOf(u.getId()), ud.getId());
        Assert.assertEquals(u.getName(), ud.getName());
        Assert.assertEquals(u.getLogin(), ud.getLogin());
        Assert.assertEquals(u.getType(), ud.getType());
    }

}
