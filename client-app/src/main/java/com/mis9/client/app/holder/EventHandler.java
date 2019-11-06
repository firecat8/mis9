/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mis9.client.app.holder;

import com.mis9.bl.ServiceProvider;
import com.mis9.client.app.SalesSystemController;
import com.mis9.dao.CrudDao;
import com.mis9.domain.Entity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

/**
 *
 * @author gdimitrova
 */
public abstract class EventHandler {

    protected static ServiceProvider svcProvider = ServiceProvider.INSTANCE;

    protected static <E extends Entity> void add(ChoiceBox choiceBox, Holdable holdable, E entity) {
        holdable.add(entity);
        Object name = holdable.getName(entity);
        choiceBox.getItems().add(name);
        choiceBox.setValue(name);
    }

    protected static <E extends Entity> void remove(ChoiceBox choiceBox, Holdable holdable, String name) {
        holdable.remove(name);
        ObservableList items = choiceBox.getItems();
        items.remove(name);
        if (items.isEmpty()) {
            return;
        }
        choiceBox.setValue(items.get(0));
    }


    protected static <E extends Entity> boolean onUpdateEvent(CrudDao dao, E entity) {
        try {
            svcProvider.beginTransaction();
            dao.update(entity);
            svcProvider.flush();
            svcProvider.commit();
        } catch (Exception ex) {
            Logger.getLogger(SalesSystemController.class.getName()).log(Level.SEVERE, null, ex);
            if (svcProvider.getTransaction() != null) {
                svcProvider.rollback();
            }
            return false;
        }
        return true;
    }
}
