package com.mis9.domain;

import java.util.Set;

/**
 *
 * @author gdimitrova
 */
public interface ItemCategory extends Entity {

    public String getName();

    public void setName(String name);

    public Set<Item> getItems();

    public void setItems(Set<Item> items);

    public void addItem(Item item);

    public void removeItem(Item item);
    
    public ItemCategory makeCopy();

    public ItemCategory copyFrom(ItemCategory source);
}
