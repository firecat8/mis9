/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mis9.client.app.holder;

import static com.mis9.client.app.holder.EventHandler.svcProvider;
import com.mis9.domain.Sale;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gdimitrova
 */
public class SalesEventHandler extends EventHandler {

    public static List<Sale> onLoadAllSalesBeforeEvent(LocalDate ld) {
        Long date = convert(ld);
        List<Sale> sales = new ArrayList<>();
        try {
            svcProvider.beginTransaction();
            sales = svcProvider.getSaleDao().loadBefore(date);
            svcProvider.flush();
            svcProvider.commit();

        } catch (Exception ex) {
            Logger.getLogger(ItemsEventHandler.class.getName()).log(Level.SEVERE, null, ex);
            if (svcProvider.getTransaction() != null) {
                svcProvider.rollback();
            }
        }
        return sales;
    }

    public static List<Sale> onLoadAllSalesBetweenEvent(LocalDate from, LocalDate to) {
        Long fromDate = convert(from);
        Long toDate = convert(to);
        List<Sale> sales = new ArrayList<>();
        try {
            svcProvider.beginTransaction();
            sales = svcProvider.getSaleDao().loadBetween(fromDate, toDate);
            svcProvider.flush();
            svcProvider.commit();

        } catch (Exception ex) {
            Logger.getLogger(ItemsEventHandler.class.getName()).log(Level.SEVERE, null, ex);
            if (svcProvider.getTransaction() != null) {
                svcProvider.rollback();
            }
        }
        return sales;
    }

    private static Long convert(LocalDate ld) {
        ld = ld == null ? LocalDate.now() : ld;
        return Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()).getTime();
    }
}
