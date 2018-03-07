package ru.dorofeev22.caregiving.repository;

import org.springframework.data.repository.CrudRepository;
import ru.dorofeev22.caregiving.entities.User;
   
public interface UserRepository extends CrudRepository<User, Long>{

}