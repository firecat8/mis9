/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mis9.client.app.holder;

import com.mis9.bl.EntityFactory;
import com.mis9.client.app.SalesSystemController;
import static com.mis9.client.app.holder.EventHandler.svcProvider;
import com.mis9.domain.Client;
import com.mis9.domain.ClientInfo;
import com.mis9.domain.Sale;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.apache.poi.hssf.usermodel.HeaderFooter.date;

/**
 *
 * @author gdimitrova
 */
public class ClientEventHandler extends EventHandler {

    public static ClientInfo onSearchByEmailEvent(String mail) {
        ClientInfo ci = null;
        try {
            svcProvider.beginTransaction();
            ci = svcProvider.getClientInfoDao().loadByEmail(mail);
            svcProvider.flush();
            svcProvider.commit();

        } catch (Exception ex) {
            Logger.getLogger(SalesSystemController.class.getName()).log(Level.SEVERE, null, ex);
            if (svcProvider.getTransaction() != null) {
                svcProvider.rollback();
            }
        }
        return ci;
    }

    public static ClientInfo onSearchByPhoneEvent(String phone) {
        ClientInfo ci = null;
        try {
            svcProvider.beginTransaction();
            ci = svcProvider.getClientInfoDao().loadByPhone(phone);
            svcProvider.flush();
            svcProvider.commit();

        } catch (Exception ex) {
            Logger.getLogger(SalesSystemController.class.getName()).log(Level.SEVERE, null, ex);
            if (svcProvider.getTransaction() != null) {
                svcProvider.rollback();
            }
        }
        return ci;
    }

    public static ClientInfo onClientSaveEvent(
            String firstName, String surname, String lastName,
            String mail, String phone, String address) {
        ClientInfo ci = EntityFactory.makeClientInfo(firstName, surname, lastName, mail, phone, address);

        try {
            svcProvider.beginTransaction();
            svcProvider.getClientInfoDao().save(ci);
            svcProvider.flush();
            svcProvider.commit();
        } catch (Exception ex) {
            Logger.getLogger(SalesSystemController.class.getName()).log(Level.SEVERE, null, ex);
            if (svcProvider.getTransaction() != null) {
                svcProvider.rollback();
            }
            return null;
        }
        return ci;
    }

    public static Sale onSaleSaveEvent(Client client, LocalDate saleDate, int amount, double totalPrice) {
        saleDate = saleDate == null ? LocalDate.now() : saleDate;
        Date date = Date.from(saleDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Sale sale = EntityFactory.makeSaleDto(client, date, amount, totalPrice);

        try {
            svcProvider.beginTransaction();
            svcProvider.getSaleDao().save(sale);
            svcProvider.flush();
            svcProvider.commit();
        } catch (Exception ex) {
            Logger.getLogger(SalesSystemController.class.getName()).log(Level.SEVERE, null, ex);
            if (svcProvider.getTransaction() != null) {
                svcProvider.rollback();
            }
            return null;
        }
        return sale;
    }

    public static List<ClientInfo> onLoadAllEvent() {
        List<ClientInfo> ci = new ArrayList<>();
        try {
            svcProvider.beginTransaction();
            ci = svcProvider.getClientInfoDao().loadAll();
            svcProvider.flush();
            svcProvider.commit();

        } catch (Exception ex) {
            Logger.getLogger(SalesSystemController.class.getName()).log(Level.SEVERE, null, ex);
            if (svcProvider.getTransaction() != null) {
                svcProvider.rollback();
            }
        }
        return ci;
    }
}
