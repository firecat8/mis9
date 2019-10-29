package com.mis9.persistence.dao;

import com.mis9.dao.SoldItemDao;
import com.mis9.domain.Item;
import com.mis9.domain.Sale;
import com.mis9.persistence.dto.SoldItemDto;
import java.util.List;
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

}
