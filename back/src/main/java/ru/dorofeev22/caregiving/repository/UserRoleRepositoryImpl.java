package ru.dorofeev22.caregiving.repository;

import ru.dorofeev22.caregiving.entities.UserRole;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UserRoleRepositoryImpl implements UserRoleCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<UserRole> findBunchRows(int firstRow, int maxRows) {
        return em.createQuery("from UserRole ur", UserRole.class)
                .setFirstResult(firstRow).setMaxResults(maxRows).getResultList();
    }
}
