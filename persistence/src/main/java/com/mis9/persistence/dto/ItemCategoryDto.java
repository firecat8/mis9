package com.mis9.persistence.dto;

import com.mis9.domain.Item;
import com.mis9.domain.ItemCategory;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author gdimitrova
 */
@Entity(name = "item_categories")
@Table(name = "item_categories")
public class ItemCategoryDto extends AbstractDto implements ItemCategory {

    public final static String NAME = "name";

    @Column(unique = true, length = 50)
    private String name;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, targetEntity = ItemDto.class, mappedBy = "category")
    private Set<Item> items = new HashSet<>();

    public ItemCategoryDto() {
        // hibernate
    }

    public ItemCategoryDto(String name) {
        this.name = name;
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
    public Set<Item> getItems() {
        return items;
    }

    @Override
    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public void addItem(Item p) {
        this.items.add(p);
    }

    @Override
    public void removeItem(Item item) {
        items.remove(item);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.name);
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
        final ItemCategoryDto other = (ItemCategoryDto) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return "ItemCategoryDto{" + "name=" + name + '}';
    }

    @Override
    public ItemCategory makeCopy() {
        ItemCategoryDto copy = new ItemCategoryDto();
        copy.copyFrom(this);
        return copy;
    }

    @Override
    public ItemCategory copyFrom(ItemCategory source) {
        id = source.getId();
        name = source.getName();
        items.clear();
        items.addAll(source.getItems());
        return this;
    }

}
