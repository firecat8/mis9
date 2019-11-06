/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mis9.client.app.holder;

import com.mis9.bl.EntityFactory;
import static com.mis9.client.app.holder.EventHandler.svcProvider;
import com.mis9.domain.Item;
import com.mis9.domain.ItemCategory;
import com.mis9.domain.Sale;
import com.mis9.domain.Size;
import com.mis9.domain.SoldItem;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author gdimitrova
 */
public class ItemsEventHandler extends EventHandler {

    public static boolean onAddEvent(
            String name, ItemCategory category, String description,
            Size itemSize, double price, int amount,
            CategoriesHolder holdable) {
        try {

            Item item = EntityFactory.makeItem(name, category, description, itemSize, price, amount);

            svcProvider.beginTransaction();
            svcProvider.getItemDao().save(item);
            svcProvider.flush();
            svcProvider.commit();
            add(holdable, item);

        } catch (Exception ex) {
            Logger.getLogger(ItemsEventHandler.class.getName()).log(Level.SEVERE, null, ex);
            if (svcProvider.getTransaction() != null) {
                svcProvider.rollback();
            }
            return false;
        }
        return true;
    }

    public static Item onLoadItem(String name, Size itemSize) {
        Item item = null;
        try {

            svcProvider.beginTransaction();
            item = (Item) svcProvider.getItemDao().load(name, itemSize).get(0);
            svcProvider.flush();
            svcProvider.commit();

        } catch (Exception ex) {
            Logger.getLogger(ItemsEventHandler.class.getName()).log(Level.SEVERE, null, ex);
            if (svcProvider.getTransaction() != null) {
                svcProvider.rollback();
            }
        }
        return item;
    }

    public static boolean onRemoveEvent(CategoriesHolder holdable, Item item) {
        try {
            svcProvider.beginTransaction();
            svcProvider.getItemDao().delete(item.getId());
            svcProvider.flush();
            svcProvider.commit();
            remove(holdable, item);

        } catch (Exception ex) {
            Logger.getLogger(ItemsEventHandler.class.getName()).log(Level.SEVERE, null, ex);
            if (svcProvider.getTransaction() != null) {
                svcProvider.rollback();
            }
            return false;
        }
        return true;
    }

    public static boolean onUpdateEvent(
            Item item,
            String name, ItemCategory category, String description,
            Size itemSize, double price, int amount,
            CategoriesHolder categoriesHolder) {
        Item newOne = EntityFactory.makeItem(item, name, category, description, itemSize, price, amount);
        if (EventHandler.onUpdateEvent(svcProvider.getItemDao(), newOne)) {
            categoriesHolder.getEntity(item.getItemCategory().getName()).removeItem(item);
            categoriesHolder.getEntity(newOne.getItemCategory().getName()).addItem(newOne);
            return true;
        }
        return false;
    }

    public static boolean onSoldItemSaveEvent(Sale sale, ObservableList<Item> items, CategoriesHolder categoriesHolder) {

        try {
            svcProvider.beginTransaction();
            for (Item item : items) {
                Item loaded = (Item) svcProvider.getItemDao().loadById(item.getId());
                loaded.setAmount(loaded.getAmount() - item.getAmount());
                svcProvider.getItemDao().update(loaded);
                svcProvider.getSoldItemDao().save(EntityFactory.makeSoldItem(sale, loaded, item.getAmount()));
                svcProvider.getItemDao().update(loaded);
            }
            svcProvider.flush();
            svcProvider.commit();

        } catch (Exception ex) {
            Logger.getLogger(ItemsEventHandler.class.getName()).log(Level.SEVERE, null, ex);
            if (svcProvider.getTransaction() != null) {
                svcProvider.rollback();
            }
            return false;
        }
        return true;
    }

    public static List<SoldItem> onLoadAllSoldItemsEvent(Sale sale) {
        List<SoldItem> items = new ArrayList<>();
        try {
            svcProvider.beginTransaction();
            items = svcProvider.getSoldItemDao().loadAll(sale);
            svcProvider.flush();
            svcProvider.commit();

        } catch (Exception ex) {
            Logger.getLogger(ItemsEventHandler.class.getName()).log(Level.SEVERE, null, ex);
            if (svcProvider.getTransaction() != null) {
                svcProvider.rollback();
            }
        }
        return items;
    }

    private static void add(CategoriesHolder holdable, Item item) {
        holdable.getEntity(item.getItemCategory().getName()).addItem(item);
    }

    private static void remove(CategoriesHolder holdable, Item item) {
        holdable.getEntity(item.getItemCategory().getName()).removeItem(item);
    }
}
