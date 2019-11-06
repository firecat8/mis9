package com.mis9.persistence;

import com.mis9.persistence.dao.ItemCategoryDaoImpl;
import com.mis9.persistence.dto.ItemCategoryDto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gdimitrova
 */
public class ItemCategoryDaoTestCase extends AbstractSearchDaoTestCase<ItemCategoryDto, ItemCategoryDaoImpl> {

    @Override
    protected String getEntityName() {
        return createEntity().getName();
    }

    @Override
    protected ItemCategoryDto createEntity() {
        return createDefault();
    }

    @Override
    protected List<ItemCategoryDto> createEntities() {
        return createCategories();
    }

    @Override
    protected ItemCategoryDaoImpl getTestDao() {
        return (ItemCategoryDaoImpl) registry.getItemCategoryDao();
    }

    public static ItemCategoryDto createDefault() {
        return createEntity("default");
    }

    private static ItemCategoryDto createEntity(String name) {
        return new ItemCategoryDto(name);
    }

    public static List<ItemCategoryDto> createCategories() {
        List<ItemCategoryDto> categories = new ArrayList<>();
        categories.add(createEntity("Clothes"));
        categories.add(createEntity("T-shirts"));
        categories.add(createEntity("Sweaters"));
        return categories;
    }

    @Override
    protected void prepareDbData() {
    }

}
