package com.mis9.persistence.dto;

import com.mis9.domain.Item;
import com.mis9.domain.Sale;
import com.mis9.domain.SoldItem;
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
@Entity(name = "sold_items")
@Table(name = "sold_items")
public class SoldItemDto extends AbstractDto implements SoldItem {

    public static final String SALE_ID_COLUMN = "sale_id";

    public static final String ITEM_ID_COLUMN = "item_id";

    public static final String SOLD_AMOUNT_COLUMN = "sold_amount";

    public static final String SALE_ID = "sale";

    public static final String ITEM_ID = "item";

    public static final String SOLD_AMOUNT = "soldAmount";

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = SaleDto.class)
    @JoinColumn(name = SALE_ID_COLUMN)
    private Sale sale;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = ItemDto.class)
    @JoinColumn(name = ITEM_ID_COLUMN)
    private Item item;

    @Column(name = SOLD_AMOUNT_COLUMN)
    private int soldAmount;

    public SoldItemDto() {
    }

    public SoldItemDto(Sale sale, Item item, int soldAmount) {
        this.sale = sale;
        this.item = item;
        this.soldAmount = soldAmount;
    }

    @Override
    public Sale getSale() {
        return sale;
    }

    @Override
    public void setSale(Sale sale) {
        this.sale = sale;
    }

    @Override
    public Item getItem() {
        return item;
    }

    @Override
    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public int getSoldAmount() {
        return soldAmount;
    }

    @Override
    public void setSoldAmount(int soldAmount) {
        this.soldAmount = soldAmount;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.sale);
        hash = 41 * hash + Objects.hashCode(this.item);
        hash = 41 * hash + this.soldAmount;
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
        final SoldItemDto other = (SoldItemDto) obj;
        if (this.soldAmount != other.soldAmount) {
            return false;
        }
        if (!Objects.equals(this.sale, other.sale)) {
            return false;
        }
        return Objects.equals(this.item, other.item);
    }

    @Override
    public String toString() {
        return "SoldItemDto{" + "sale=" + sale.toString() + ", item=" + item.toString() + ", soldAmount=" + soldAmount + '}';
    }

}
