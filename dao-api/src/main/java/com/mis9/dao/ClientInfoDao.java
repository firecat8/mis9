package com.mis9.dao;

import com.mis9.domain.Client;
import com.mis9.domain.ClientInfo;

/**
 *
 * @author gdimitrova
 */
public interface ClientInfoDao<C extends ClientInfo> extends CrudDao<C> {

    public C loadClientInfo(Client client);
}
