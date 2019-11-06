package com.mis9.persistence.dto;

import com.mis9.domain.Client;
import com.mis9.domain.ClientInfo;
import java.util.Objects;
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

    public static final String ADDRESS = "address";

    public static final String EMAIL = "email";

    public static final String PHONE = "phone";

    @OneToOne(cascade = CascadeType.ALL, targetEntity = ClientDto.class)
    @JoinColumn(name = CLIENT_ID)
    private Client client;

    @Column(length = 50, name = ADDRESS)
    private String address;

    @Column(unique = true, name = EMAIL)
    @EmailPattern
    private String email;

    @Column(unique = true, length = 10, name = PHONE)
    private String phone;

    public ClientInfoDto() {
        // Hibernate
    }

    public ClientInfoDto(Client client, String email, String phone, String address) {
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.client);
        hash = 61 * hash + Objects.hashCode(this.address);
        hash = 61 * hash + Objects.hashCode(this.email);
        hash = 61 * hash + Objects.hashCode(this.phone);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClientInfoDto other = (ClientInfoDto) obj;
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        return this.client.equals(other.client);
    }

    @Override
    public String toString() {
        return "ClientInfoDto{" + "client=" + client.toString() + ", address=" + address + ", email=" + email + ", phone=" + phone + '}';
    }

}
