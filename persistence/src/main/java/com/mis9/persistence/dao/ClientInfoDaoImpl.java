package com.mis9.persistence.dao;

import com.mis9.dao.ClientInfoDao;
import com.mis9.domain.Client;
import com.mis9.persistence.dto.ClientInfoDto;
import javax.persistence.EntityManager;

/**
 *
 * @author gdimitrova
 */
public class ClientInfoDaoImpl extends AbstractCrudDao<ClientInfoDto> implements ClientInfoDao<ClientInfoDto> {

    public ClientInfoDaoImpl(EntityManager em) {
        super(ClientInfoDto.class, em);
    }

    @Override
    public ClientInfoDto loadClientInfo(Client client) {
        return getResult(ClientInfoDto.CLIENT_ID, client);
    }

}
