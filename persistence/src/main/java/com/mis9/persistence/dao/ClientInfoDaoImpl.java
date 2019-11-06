package com.mis9.persistence.dao;

import com.mis9.dao.ClientInfoDao;
import com.mis9.domain.Client;
import com.mis9.persistence.dto.ClientInfoDto;
import java.util.HashMap;
import java.util.Map;
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

    @Override
    protected Map<String, Object> loadProperties(ClientInfoDto newOne) {
        Map<String, Object> props = new HashMap<>();
        props.put(ClientInfoDto.CLIENT_ID, newOne.getClient());
        props.put(ClientInfoDto.ADDRESS, newOne.getAddress());
        props.put(ClientInfoDto.EMAIL, newOne.getEmail());
        props.put(ClientInfoDto.PHONE, newOne.getPhone());
        return props;
    }

    @Override
    public ClientInfoDto loadByEmail(String email) {
        return getResult(ClientInfoDto.EMAIL, email);
    }

    @Override
    public ClientInfoDto loadByPhone(String phone) {
        return getResult(ClientInfoDto.PHONE, phone);
    }

}
