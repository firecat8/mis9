package com.mis9.dao;

import com.mis9.domain.Item;
import com.mis9.domain.ItemCategory;
import java.util.List;

/**
 *
 * @author gdimitrova
 */
public interface ItemDao<I extends Item> extends CrudDao<I> {

    public List<I> loadByPriceLessThan(double value);

    public List<I> loadByPriceEqual(double value);

    public List<I> loadByCategory(ItemCategory category);
}
