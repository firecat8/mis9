package com.mis9.bl;

import com.mis9.domain.Client;
import com.mis9.domain.ClientInfo;
import com.mis9.domain.Item;
import com.mis9.domain.ItemCategory;
import com.mis9.domain.Sale;
import com.mis9.domain.Size;
import com.mis9.domain.SoldItem;
import com.mis9.persistence.dto.ClientDto;
import com.mis9.persistence.dto.ClientInfoDto;
import com.mis9.persistence.dto.ItemCategoryDto;
import com.mis9.persistence.dto.ItemDto;
import com.mis9.persistence.dto.SaleDto;
import com.mis9.persistence.dto.SoldItemDto;
import java.util.Date;

/**
 *
 * @author gdimitrova
 */
public class EntityFactory {

    public static ItemCategory makeCategory(ItemCategory s, String name) {
        ItemCategory copy = s.makeCopy();
        copy.setName(name);
        return copy;
    }

    public static ItemCategory makeCategory(String name) {
        return new ItemCategoryDto(name);
    }

    public static Item makeItem(Item s, String name, ItemCategory category, String description, Size itemSize, double price, int amount) {
        Item copy = s.makeCopy();
        copy.init(name, category, description, itemSize, price, amount);
        return copy;
    }

    public static Item makeItem(String name, ItemCategory category, String description, Size itemSize, double price, int amount) {
        return new ItemDto(name, (ItemCategoryDto) category, description, itemSize, price, amount);
    }

    public static SoldItem makeSoldItem(Sale sale, Item item, int soldAmount) {
        return new SoldItemDto(sale, item, soldAmount);
    }

    public static Sale makeSaleDto(Client client, Date saleDate, int amount, double totalPrice) {
        return new SaleDto(client, saleDate.getTime(), amount, totalPrice);
    }

    public static ClientInfo makeClientInfo(
            String firstName, String surname, String lastName,
            String mail, String phone, String address) {
        return new ClientInfoDto(makeClient(firstName, surname, lastName), mail, phone, address);
    }

    private static Client makeClient(String firstName, String surname, String lastName) {
        return new ClientDto(firstName, surname, lastName);
    }
}
