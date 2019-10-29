package com.mis9.persistence.dto;

import com.mis9.domain.Item;
import com.mis9.domain.Sale;
import com.mis9.domain.SoldItem;
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

    public static final String SALE_ID = "sale_id";

    public static final String ITEM_ID = "item_id";

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = SaleDto.class)
    @JoinColumn(name = SALE_ID)
    private Sale sale;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = ItemDto.class)
    @JoinColumn(name = ITEM_ID)
    private Item item;

    @Column(name = "sold_amount")
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

}
