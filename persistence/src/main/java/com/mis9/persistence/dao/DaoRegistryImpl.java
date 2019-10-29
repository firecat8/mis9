package com.mis9.persistence.dao;

import com.mis9.dao.ClientDao;
import com.mis9.dao.ClientInfoDao;
import com.mis9.dao.DaoRegistry;
import com.mis9.dao.ItemCategoryDao;
import com.mis9.dao.ItemDao;
import com.mis9.dao.SaleDao;
import com.mis9.dao.SoldItemDao;
import javax.persistence.EntityManager;

/**
 *
 * @author gdimitrova
 */
public class DaoRegistryImpl implements DaoRegistry {

    private final ItemCategoryDaoImpl categoryDao;

    private final ItemDaoImpl itemsDao;

    private final ClientDaoImpl clientDao;

    private final ClientInfoDaoImpl clientInfoDao;

    private final SaleDaoImpl saleDao;

    private final SoldItemDaoImpl soldItemDao;

    public DaoRegistryImpl(EntityManager em) {

        this.categoryDao = new ItemCategoryDaoImpl(em);

        this.itemsDao = new ItemDaoImpl(em);

        this.clientDao = new ClientDaoImpl(em);

        this.clientInfoDao = new ClientInfoDaoImpl(em);

        this.saleDao = new SaleDaoImpl(em);

        this.soldItemDao = new SoldItemDaoImpl(em);
    }

    @Override
    public ItemCategoryDao getItemCategoryDao() {
        return categoryDao;
    }

    @Override
    public ItemDao getItemDao() {
        return itemsDao;
    }

    @Override
    public ClientDao getClientDao() {
        return clientDao;
    }

    @Override
    public ClientInfoDao getClientInfoDao() {
        return clientInfoDao;
    }

    @Override
    public SaleDao getSaleDao() {
        return saleDao;
    }

    @Override
    public SoldItemDao getSoldItemDao() {
        return soldItemDao;
    }

}
