package com.mis9.persistence.dao;

import com.mis9.dao.ClientDao;
import com.mis9.persistence.dto.ClientDto;
import javax.persistence.EntityManager;

/**
 *
 * @author gdimitrova
 */
public class ClientDaoImpl extends AbstractCrudDao<ClientDto> implements ClientDao<ClientDto>{

    public ClientDaoImpl(EntityManager em) {
        super(ClientDto.class, em);
    }
    
}
