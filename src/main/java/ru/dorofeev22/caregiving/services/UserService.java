package ru.dorofeev22.caregiving.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dorofeev22.caregiving.dtos.UserDto;
import ru.dorofeev22.caregiving.entities.User;
import ru.dorofeev22.caregiving.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void save(UserDto ud) {
        userRepository.save(new User(ud.getName(), ud.getLogin()));
    }

    @Transactional
    public List<UserDto> find() {
        Iterable<User> users = userRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
                .map(u -> fromDto(u)).collect(Collectors.toList());
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
