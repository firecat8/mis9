package com.mis9.persistence.dao;

import com.mis9.dao.CrudDao;
import com.mis9.dao.NotFoundResultsException;
import com.mis9.domain.Entity;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author gdimitrova
 */
public abstract class AbstractCrudDao<E extends Entity> extends AbstractDao<E> implements CrudDao<E> {
    
    protected AbstractCrudDao(Class<E> dtoClass, EntityManager em) {
        super(dtoClass, em);
    }
    
    @Override
    public void save(E entity) {
        saveOrUpdate(entity);
    }
    
    @Override
    public void update(E update) {
        super.update(update, loadProperties(update));
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
    public void deleteAll(List<E> list) {
        list.forEach((e) -> {
            delete(e.getId());
//            em.remove(e);
        });
    }
    
    @Override
    public E loadById(int id) throws NotFoundResultsException {
        return getResult("id", id);
    }
    
    @Override
    public void saveAll(List<E> entities) {
        saveOrUpdateAll(entities);
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
    
    protected void saveOrUpdateAll(List<E> entities) {
        entities.forEach((e) -> {
            saveOrUpdate(e);
        });
    }
    
    protected void mergeEntity(E entity) {
        em.merge(entity);
    }
    
    private void saveOrUpdate(E entity) {
        if (entity.getId() > 0) {
            mergeEntity(entity);
            return;
        }
        persistEntity(entity);
    }
    
    protected abstract Map<String, Object> loadProperties(E newOne);
}
