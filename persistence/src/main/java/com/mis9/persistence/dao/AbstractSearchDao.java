package com.mis9.persistence.dao;

import com.mis9.dao.SearchDao;
import com.mis9.domain.Entity;
import javax.persistence.EntityManager;

/**
 *
 * @author gdimitrova
 */
public abstract class AbstractSearchDao<E extends Entity> extends AbstractCrudDao<E> implements SearchDao<E> {

    public AbstractSearchDao(Class<E> dtoClass, EntityManager em) {
        super(dtoClass, em);
    }

    @Override
    public E searchByName(String name) {
        return getResult("name", name);
    }

}
