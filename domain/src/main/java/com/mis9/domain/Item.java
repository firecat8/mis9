package com.mis9.domain;

/**
 *
 * @author gdimitrova
 */
public interface Item extends Entity {

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

    public ItemCategory getItemCategory();

    public void setItemCategory(ItemCategory category);

    public Item makeCopy();

    public Item copyFrom(Item source);

    public void init(String name, ItemCategory category, String description, Size itemSize, double price, int amount);
}
