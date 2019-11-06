package com.mis9.domain;

/**
 *
 * @author gdimitrova
 */
public interface Client extends Entity {

    public String getFirstName();

    public void setFirstName(String firstName);

    public String getSurname();

    public void setSurname(String surname);

    public String getLastName();

    public void setLastName(String lastName);
}
