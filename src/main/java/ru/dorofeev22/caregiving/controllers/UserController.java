package ru.dorofeev22.caregiving.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.dorofeev22.caregiving.dtos.UserDto;
import ru.dorofeev22.caregiving.entities.User;
import ru.dorofeev22.caregiving.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody UserDto ud) {
        userRepository.save(new User(ud.getName(), ud.getLogin()));
    }

    @GetMapping
    public List<UserDto> users() {
        Iterable<User> users = userRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
                .map(u -> new UserDto(u.getId(), u.getName(), u.getLogin())).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, HttpServletResponse response) {
        User u = getById(id);
        if (u != null) {
            userRepository.delete(u);
            response.setStatus(HttpStatus.OK.value());
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }

    @GetMapping("/{id}")
    public UserDto user(@PathVariable Long id, HttpServletResponse response) {
        User u = getById(id);
        if (u != null) {
            response.setStatus(HttpStatus.OK.value());
            return new UserDto(u.getId(), u.getName(), u.getLogin());
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }
    }

    private User getById(Long id) {
        Optional o = userRepository.findById(id);
        return (User) o.orElse(null);
    }

}
