package com.mis9.persistence.dto;

import com.mis9.domain.Client;
import com.mis9.domain.Sale;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author gdimitrova
 */
@Entity(name = "sales")
@Table(name = "sales")
public class SaleDto extends AbstractDto implements Sale {

    public static final String CLIENT_ID_COLUMN = "client_id";

    public static final String CLIENT_ID = "client";

    public static final String SALE_DATE_COLUMN = "sale_date";

    public static final String SALE_DATE = "saleDate";

    public static final String AMOUNT = "amount";

    public static final String TOTAL_PRICE_COLUMN = "total_price";

    public static final String TOTAL_PRICE = "totalPrice";

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = ClientDto.class)
    @JoinColumn(name = CLIENT_ID_COLUMN)
    private Client client;

    @Column(name = SALE_DATE_COLUMN)
    private long saleDate;

    @Column(name = AMOUNT)
    private int amount;

    @Column(name = TOTAL_PRICE_COLUMN, precision = 2)
    private double totalPrice;

    public SaleDto() {
    }

    public SaleDto(Client client, long saleDate, int amount, double totalPrice) {
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
    public long getSaleDate() {
        return saleDate;
    }

    @Override
    public void setSaleDate(long saleDate) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.client);
        hash = 79 * hash + Objects.hashCode(this.saleDate);
        hash = 79 * hash + this.amount;
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.totalPrice) ^ (Double.doubleToLongBits(this.totalPrice) >>> 32));
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
        final SaleDto other = (SaleDto) obj;
        if (this.amount != other.amount) {
            return false;
        }
        if (Double.doubleToLongBits(this.totalPrice) != Double.doubleToLongBits(other.totalPrice)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        return Objects.equals(this.saleDate, other.saleDate);
    }

    @Override
    public String toString() {
        return "SaleDto{" + "client=" + client.toString() + ", saleDate=" + saleDate + ", amount=" + amount + ", totalPrice=" + totalPrice + '}';
    }

}
