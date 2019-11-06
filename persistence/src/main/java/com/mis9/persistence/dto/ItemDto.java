package com.mis9.persistence.dto;

import com.mis9.domain.Item;
import com.mis9.domain.ItemCategory;
import com.mis9.domain.Size;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class ItemDto extends AbstractDto implements Item {

    public final static String CATEGORY_COLUMN = "category_id";

    public final static String NAME = "name";

    public final static String DESCRIPTION = "description";

    public final static String AMOUNT = "amount";

    public final static String CATEGORY = "category";

    public final static String ITEM_SIZE = "item_size";

    public final static String PRICE = "price";

    //properties
    public final static String ITEM_SIZE_PROP = "itemSize";

    @Column(length = 50, name = NAME)
    private String name;

    @ManyToOne(targetEntity = ItemCategoryDto.class)
    @JoinColumn(name = CATEGORY_COLUMN)
    private ItemCategory category;

    @Column(length = 100)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = ITEM_SIZE)
    private Size itemSize;

    @Column(precision = 2, name = PRICE)
    private double price;

    @Column(name = AMOUNT)
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
    public ItemCategory getItemCategory() {
        return category;
    }

    @Override
    public void setItemCategory(ItemCategory category) {
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.category);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.itemSize);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 67 * hash + this.amount;
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
        final ItemDto other = (ItemDto) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (this.amount != other.amount) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        return this.itemSize == other.itemSize;
    }

    @Override
    public String toString() {
        return "ItemDto{" + "name=" + name + ", category=" + category.toString() + ", description=" + description + ", itemSize=" + itemSize + ", price=" + price + ", amount=" + amount + '}';
    }

    @Override
    public Item copyFrom(Item s) {
        id = s.getId();
        name = s.getName();
        category = s.getItemCategory();
        description = s.getDescription();
        itemSize = s.getSize();
        price = s.getPrice();
        amount = s.getAmount();
        return this;
    }

    @Override
    public Item makeCopy() {
        ItemDto copy = new ItemDto();
        copy.copyFrom(this);
        return copy;
    }
    
    @Override
    public void init(String name, ItemCategory category, String description, Size itemSize, double price, int amount) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.itemSize = itemSize;
        this.price = price;
        this.amount = amount;
    }

}
