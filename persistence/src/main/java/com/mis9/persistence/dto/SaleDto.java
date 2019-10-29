package com.mis9.persistence.dto;

import com.mis9.domain.Client;
import com.mis9.domain.Sale;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gdimitrova
 */
@Entity(name = "sales")
@Table(name = "sales")
public class SaleDto extends AbstractDto implements Sale {

    public static final String CLIENT_ID = "client_id";

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = ClientDto.class)
    @JoinColumn(name = CLIENT_ID)
    private Client client;

    @Temporal(TemporalType.TIME)
    @Column(name = "sale_date")
    private Date saleDate;

    @Column(name = "amount")
    private int amount;

    @Column(name = "total_price", precision = 2)
    private double totalPrice;

    public SaleDto() {
    }

    public SaleDto(Client client, Date saleDate, int amount, double totalPrice) {
        this.client = client;
        this.saleDate = saleDate;
        this.amount = amount;
        this.totalPrice = totalPrice;
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
    public Date getSaleDate() {
        return saleDate;
    }

    @Override
    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    @Override
    public int getSoldAmount() {
        return amount;
    }

    @Override
    public void setSoldAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
