package com.mis9.dao;

import com.mis9.domain.Item;
import com.mis9.domain.Sale;
import com.mis9.domain.SoldItem;
import java.util.List;

/**
 *
 * @author gdimitrova
 */
public interface SoldItemDao<S extends SoldItem> extends CrudDao<S> {

    public List<S> loadAll(Item item);

    public List<S> loadAll(Sale sale);
}
