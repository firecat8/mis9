package com.mis9.persistence.dto;

import com.mis9.domain.Client;
import com.mis9.domain.ClientInfo;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author gdimitrova
 */
@Entity(name = "client_info")
@Table(name = "client_info")
public class ClientInfoDto extends AbstractDto implements ClientInfo {

    public static final String CLIENT_ID = "client_id";

    @OneToOne(cascade = CascadeType.ALL, targetEntity = ClientDto.class)
    @JoinColumn(name = CLIENT_ID)
    private Client client;

    @Column(length = 50)
    private String address;

    @Column(unique = true)
    @EmailPattern
    private String email;

    @Column(unique = true, length = 10)
    private String phone;

    public ClientInfoDto() {
        // Hibernate
    }

    public ClientInfoDto(Client client, String address, String email, String phone) {
        this.client = client;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
