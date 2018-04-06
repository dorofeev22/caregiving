package ru.dorofeev22.caregiving.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dorofeev22.caregiving.dtos.UserDto;
import ru.dorofeev22.caregiving.entities.User;
import ru.dorofeev22.caregiving.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserService();
        }

        @Bean
        public MapperService mapperService() {
            return new MapperService();
        }
    }

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserDto newUserDto;
    private User userInDatabase;
    private UserDto editedUserDto;

    @Before
    public void setUp() {
        newUserDto = TestUtils.createUserDto(null);
        userInDatabase = TestUtils.createUser(null);
        userInDatabase.setPassword(passwordEncoder.encode(userInDatabase.getPassword()));
        userInDatabase = userRepository.save(userInDatabase);
        editedUserDto = TestUtils.createUserDto(userInDatabase.getId());
    }


    @Test
    public void toDto() {
        UserDto ud = userService.toDto(TestUtils.createUser(1l));
        Assert.assertNull("User DTO password must be null: ", ud.getPassword());
    }

    @Test
    public void fromDto() {
        String password = "123";
        newUserDto.setPassword(password);
        User u = userService.fromDto(newUserDto);
        Assert.assertNotNull("Password must exists: ", u.getPassword());
        Assert.assertNotEquals("Unencrypted password: ", password, u.getPassword());

        u = userService.fromDto(editedUserDto);
        Assert.assertEquals("Password was changed: ", userInDatabase.getPassword(), u.getPassword());
    }
}
