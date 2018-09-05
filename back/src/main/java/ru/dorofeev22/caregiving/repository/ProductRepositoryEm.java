package ru.dorofeev22.caregiving.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dorofeev22.caregiving.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductRepositoryEm {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void saveCollection(List<Product> products) {
        for (Product p : products) {
            em.persist(p);
        }
        em.flush();
    }

}
