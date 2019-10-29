/*
 * EuroRisk Systems (c) Ltd. All rights reserved.
 */
package com.mis9.persistence;

import com.mis9.domain.Entity;
import com.mis9.persistence.dao.AbstractCrudDao;
import java.util.List;

/**
 *
 * @author gdimitrova
 * @param <E>
 * @param <Dao>
 */
public abstract class AbstractCrudDaoTestCase<E extends Entity, Dao extends AbstractCrudDao<E>> extends AbstractDaoTestCase<E, Dao> {

    public void testSave() {
        boolean isSuccess = true;
        try {
            beginTransaction();
            dao.save(createEntity());
            commit();
        } catch (Exception e) {
            isSuccess = false;
            System.err.println("\n Couldn't save \n" + e.getMessage() + "\n");
            if (getTransaction() != null) {
                rollback();
            }
        }
        assertTrue("Couldn't save", isSuccess);
    }

    public void testSaveAll() {
        boolean isSuccess = true;
        try {
            beginTransaction();
            dao.saveAll(createEntities());
            commit();
        } catch (Exception e) {
            isSuccess = false;
            System.err.println("\n Couldn't save all \n" + e.getMessage() + "\n");
            if (getTransaction() != null) {
                rollback();
            }
        }
        assertTrue("Couldn't save all", isSuccess);
    }

    public void testLoadById() {
        boolean isSuccess = true;
        try {
            beginTransaction();
            E expected = createEntity();
            dao.save(expected);
            E actual = dao.loadById(expected.getId());
            assertEquals(expected, actual);
            commit();
        } catch (Exception e) {
            isSuccess = false;
            System.err.println("\n Could not load by id \n" + e.getMessage() + "\n");
            if (getTransaction() != null) {
                rollback();
            }
        }
        assertTrue("Couldn't  load by id", isSuccess);
    }

    public void testLoadByIdNotFound() {
        loadById(100000);
    }

    public void testDelete() {
        boolean isSuccess = true;
        try {
            beginTransaction();
            E expected = createEntity();
            dao.save(expected);
            dao.delete(expected.getId());
            loadById(expected.getId());
            commit();
        } catch (Exception e) {
            isSuccess = false;
            System.err.println("\n Couldn't delete \n" + e.getMessage() + "\n");
            if (getTransaction() != null) {
                rollback();
            }
        }
        assertTrue("Couldn't delete", isSuccess);
    }

    private void loadById(int id) {
        E actual = null;
        try {
            actual = dao.loadById(100);
        } catch (Exception e) {

        }
        assertNull(actual);
    }

    abstract protected E createEntity();

    abstract protected List<E> createEntities();
}
