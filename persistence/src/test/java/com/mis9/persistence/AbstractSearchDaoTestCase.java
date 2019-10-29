/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.persistence;

import com.mis9.domain.Entity;
import com.mis9.persistence.dao.AbstractSearchDao;

/**
 *
 * @author gdimitrova
 * @param <E>
 * @param <S>
 */
public abstract class AbstractSearchDaoTestCase<E extends Entity, S extends AbstractSearchDao<E>> extends AbstractCrudDaoTestCase<E, S> {

    public void testSearchByName() {
        try {
            beginTransaction();
            E entity = createEntity();
            dao.save(entity);
            dao.searchByName(getEntityName());
            commit();
        } catch (Exception e) {
            System.err.println("\n Could search by name \n" + e.getMessage() + "\n");
            if (getTransaction() != null) {
                rollback();
            }
        }
    }

    abstract protected String getEntityName();
}
