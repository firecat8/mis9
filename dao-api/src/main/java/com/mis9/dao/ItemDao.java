package com.mis9.dao;

import com.mis9.domain.Item;
import com.mis9.domain.ItemCategory;
import com.mis9.domain.Size;
import java.util.List;

/**
 *
 * @author gdimitrova
 */
public interface ItemDao<I extends Item, C extends ItemCategory> extends SearchDao<I> {

    public List<I> loadByPriceLessThan(double value);

    public List<I> loadByPriceEqual(double value);

    public List<I> load(String name,Size sizee);

    public List<I> loadByCategory(C category);
}
