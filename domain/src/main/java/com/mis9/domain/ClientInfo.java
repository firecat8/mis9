package com.mis9.domain;

/**
 *
 * @author gdimitrova
 */
public interface ClientInfo {
    
    public Client getClient();

    public void setClient(Client client);
    
    public String getAddress();

    public void setAddress(String address);
    
    public String getEmail();

    public void setEmail(String mail);
    
    public String getPhone();

    public void setPhone(String phone);
}
