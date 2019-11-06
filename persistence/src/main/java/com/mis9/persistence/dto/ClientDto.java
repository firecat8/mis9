package com.mis9.persistence.dto;

import com.mis9.domain.Client;
import java.util.Objects;
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

    public final static String FIRST_NAME = "first_name";

    public final static String SURNAME = "surname";

    public final static String LAST_NAME = "last_name";

    @Column(name = FIRST_NAME)
    private String firstName;

    @Column(name = SURNAME)
    private String surname;

    @Column(name = LAST_NAME)
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
    public void setSurname(String surname) {
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.firstName);
        hash = 71 * hash + Objects.hashCode(this.surname);
        hash = 71 * hash + Objects.hashCode(this.lastName);
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
        final ClientDto other = (ClientDto) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        return Objects.equals(this.lastName, other.lastName);
    }

    @Override
    public String toString() {
        return "ClientDto{" + "firstName=" + firstName + ", surname=" + surname + ", lastName=" + lastName + '}';
    }

}
