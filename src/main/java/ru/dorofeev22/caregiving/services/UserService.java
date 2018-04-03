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

    @Autowired
    private MapperService mapperService;

    @Transactional
    public void save(UserDto ud) {
        userRepository.save(mapperService.fromDto(ud, User.class));
    }

    @Transactional
    public Page<UserDto> find(int page, int size, String name, String login) {
        return userRepository.findByNameContainingAndLoginContaining(
                name, login, PageRequest.of(page, size, Sort.Direction.ASC, "name")).map(this::toDto);
    }

    @Transactional
    public UserDto getById(Long id) {
        Optional<User> o = userRepository.findById(id);
        return toDto(o.orElse(null));
    }

    @Transactional
    public boolean delete(Long id) {
        Optional<User> o = userRepository.findById(id);
        if (o.isPresent()) {
            userRepository.delete(o.get());
        }
        return o.isPresent();
    }

    private UserDto toDto(User u) {
        return mapperService.toDto(u, UserDto.class);
    }

}
