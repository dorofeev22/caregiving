package ru.dorofeev22.caregiving.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.dorofeev22.caregiving.dtos.UserDto;
import ru.dorofeev22.caregiving.entities.User;
import ru.dorofeev22.caregiving.services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Validated(UserDto.New.class) @RequestBody UserDto ud) {
        userService.save(ud);
    }

    @PutMapping
    public void update(@Validated(UserDto.Existing.class) @RequestBody UserDto ud) {
        userService.save(ud);
    }

    @GetMapping
    public Page<UserDto> users(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "login", required = false, defaultValue = "") String login,
            @RequestParam(value = "role", required = false, defaultValue = "") String roleName,
            @RequestParam(value = "type", required = false) User.Type type) {
        return userService.find(
                page, size, name, login, (type != null ? Arrays.asList(type) : Arrays.asList(User.Type.values())), roleName);
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

    @GetMapping("/userTypes")
    public List<User.Type> userTypes() {
        return Arrays.asList(User.Type.values());
    }
}
