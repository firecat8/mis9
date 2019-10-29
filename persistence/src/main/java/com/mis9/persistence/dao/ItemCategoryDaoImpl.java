package com.mis9.persistence.dao;

import com.mis9.dao.ItemCategoryDao;
import com.mis9.domain.ItemCategory;
import com.mis9.persistence.dto.ItemCategoryDto;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author gdimitrova
 */
public class ItemCategoryDaoImpl extends AbstractSearchDao<ItemCategoryDto> implements ItemCategoryDao<ItemCategoryDto> {

    public ItemCategoryDaoImpl(EntityManager em) {
        super(ItemCategoryDto.class, em);
    }

    @Override
    public List<ItemCategoryDto> loadAllSubcategories(ItemCategory category) {
        return getResults(ItemCategoryDto.PARENT_CATEGORY, category);
    }
}
