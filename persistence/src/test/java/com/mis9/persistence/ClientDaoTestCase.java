package com.mis9.persistence;

import com.mis9.persistence.dao.ClientDaoImpl;
import com.mis9.persistence.dto.ClientDto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gdimitrova
 */
public class ClientDaoTestCase extends AbstractCrudDaoTestCase<ClientDto, ClientDaoImpl> {

    @Override
    protected ClientDto createEntity() {
        return createDefault();
    }

    @Override
    protected List<ClientDto> createEntities() {
        return createClients();
    }

    @Override
    protected ClientDaoImpl getTestDao() {
        return (ClientDaoImpl) registry.getClientDao();
    }

    public static ClientDto createDefault() {
        return createEntity("default");
    }

    private static ClientDto createEntity(String name) {
        return new ClientDto(name, name, name);
    }

    public static List<ClientDto> createClients() {
        List<ClientDto> clients = new ArrayList<>();
        clients.add(createEntity("Smith"));
        clients.add(createEntity("Alex"));
        return clients;
    }

    @Override
    protected void prepareDbData() {
    }
}
