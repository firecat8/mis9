package com.mis9.domain;

/**
 *
 * @author gdimitrova
 */
public interface Item<IC extends ItemCategory> extends Entity {

    public String getName();

    public void setName(String name);

    public String getDescription();

    public void setDescription(String saleDescription);

    public Size getSize();

    public void setSize(Size size);

    public double getPrice();

    public void setPrice(double price);

    public int getAmount();

    public void setAmount(int amount);

    public IC getItemCategory();

    public void setItemCategory(IC category);
}
