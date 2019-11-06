package com.mis9.domain;

/**
 *
 * @author gdimitrova
 */
public interface Sale extends Entity {

    public Client getClient();

    public void setClient(Client client);

    public long getSaleDate();

    public void setSaleDate(long saleDate);

    public int getSoldAmount();

    public void setSoldAmount(int amount);

    public double getTotalPrice();

    public void setTotalPrice(double totalPrice);

}
