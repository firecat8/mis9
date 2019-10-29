package com.mis9.persistence;

import com.mis9.domain.Size;
import com.mis9.persistence.dao.ItemDaoImpl;
import com.mis9.persistence.dto.ItemCategoryDto;
import com.mis9.persistence.dto.ItemDto;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author gdimitrova
 */
public class ItemDaoTestCase extends AbstractSearchDaoTestCase<ItemDto, ItemDaoImpl> {

    private static ItemCategoryDto category;

    private static List<ItemCategoryDto> categories;

    public void testLoadByPriceLessThan() {
        testLoadByPrice(dao::loadByPriceLessThan, 10, 2);
    }

    public void testLoadByPriceEqual() {
        testLoadByPrice(dao::loadByPriceEqual, 10, 1);
    }

    public void testLoadByCategory() {
        List<ItemDto> actual = new ArrayList<>();
        try {
            beginTransaction();
            List<ItemDto> itemDtos = createEntities();
            actual = dao.loadByCategory(itemDtos.get(0).getItemCategory());
            commit();
        } catch (Exception e) {
            System.err.println("\n Couldn't load by category \n" + e.getMessage() + "\n");
            if (getTransaction() != null) {
                rollback();
            }
        }
        assertEquals("Expected 2, actual " + actual.size(), 2, actual.size());
    }

    @Override
    protected String getEntityName() {
        return createEntity().getName();
    }

    @Override
    protected ItemDto createEntity() {
        return createDefault();
    }

    @Override
    protected List<ItemDto> createEntities() {
        return createItems();
    }

    @Override
    protected ItemDaoImpl getTestDao() {
        return (ItemDaoImpl) registry.getItemDao();
    }

    public static ItemDto createDefault() {
        return createEntity("default", category, Size.NONE, 1, 1);
    }

    public static List<ItemDto> createItems() {
        List<ItemDto> items = new ArrayList<>();
        items.add(createEntity("Blue one T-shirts", categories.get(1), Size.S, 10, 2));
        items.add(createEntity("Blue one T-shirts", categories.get(1), Size.XL, 5, 1));
        items.add(createEntity("Red one sweater", categories.get(2), Size.XL, 5, 3));
        return items;
    }

    private static ItemDto createEntity(String name, ItemCategoryDto category, Size size, double price, int amount) {
        return new ItemDto(name,
                category,
                name, size, price, amount);
    }

    private void testLoadByPrice(Function<Double, List<ItemDto>> method, double price, int expectedValue) {
        List<ItemDto> actual = new ArrayList<>();
        try {
            beginTransaction();
            List<ItemDto> itemDtos = createEntities();
            dao.saveAll(itemDtos);
            actual = method.apply(price);
            commit();
        } catch (Exception e) {
            System.err.println("\n Couldn't load by price \n" + e.getMessage() + "\n");
            if (getTransaction() != null) {
                rollback();
            }
        }
        assertEquals("Expected " + expectedValue + ", actual " + actual.size(), expectedValue, actual.size());
    }

    @Override
    protected void prepareDbData() {
        try {
            beginTransaction();
            prepareData();
            commit();
        } catch (Exception e) {
            System.err.println("\n Couldn't prepared data \n" + e.getMessage() + "\n");
            if (getTransaction() != null) {
                rollback();
            }
        }
    }

    public static void prepareData() {
        category = ItemCategoryDaoTestCase.createDefault();
        registry.getItemCategoryDao().save(category);
        categories = ItemCategoryDaoTestCase.createCategories();
        registry.getItemCategoryDao().saveAll(categories);
    }

}
