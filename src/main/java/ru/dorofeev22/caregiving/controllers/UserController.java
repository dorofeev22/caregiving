package ru.dorofeev22.caregiving.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.dorofeev22.caregiving.dtos.UserDto;
import ru.dorofeev22.caregiving.services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody UserDto ud) {
        userService.save(ud);
    }

    @GetMapping
    public List<UserDto> users() {
        return userService.find();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, HttpServletResponse response) {
        response.setStatus((userService.delete(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND).value());
    }

    @GetMapping("/{id}")
    public UserDto user(@PathVariable Long id, HttpServletResponse response) {
        UserDto u = userService.getById(id);
        response.setStatus((u != null ? HttpStatus.OK : HttpStatus.NOT_FOUND).value());
        return u;
    }

}
