package ru.dorofeev22.caregiving.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.dorofeev22.caregiving.entities.UserRole;

public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, Long>, UserRoleCustomRepository {

    Page<UserRole> findByNameContainingAndDescriptionContaining(String name, String description, Pageable pageable);

}