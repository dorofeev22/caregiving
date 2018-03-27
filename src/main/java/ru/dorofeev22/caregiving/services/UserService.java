package ru.dorofeev22.caregiving.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dorofeev22.caregiving.dtos.UserDto;
import ru.dorofeev22.caregiving.entities.User;
import ru.dorofeev22.caregiving.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void save(UserDto ud) {
        userRepository.save(new User(ud.getName(), ud.getLogin()));
    }

    @Transactional
    public Page<UserDto> find(int page, int size) {
        Page<User> usersPage = userRepository.findAll(PageRequest.of(page, size, Sort.Direction.ASC, "name"));
        return usersPage.map(this::fromDto);
    }

    @Transactional
    public UserDto getById(Long id) {
        Optional<User> o = userRepository.findById(id);
        return fromDto(o.orElse(null));
    }

    @Transactional
    public boolean delete(Long id) {
        Optional<User> o = userRepository.findById(id);
        if (o.isPresent()) {
            userRepository.delete(o.get());
        }
        return o.isPresent();
    }

    private UserDto fromDto(User u) {
        return u != null ? new UserDto(u.getId(), u.getName(), u.getLogin()) : null;
    }

}
