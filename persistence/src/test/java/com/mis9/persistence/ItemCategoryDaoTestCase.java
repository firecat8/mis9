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

    public void testLoadAllSubcategories() {
        List<ItemCategoryDto> actual = new ArrayList<>();
        try {
            beginTransaction();
            List<ItemCategoryDto> categoryDtos = createCategories();
            dao.saveAll(categoryDtos);
            actual = dao.loadAllSubcategories(categoryDtos.get(0));
            commit();
        } catch (Exception e) {
            System.err.println("\n Couldn't load all subCategories \n" + e.getMessage() + "\n");
            if (getTransaction() != null) {
                rollback();
            }
        }
        assertEquals("Expected 2, actual" + actual.size(), actual.size(), 2);
    }

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
        return createEntity("default", null);
    }

    private static ItemCategoryDto createEntity(String name, ItemCategoryDto category) {
        return new ItemCategoryDto(name, category);
    }

    public static List<ItemCategoryDto> createCategories() {
        List<ItemCategoryDto> categories = new ArrayList<>();
        categories.add(createEntity("Clothes", null));
        categories.add(createEntity("T-shirts", categories.get(0)));
        categories.add(createEntity("Sweaters", categories.get(0)));
        return categories;
    }

    @Override
    protected void prepareDbData() {
    }

}
