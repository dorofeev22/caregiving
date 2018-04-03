package ru.dorofeev22.caregiving.controllers;

import org.assertj.core.util.Strings;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dorofeev22.caregiving.dtos.UserDto;
import ru.dorofeev22.caregiving.services.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody UserDto ud) throws JSONException {
        if (ud.getId() == null && Strings.isNullOrEmpty(ud.getPassword())) {
            return new ResponseEntity<>("Password must be exists", HttpStatus.BAD_REQUEST);
        }
        userService.save(ud);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public Page<UserDto> users(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "login", required = false, defaultValue = "") String login) {
        return userService.find(page, size, name, login);
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
