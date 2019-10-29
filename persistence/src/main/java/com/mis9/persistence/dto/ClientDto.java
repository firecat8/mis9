package com.mis9.persistence.dto;

import com.mis9.domain.Client;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author gdimitrova
 */
@Entity(name = "clients")
@Table(name = "clients")
public class ClientDto extends AbstractDto implements Client {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "last_name")
    private String lastName;

    public ClientDto() {
        // Hibernate
    }

    public ClientDto(String firstName, String surname, String lastName) {
        this.firstName = firstName;
        this.surname = surname;
        this.lastName = lastName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public void setgetSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
