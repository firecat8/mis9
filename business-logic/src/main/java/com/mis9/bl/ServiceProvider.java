package com.mis9.bl;

import com.mis9.dao.ClientDao;
import com.mis9.dao.ClientInfoDao;
import com.mis9.dao.DaoRegistry;
import com.mis9.dao.ItemCategoryDao;
import com.mis9.dao.ItemDao;
import com.mis9.dao.SaleDao;
import com.mis9.dao.SoldItemDao;
import com.mis9.persistence.dao.DaoRegistryImpl;
import com.mis9.persistence.dao.EntityManagerFactoryHolder;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 *
 * @author gdimitrova
 */
public class ServiceProvider {

    public static final ServiceProvider INSTANCE = new ServiceProvider();

    private final EntityManagerFactory factory = EntityManagerFactoryHolder.getFactory();

    private EntityManager em = factory.createEntityManager();

    private final DaoRegistry registry = new DaoRegistryImpl(em);

    private ServiceProvider() {
    }

    public void flush() {
        em.flush();
    }

    public void beginTransaction() {
        em.getTransaction().begin();
    }

    public EntityTransaction getTransaction() {
        return em.getTransaction();
    }

    public void rollback() {
        getTransaction().rollback();
    }

    public void commit() {
        em.getTransaction().commit();
    }

    public ClientDao getClientDao() {
        return registry.getClientDao();
    }

    public ItemCategoryDao getItemCategoryDao() {
        return registry.getItemCategoryDao();
    }

    public ItemDao getItemDao() {
        return registry.getItemDao();
    }

    public ClientInfoDao getClientInfoDao() {
        return registry.getClientInfoDao();
    }

    public SaleDao getSaleDao() {
        return registry.getSaleDao();
    }

    public SoldItemDao getSoldItemDao() {
        return registry.getSoldItemDao();
    }
}
