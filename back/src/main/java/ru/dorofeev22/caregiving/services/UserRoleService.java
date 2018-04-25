package ru.dorofeev22.caregiving.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dorofeev22.caregiving.dtos.UserRoleDto;
import ru.dorofeev22.caregiving.entities.UserRole;
import ru.dorofeev22.caregiving.repository.BunchPageable;
import ru.dorofeev22.caregiving.repository.UserRoleRepository;

import java.util.List;

@Service
public class UserRoleService extends BaseService {

    @Autowired
    private UserRoleRepository repository;

    @Autowired
    private MapperService mapperService;

    @Transactional
    public Page<UserRoleDto> find(int page, int size, String name, String description) {
        return repository.findByNameContainingAndDescriptionContaining(
                name, description, PageRequest.of(page, size, Sort.Direction.ASC, "name")).map(this::toDto);

    }

    /**
     * Get bunch of rows.
     * @param offset first row
     * @param limit row count
     * @param name filtering condition by name
     * @return
     */
    @Transactional
    public Page<UserRoleDto> find(int offset, int limit, String name) {
        return repository.findByNameContaining(name, new BunchPageable(offset, limit)).map(this::toDto);

    }


    /**
     * Example how to use EntityManager in JPA repository
     */
    @Transactional
    public List<UserRole> find(int offset, int limit) {
        return repository.findBunchRows(offset, limit);
    }

    private UserRoleDto toDto(UserRole ur) {
        return mapperService.toDto(ur, UserRoleDto.class);
    }

    @Transactional
    public UserRole findById(Long id) {
        return findById(repository, id);
    }

}
