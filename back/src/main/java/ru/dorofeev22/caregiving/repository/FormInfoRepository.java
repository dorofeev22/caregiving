package ru.dorofeev22.caregiving.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.dorofeev22.caregiving.entities.FormInfo;

@Repository
public interface FormInfoRepository extends PagingAndSortingRepository<FormInfo, Long> {

}