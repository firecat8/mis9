package com.mis9.persistence.dao;

import com.mis9.dao.ItemDao;
import com.mis9.domain.ItemCategory;
import com.mis9.domain.Size;
import com.mis9.persistence.dto.ItemDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;

/**
 *
 * @author gdimitrova
 */
public class ItemDaoImpl extends AbstractSearchDao<ItemDto> implements ItemDao<ItemDto, ItemCategory> {

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

    @Override
    protected Map<String, Object> loadProperties(ItemDto newOne) {
        Map<String, Object> props = new HashMap<>();
        props.put(ItemDto.NAME, newOne.getName());
        props.put(ItemDto.DESCRIPTION, newOne.getDescription());
        props.put(ItemDto.PRICE, newOne.getPrice());
        props.put(ItemDto.AMOUNT, newOne.getAmount());
        props.put(ItemDto.ITEM_SIZE_PROP, newOne.getSize());
        props.put(ItemDto.CATEGORY, newOne.getItemCategory());
        return props;
    }

    @Override
    public List<ItemDto> load(String name, Size size) {
        Map<String, Object> props = new HashMap<>();
        props.put(ItemDto.NAME, name);
        props.put(ItemDto.ITEM_SIZE_PROP, size);
        return getResults(props);
    }

}
