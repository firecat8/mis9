/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mis9.client.app.holder;

import com.mis9.bl.EntityFactory;
import com.mis9.client.app.SalesSystemController;
import static com.mis9.client.app.holder.EventHandler.svcProvider;
import com.mis9.dao.ItemCategoryDao;
import com.mis9.dao.ItemDao;
import com.mis9.domain.Item;
import com.mis9.domain.ItemCategory;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ChoiceBox;

/**
 *
 * @author gdimitrova
 */
public class CategoryEventHandler extends EventHandler {
    
    public static void onAddEvent(String name, ChoiceBox choiceBox, CategoriesHolder holdable) {
        try {
            svcProvider.beginTransaction();
            svcProvider.getItemCategoryDao().save(EntityFactory.makeCategory(name));
            ItemCategory found = (ItemCategory) svcProvider.getItemCategoryDao().searchByName(name);
            svcProvider.flush();
            svcProvider.commit();
            add(choiceBox, holdable, found);
            
        } catch (Exception ex) {
            Logger.getLogger(SalesSystemController.class.getName()).log(Level.SEVERE, null, ex);
            if (svcProvider.getTransaction() != null) {
                svcProvider.rollback();
            }
        }
    }
    
    public static boolean onUpdateEvent(CategoriesHolder categoriesHolder, ItemCategory entity, String name) {
        ItemCategory newOne = EntityFactory.makeCategory(entity, name);
        if (EventHandler.onUpdateEvent(svcProvider.getItemCategoryDao(), newOne)) {
            categoriesHolder.remove(entity.getName());
            categoriesHolder.add(newOne);
            return true;
        }
        return false;
    }
    
    public static boolean onRemoveEvent(ChoiceBox choiceBox, CategoriesHolder holdable, Boolean isChecked, Object selectedCatName) {
        ItemCategory to = holdable.getEntity(selectedCatName);
        
        ItemCategory from = holdable.getEntity(choiceBox.getSelectionModel().getSelectedItem());
        try {
            svcProvider.beginTransaction();
            ItemDao itemDao = svcProvider.getItemDao();
            ItemCategoryDao icdao = svcProvider.getItemCategoryDao();
            ItemCategory fromCopy = from.makeCopy();
            ItemCategory toCopy = to.makeCopy();
            
            if (isChecked) {
                itemDao.deleteAll(new ArrayList<>(from.getItems()));
            } else {
                updateChanges(fromCopy, toCopy, itemDao, icdao);
            }
            icdao.delete(from.getId());
            svcProvider.flush();
            svcProvider.commit();
            remove(choiceBox, holdable, from.getName());
            
        } catch (Exception ex) {
            Logger.getLogger(SalesSystemController.class.getName()).log(Level.SEVERE, null, ex);
            if (svcProvider.getTransaction() != null) {
                svcProvider.rollback();
            }
            return false;
        }
        return true;
    }
    
    private static void updateChanges(ItemCategory from, ItemCategory to, ItemDao itemDao, ItemCategoryDao icdao) {
        ArrayList<Item> items = new ArrayList<>(from.getItems());
        items.forEach((item) -> {
            item.setItemCategory(to);
        });
        itemDao.saveAll(items);
        to.getItems().addAll(items);
        icdao.save(to);
        from.getItems().clear();
        icdao.save(from);
    }
}
