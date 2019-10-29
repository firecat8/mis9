package com.mis9.dao;

import com.mis9.domain.Client;
import com.mis9.domain.Sale;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gdimitrova
 */
public interface SaleDao<S extends Sale> extends CrudDao<S> {

    public List<S> loadAll(Client client);

    public List<S> loadAll(Map<String, Object> params);
}
