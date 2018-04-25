package ru.dorofeev22.caregiving.repository;

import ru.dorofeev22.caregiving.entities.UserRole;

import java.util.List;

public interface UserRoleCustomRepository {

    /**
     * Example how to use EntityManager in JPA repository
     * @param firstRow
     * @param maxRows
     * @return
     */
    List<UserRole> findBunchRows(int firstRow, int maxRows);

}