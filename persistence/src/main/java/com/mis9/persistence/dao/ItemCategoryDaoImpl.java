package com.mis9.persistence.dao;

import com.mis9.dao.ItemCategoryDao;
import com.mis9.persistence.dto.ItemCategoryDto;
import java.util.HashMap;
import java.util.Map;
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
    protected Map<String, Object> loadProperties(ItemCategoryDto newOne) {
        Map<String, Object> props = new HashMap<>();
        props.put(ItemCategoryDto.NAME, newOne.getName());
        return props;
    }

}
