package ru.dorofeev22.caregiving.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.dorofeev22.caregiving.entities.User;
   
public interface UserRepository extends PagingAndSortingRepository<User, Long>, CrudRepository<User, Long>{

    @Override
    Page<User> findAll(Pageable pageable);
}