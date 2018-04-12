package ru.dorofeev22.caregiving.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dorofeev22.caregiving.entities.UserRole;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

}