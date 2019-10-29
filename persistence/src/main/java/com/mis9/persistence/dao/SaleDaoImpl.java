package com.mis9.persistence.dao;

import com.mis9.dao.SaleDao;
import com.mis9.domain.Client;
import com.mis9.persistence.dto.SaleDto;
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

}
