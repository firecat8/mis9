package com.mis9.persistence.dao;

import com.mis9.dao.SoldItemDao;
import com.mis9.domain.Item;
import com.mis9.domain.Sale;
import com.mis9.persistence.dto.SoldItemDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;

/**
 *
 * @author gdimitrova
 */
public class SoldItemDaoImpl extends AbstractCrudDao<SoldItemDto> implements SoldItemDao<SoldItemDto> {

    public SoldItemDaoImpl(EntityManager em) {
        super(SoldItemDto.class, em);
    }

    @Override
    public List<SoldItemDto> loadAll(Item item) {
        return getResults(SoldItemDto.ITEM_ID, item);
    }

    @Override
    public List<SoldItemDto> loadAll(Sale sale) {
        return getResults(SoldItemDto.SALE_ID, sale);
    }

    @Override
    protected Map<String, Object> loadProperties(SoldItemDto newOne) {
        Map<String, Object> props = new HashMap<>();
        props.put(SoldItemDto.ITEM_ID, newOne.getItem());
        props.put(SoldItemDto.SALE_ID, newOne.getSale());
        props.put(SoldItemDto.SOLD_AMOUNT, newOne.getSoldAmount());
        return props;
    }

}
