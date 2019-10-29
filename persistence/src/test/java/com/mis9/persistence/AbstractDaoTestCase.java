/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.persistence;

import com.mis9.persistence.dao.AbstractDao;
import com.mis9.persistence.dao.DaoRegistryImpl;
import com.mis9.persistence.dao.EntityManagerFactoryHolder;
import com.mis9.persistence.dto.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Table;
import junit.framework.TestCase;

/**
 *
 * @author gdimitrova
 * @param <E>
 * @param <Dao>
 */
public abstract class AbstractDaoTestCase<E, Dao extends AbstractDao<E>> extends TestCase {

    private EntityManagerFactory factory;

    protected static EntityManager em;

    protected static DaoRegistryImpl registry;

    protected Dao dao;

    @Override
    protected void setUp() {
        factory = EntityManagerFactoryHolder.getFactory();
        em = factory.createEntityManager();
        registry = new DaoRegistryImpl(em);
        dao = getTestDao();
        removeRows();
        prepareDbData();
    }

    @Override
    protected void tearDown() throws Exception {
        em.clear();
        dao = null;
    }

    protected void removeRows() {
        removeRows(resolveTableName(ItemDto.class));
        removeRows(resolveTableName(ItemCategoryDto.class));
        removeRows(resolveTableName(ClientDto.class));
        removeRows(resolveTableName(ClientInfoDto.class));
        removeRows(resolveTableName(SaleDto.class));
        removeRows(resolveTableName(SoldItemDto.class));
    }

    protected void removeRows(String entityName) {
        EntityTransaction tr = null;
        try {
            beginTransaction();
            em.createQuery("delete FROM " + entityName).executeUpdate();
            commit();
        } catch (Exception e) {
            System.err.println("\n No Removed rows of " + entityName + "\n" + e.getMessage() + "\n");
            if (getTransaction() != null) {
                rollback();
            }
        }

    }

    protected void beginTransaction() {
        em.getTransaction().begin();
    }

    protected EntityTransaction getTransaction() {
        return em.getTransaction();
    }

    protected void rollback() {
        getTransaction().rollback();
    }

    protected void commit() {
        em.getTransaction().commit();
    }

    abstract protected Dao getTestDao();

    abstract protected void prepareDbData();

    private String resolveTableName(Class<?> dtoClass) {
        Table t = dtoClass.getAnnotation(Table.class);
        return t == null ? dtoClass.getSimpleName() : t.name();
    }
}
