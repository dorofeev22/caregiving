package ru.dorofeev22.caregiving.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.dorofeev22.caregiving.entities.User;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Page<User> findByNameContainingAndLoginContainingAndTypeIn(String name, String login, List<User.Type> types, Pageable pageable);

}