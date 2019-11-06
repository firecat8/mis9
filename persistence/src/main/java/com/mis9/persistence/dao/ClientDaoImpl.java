package com.mis9.persistence.dao;

import com.mis9.dao.ClientDao;
import com.mis9.persistence.dto.ClientDto;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;

/**
 *
 * @author gdimitrova
 */
public class ClientDaoImpl extends AbstractCrudDao<ClientDto> implements ClientDao<ClientDto> {

    public ClientDaoImpl(EntityManager em) {
        super(ClientDto.class, em);
    }

    @Override
    protected Map<String, Object> loadProperties(ClientDto newOne) {
        Map<String, Object> props = new HashMap<>();
        props.put(ClientDto.FIRST_NAME, newOne.getFirstName());
        props.put(ClientDto.SURNAME, newOne.getSurname());
        props.put(ClientDto.LAST_NAME, newOne.getLastName());
        return props;
    }

}
