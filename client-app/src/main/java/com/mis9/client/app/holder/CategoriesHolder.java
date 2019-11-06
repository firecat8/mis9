package com.mis9.client.app.holder;

import com.mis9.domain.ItemCategory;

/**
 *
 * @author gdimitrova
 */
public class CategoriesHolder extends AbstractHolder<ItemCategory> {

    @Override
    public void add(ItemCategory entity) {
        entities.put(entity.getName(), entity);
    }


}
