package com.mis9.domain;

import java.util.Date;

/**
 *
 * @author gdimitrova
 */
public interface Sale extends Entity {

    public Client getClient();

    public void setClient(Client client);

    public Date getSaleDate();

    public void setSaleDate(Date saleDate);

    public int getSoldAmount();

    public void setSoldAmount(int amount);

    public double getTotalPrice();

    public void setTotalPrice(double totalPrice);

}
