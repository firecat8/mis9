package com.mis9.persistence.dao;

import com.mis9.dao.ItemDao;
import com.mis9.domain.ItemCategory;
import com.mis9.persistence.dto.ItemDto;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author gdimitrova
 */
public class ItemDaoImpl extends AbstractSearchDao<ItemDto> implements ItemDao<ItemDto> {

    public ItemDaoImpl(EntityManager em) {
        super(ItemDto.class, em);
    }

    @Override
    public List<ItemDto> loadByPriceLessThan(double value) {
        return getResults(ItemDto.PRICE, value, ComparisonSign.LESS_THAN);
    }

    @Override
    public List<ItemDto> loadByPriceEqual(double value) {
        return getResults(ItemDto.PRICE, value, ComparisonSign.EQUAL);
    }

    @Override
    public List<ItemDto> loadByCategory(ItemCategory category) {
        return getResults(ItemDto.CATEGORY, category);
    }

}
