package com.mis9.persistence;

import com.mis9.domain.Item;
import com.mis9.domain.Sale;
import com.mis9.persistence.dao.SoldItemDaoImpl;
import com.mis9.persistence.dto.ItemDto;
import com.mis9.persistence.dto.SaleDto;
import com.mis9.persistence.dto.SoldItemDto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gdimitrova
 */
public class SoldItemDaoTestCase extends AbstractCrudDaoTestCase<SoldItemDto, SoldItemDaoImpl> {

    private static ItemDto item;

    private static List<ItemDto> items;

    private static SaleDto sale;

    private static List<SaleDto> sales;

    @Override
    protected SoldItemDto createEntity() {
        return createDefault();
    }

    @Override
    protected List<SoldItemDto> createEntities() {
        return createSoldItems();
    }

    @Override
    protected SoldItemDaoImpl getTestDao() {
        return (SoldItemDaoImpl) registry.getSoldItemDao();
    }

    public static SoldItemDto createDefault() {
        return createEntity(sale, item, 1);
    }

    private static SoldItemDto createEntity(Sale sale, Item item, int soldAmount) {
        return new SoldItemDto(sale, item, soldAmount);
    }

    public static List<SoldItemDto> createSoldItems() {
        List<SoldItemDto> sold = new ArrayList<>();
        sold.add(createEntity(sales.get(0), items.get(0), 1));
        sold.add(createEntity(sales.get(0), items.get(2), 1));
        return sold;
    }

    @Override
    protected void prepareDbData() {

        try {
            beginTransaction();
            ItemDaoTestCase.prepareData();
            item = ItemDaoTestCase.createDefault();
            registry.getItemCategoryDao().save(item);
            items = ItemDaoTestCase.createItems();
            registry.getItemCategoryDao().saveAll(items);

            SaleDaoTestCase.prepareData();
            sale = SaleDaoTestCase.createDefault(item);
            registry.getSaleDao().save(sale);
            sales = SaleDaoTestCase.createSales(items);
            registry.getSaleDao().saveAll(sales);

            commit();
        } catch (Exception e) {
            System.err.println("\n Couldn't prepared data \n" + e.getMessage() + "\n");
            if (getTransaction() != null) {
                rollback();
            }
        }
    }
}
