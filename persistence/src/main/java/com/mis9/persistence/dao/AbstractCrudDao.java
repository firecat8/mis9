package com.mis9.persistence.dao;

import com.mis9.dao.CrudDao;
import com.mis9.dao.NotFoundResultsException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author gdimitrova
 */
public abstract class AbstractCrudDao<E> extends AbstractDao<E> implements CrudDao<E> {

    protected AbstractCrudDao(Class<E> dtoClass, EntityManager em) {
        super(dtoClass, em);
    }

    @Override
    public void save(E entity) {
        persistEntity(entity);
    }

    @Override
    public void delete(int id) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaDelete<E> delete = cb.createCriteriaDelete(dtoClassName);
        Root<E> root = delete.from(dtoClassName);
        delete.where(cb.equal(root.get("id"), id));
        em.createQuery(delete).executeUpdate();
    }

    @Override
    public E loadById(int id) throws NotFoundResultsException {
        return getResult("id", id);
    }

    @Override
    public void saveAll(List<E> entities) {
        persistEntities(entities);
    }

    @Override
    public List<E> loadAll() {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<E> query = cb.createQuery(dtoClassName);
        query.select(query.from(dtoClassName));
        return em.createQuery(query).getResultList();
    }

    protected void persistEntity(E entity) {
        em.persist(entity);
    }

    protected void persistEntities(List<E> entities) {
        entities.forEach((e) -> {
            persistEntity(e);
        });

    }
}
