package ru.dorofeev22.caregiving.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dorofeev22.caregiving.dtos.UserRoleDto;
import ru.dorofeev22.caregiving.entities.UserRole;
import ru.dorofeev22.caregiving.repository.UserRoleRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository repository;

    @Autowired
    private MapperService mapperService;

    public List<UserRoleDto> find() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(this::toDto).collect(Collectors.toList());
    }

    private UserRoleDto toDto(UserRole ur) {
        return mapperService.toDto(ur, UserRoleDto.class);
    }

}
