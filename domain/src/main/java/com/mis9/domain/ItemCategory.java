package com.mis9.domain;

/**
 *
 * @author gdimitrova
 */
public interface ItemCategory extends Entity {

    public String getName();

    public void setName(String name);
    
    public ItemCategory getParentCategory();
}
