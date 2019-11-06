package com.mis9.persistence;

import com.mis9.domain.Client;
import com.mis9.persistence.dao.SaleDaoImpl;
import com.mis9.persistence.dto.ClientDto;
import com.mis9.persistence.dto.ItemDto;
import com.mis9.persistence.dto.SaleDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gdimitrova
 */
public class SaleDaoTestCase extends AbstractCrudDaoTestCase<SaleDto, SaleDaoImpl> {

    private static ItemDto item;

    private static List<ItemDto> items;

    private static ClientDto client;

    private static List<ClientDto> clients;

    @Override
    protected SaleDto createEntity() {
        return createDefault(item);
    }

    @Override
    protected List<SaleDto> createEntities() {
        return createSales(items);
    }

    @Override
    protected SaleDaoImpl getTestDao() {
        return (SaleDaoImpl) registry.getSaleDao();
    }

    public static SaleDto createDefault(ItemDto item) {
        return new SaleDto(client,0, item.getAmount(), item.getPrice());
    }

    public static SaleDto createEntity(Client client, Date saleDate, int amount, double totalPrice) {
        return new SaleDto(client, saleDate.getTime(), amount, totalPrice);
    }

    public static List<SaleDto> createSales(List<ItemDto> items) {
        List<SaleDto> sales = new ArrayList<>();
        sales.add(createEntity(clients.get(0), new Date(10), 2, items.get(0).getPrice() + items.get(2).getPrice()));
        sales.add(createEntity(clients.get(1), new Date(11), 1, items.get(1).getPrice()));
        return sales;
    }

    @Override
    protected void prepareDbData() {
        try {
            beginTransaction();
            ItemDaoTestCase.prepareData();
            item = ItemDaoTestCase.createDefault();
            registry.getItemDao().save(item);
            items = ItemDaoTestCase.createItems();
            registry.getItemDao().saveAll(items);
            prepareData();
            commit();
        } catch (Exception e) {
            System.err.println("\n Couldn't prepared data \n" + e.getMessage() + "\n");
            if (getTransaction() != null) {
                rollback();
            }
        }
    }

    public static void prepareData() {
        client = ClientDaoTestCase.createDefault();
        registry.getClientDao().save(client);
        clients = ClientDaoTestCase.createClients();
        registry.getClientDao().saveAll(clients);
    }
}
