package ru.dorofeev22.caregiving.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BaseService {

    protected <T> T findById(CrudRepository<T, Long> repository, Long id) {
        Optional<T> o = repository.findById(id);
        return o.orElse(null);
    }

}
