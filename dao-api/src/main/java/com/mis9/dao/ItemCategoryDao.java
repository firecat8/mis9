package com.mis9.dao;

import com.mis9.domain.ItemCategory;
import java.util.List;

/**
 *
 * @author gdimitrova
 */
public interface ItemCategoryDao<I extends ItemCategory> extends SearchDao<I> {

    public List<I> loadAllSubcategories(ItemCategory category);
}
