package ru.dorofeev22.caregiving.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dorofeev22.caregiving.entities.User;
import ru.dorofeev22.caregiving.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User uSaved;

    @Before
    public void setUp() {
        uSaved = TestUtils.createUser(null);
    }

    @Test
    public void saveAndFindById() {
        uSaved = userRepository.save(uSaved);
        User uFound = userRepository.findById(uSaved.getId()).orElse(null);
        Assert.assertNotNull(uFound);
        assertThat(uFound.getId()).isEqualTo((uSaved.getId()));
        assertThat(uFound.getLogin()).isEqualTo((uSaved.getLogin()));
        assertThat(uFound.getName()).isEqualTo((uSaved.getName()));
        assertThat(uFound.getPassword()).isEqualTo((uSaved.getPassword()));
        assertThat(uFound.getType()).isEqualTo((uSaved.getType()));
        assertThat(User.class.getDeclaredFields().length).isEqualTo(5);
    }

}
