package com.mis9.domain;

/**
 *
 * @author gdimitrova
 */
public interface SoldItem {

    public Sale getSale();

    public void setSale(Sale sale);

    public Item getItem();

    public void setItem(Item item);

    public int getSoldAmount();

    public void setSoldAmount(int amount);
}
