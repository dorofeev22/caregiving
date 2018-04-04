package ru.dorofeev22.caregiving.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dorofeev22.caregiving.dtos.UserDto;
import ru.dorofeev22.caregiving.entities.User;
import ru.dorofeev22.caregiving.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MapperService mapperService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void save(UserDto ud) {
        userRepository.save(fromDto(ud));
    }

    @Transactional
    public Page<UserDto> find(int page, int size, String name, String login, List<User.Type> types) {
        return userRepository.findByNameContainingAndLoginContainingAndTypeIn(
                name, login, types, PageRequest.of(page, size, Sort.Direction.ASC, "name")).map(this::toDto);
    }

    @Transactional
    public UserDto getById(Long id) {
        return toDto(findById(id));
    }

    private User findById(Long id) {
        Optional<User> o = userRepository.findById(id);
        return o.orElse(null);
    }

    @Transactional
    public boolean delete(Long id) {
        Optional<User> o = userRepository.findById(id);
        if (o.isPresent()) {
            userRepository.delete(o.get());
        }
        return o.isPresent();
    }

    /**
     * Transform User DTO to User entity with password encoding for new user
     * @param ud
     * @return
     */
    public User fromDto(UserDto ud) {
        User u = mapperService.fromDto(ud, User.class);
        if (ud.getId() != null) {
            User ou = findById(ud.getId());
            u.setPassword(ou.getPassword());
        } else {
            u.setPassword(passwordEncoder.encode(ud.getPassword()));
        }
        return u;
    }

    /**
     * Transform User entity to User DTO without password
     * @param u
     * @return
     */
    public UserDto toDto(User u) {
        UserDto ud = mapperService.toDto(u, UserDto.class);
        ud.setPassword(null);
        return ud;
    }

}
