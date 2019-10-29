package com.mis9.persistence.dto;

import com.mis9.domain.Item;
import com.mis9.domain.Size;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author gdimitrova
 */
@Entity(name = "items")
@Table(name = "items",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "item_size"})
)
public class ItemDto extends AbstractDto implements Item<ItemCategoryDto> {

    public final static String CATEGORY_COLUMN = "category_id";

    public final static String CATEGORY = "category";

    public final static String ITEM_SIZE = "item_size";

    public final static String PRICE = "price";

    @Column(length = 50)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = CATEGORY_COLUMN)
    private ItemCategoryDto category;

    @Column(length = 100)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = ITEM_SIZE)
    private Size itemSize;

    @Column(precision = 2)
    private double price;

    @Column
    private int amount;

    public ItemDto() {
        // hibernate
    }

    public ItemDto(String name, ItemCategoryDto category, String description, Size itemSize, double price, int amount) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.itemSize = itemSize;
        this.price = price;
        this.amount = amount;
        category.addItem(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String saleDescription) {
        this.description = saleDescription;
    }

    @Override
    public Size getSize() {
        return itemSize;
    }

    @Override
    public void setSize(Size size) {
        this.itemSize = size;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public ItemCategoryDto getItemCategory() {
        return category;
    }

    @Override
    public void setItemCategory(ItemCategoryDto category) {
        this.category = category;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

}
