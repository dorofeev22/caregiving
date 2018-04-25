package ru.dorofeev22.caregiving.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dorofeev22.caregiving.dtos.UserRoleDto;
import ru.dorofeev22.caregiving.services.UserRoleService;

@RestController
@RequestMapping(value = "/user-role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping
    public Page<UserRoleDto> usersRoles(
            @RequestParam int offset,
            @RequestParam int limit,
            @RequestParam(required = false, defaultValue = "") String name) {
        return userRoleService.find(offset, limit, name);
    }

}
