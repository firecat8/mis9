package com.mis9.persistence;

import com.mis9.domain.Client;
import com.mis9.persistence.dao.ClientInfoDaoImpl;
import com.mis9.persistence.dto.ClientDto;
import com.mis9.persistence.dto.ClientInfoDto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gdimitrova
 */
public class ClientInfoDaoTestCase extends AbstractCrudDaoTestCase<ClientInfoDto, ClientInfoDaoImpl> {

    private static ClientDto client;

    private static List<ClientDto> clients;

    @Override
    protected ClientInfoDto createEntity() {
        return createDefault();
    }

    @Override
    protected List<ClientInfoDto> createEntities() {
        return createClientsInfo();
    }

    @Override
    protected ClientInfoDaoImpl getTestDao() {
        return (ClientInfoDaoImpl) registry.getClientInfoDao();
    }

    public static ClientInfoDto createDefault() {
        return createEntity(client, "default", "", "1111");
    }

    public static List<ClientInfoDto> createClientsInfo() {
        List<ClientInfoDto> clientInfos = new ArrayList<>();
        clientInfos.add(createEntity(clients.get(0), "bul.A", "a@ra.bg", "84857"));
        clientInfos.add(createEntity(clients.get(1), "bul.B", "b@ra.bg", "848567"));
        return clientInfos;
    }

    private static ClientInfoDto createEntity(Client client, String address, String mail, String phone) {
        return new ClientInfoDto(client, address, mail, phone);
    }

    @Override
    protected void prepareDbData() {
        try {
            beginTransaction();
            prepareData();
            commit();
        } catch (Exception e) {
            System.err.println("\n Couldn't prepared data \n" + e.getMessage() + "\n");
            if (getTransaction() != null) {
                rollback();
            }
        }
    }

    public static void prepareData() {
        client = ClientDaoTestCase.createDefault();
        registry.getClientDao().save(client);
        clients = ClientDaoTestCase.createClients();
        registry.getClientDao().saveAll(clients);
    }

}
