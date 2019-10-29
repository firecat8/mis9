package com.mis9.dao;

/**
 *
 * @author gdimitrova
 */
public interface DaoRegistry {

    public ItemDao getItemDao();

    public ItemCategoryDao getItemCategoryDao();

    public ClientDao getClientDao();

    public ClientInfoDao getClientInfoDao();

    public SaleDao getSaleDao();

    public SoldItemDao getSoldItemDao();
}
