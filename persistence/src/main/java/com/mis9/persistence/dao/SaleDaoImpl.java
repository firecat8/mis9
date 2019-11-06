package com.mis9.persistence.dao;

import com.mis9.dao.SaleDao;
import com.mis9.domain.Client;
import com.mis9.persistence.dto.SaleDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;

/**
 *
 * @author gdimitrova
 */
public class SaleDaoImpl extends AbstractCrudDao<SaleDto> implements SaleDao<SaleDto> {

    public SaleDaoImpl(EntityManager em) {
        super(SaleDto.class, em);
    }

    @Override
    public List<SaleDto> loadAll(Client client) {
        return getResults(SaleDto.CLIENT_ID, client);
    }

    @Override
    public List<SaleDto> loadAll(Map<String, Object> params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Map<String, Object> loadProperties(SaleDto newOne) {
        Map<String, Object> props = new HashMap<>();
        props.put(SaleDto.CLIENT_ID, newOne.getClient());
        props.put(SaleDto.SALE_DATE, newOne.getSaleDate());
        props.put(SaleDto.AMOUNT, newOne.getSoldAmount());
        props.put(SaleDto.TOTAL_PRICE, newOne.getTotalPrice());
        return props;
    }

    @Override
    public List<SaleDto> loadBefore(long time) {
        return getResults(SaleDto.SALE_DATE, time);
    }

    @Override
    public List<SaleDto> loadBetween(long from, long to) {
        return getResults(SaleDto.SALE_DATE, from, to);
    }

}
