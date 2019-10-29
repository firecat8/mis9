package com.mis9.persistence.dto;

import com.mis9.domain.ItemCategory;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    public final static String PARENT_CATEGORY_COLUMN = "parent_id";

    public final static String PARENT_CATEGORY = "parentCategory";

    @Column(unique = true, length = 50)
    private String name;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = PARENT_CATEGORY_COLUMN)
    private ItemCategoryDto parentCategory;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "category")
    private Set<ItemDto> items = new HashSet<>();

    public ItemCategoryDto() {
        // hibernate
    }

    public ItemCategoryDto(String name, ItemCategoryDto parentCategory) {
        this.name = name;
        this.parentCategory = parentCategory;
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
    public ItemCategoryDto getParentCategory() {
        return parentCategory;
    }

    public Set<ItemDto> getItems() {
        return items;
    }

    public void setItems(Set<ItemDto> items) {
        this.items = items;
    }

    public void addItem(ItemDto p) {
        this.items.add(p);
    }
}
