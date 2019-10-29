package com.mis9.persistence.dao;

import com.mis9.dao.SearchDao;
import javax.persistence.EntityManager;

/**
 *
 * @author gdimitrova
 */
public abstract class AbstractSearchDao<Entity> extends AbstractCrudDao<Entity> implements SearchDao<Entity> {

    public AbstractSearchDao(Class<Entity> dtoClass, EntityManager em) {
        super(dtoClass, em);
    }

    @Override
    public Entity searchByName(String name) {
        return getResult("name", name);
    }

}

