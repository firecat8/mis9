/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mis9.client.app.utils;

import com.mis9.domain.Client;
import com.mis9.domain.ClientInfo;
import com.mis9.domain.Item;
import com.mis9.domain.Sale;
import com.mis9.domain.Size;
import com.mis9.domain.SoldItem;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 *
 * @author gdimitrova
 */
public class TableViewUtils {

    public static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public static void initProductsTable(
            TableView<Item> table,
            Consumer<Item> m,
            Consumer<Item> del,
            TableColumn<Item, String> name,
            TableColumn<Item, String> description,
            TableColumn<Item, String> category,
            TableColumn<Item, Size> size,
            TableColumn<Item, Double> price,
            TableColumn<Item, Integer> amount) {

        table.setRowFactory(tv -> {
            TableRow<Item> row = new TableRow<>();
            ContextMenu menu = new ContextMenu();

            MenuItem modify = new MenuItem("Modify");
            modify.setOnAction(e -> m.accept(row.getItem()));
            MenuItem delete = new MenuItem("Delete");
            delete.setOnAction(e -> del.accept(row.getItem()));

            menu.getItems().addAll(modify, delete);

            row.emptyProperty().addListener((obs, wasEmpty, isNowEmpty)
                    -> row.setContextMenu(isNowEmpty ? null : menu));

            return row;
        });
        // Columns
        setPropertyValueFactory(name, category, size, price, amount);
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

    }

    public static void initSalesBeforeDateTable(TableView<Sale> table, Consumer<Sale> see, TableColumn<Sale, Sale> saleColumn) {
        table.setRowFactory(tv -> {
            TableRow<Sale> row = new TableRow<>();
            ContextMenu menu = new ContextMenu();

            MenuItem seetMenu = new MenuItem("See items");
            seetMenu.setOnAction(e -> see.accept(row.getItem()));

            menu.getItems().addAll(seetMenu);

            row.emptyProperty().addListener((obs, wasEmpty, isNowEmpty)
                    -> row.setContextMenu(isNowEmpty ? null : menu));

            return row;
        });
        TableColumn<Sale, String> firstNameColumn = new TableColumn<Sale, String>("First name");
        firstNameColumn.setCellValueFactory(makeSaleCellFactory((s) -> {
            return s.getClient().getFirstName();
        }));
        TableColumn<Sale, String> surnameColumn = new TableColumn<Sale, String>("Surname");
        surnameColumn.setCellValueFactory(makeSaleCellFactory((s) -> {
            return s.getClient().getSurname();
        }));
        TableColumn<Sale, String> lastNameColumn = new TableColumn<Sale, String>("Last name");
        lastNameColumn.setCellValueFactory(makeSaleCellFactory((s) -> {
            return s.getClient().getLastName();
        }));
        //
        TableColumn<Sale, Long> saleDateColumn = new TableColumn<Sale, Long>("Sale date");
        saleDateColumn.setCellValueFactory(makeSaleCellFactory((s) -> {
            return format.format(new Date(s.getSaleDate()));
        }));
        TableColumn<Sale, Client> clientColumn = new TableColumn<Sale, Client>("Client");
        clientColumn.getColumns().addAll(firstNameColumn, surnameColumn, lastNameColumn);
        //
        saleColumn.getColumns().addAll(saleDateColumn, clientColumn);
    }

    public static void initClientsTable(TableView<ClientInfo> table,
            Consumer<ClientInfo> select, TableColumn<ClientInfo, String> firstName, TableColumn<ClientInfo, String> surname,
            TableColumn<ClientInfo, String> lastName, TableColumn<ClientInfo, String> mail, TableColumn<ClientInfo, String> phone,
            TableColumn<ClientInfo, String> address) {

        table.setRowFactory(tv -> {
            TableRow<ClientInfo> row = new TableRow<>();
            ContextMenu menu = new ContextMenu();

            MenuItem selectMenu = new MenuItem("Select");
            selectMenu.setOnAction(e -> select.accept(row.getItem()));

            menu.getItems().addAll(selectMenu);

            row.emptyProperty().addListener((obs, wasEmpty, isNowEmpty)
                    -> row.setContextMenu(isNowEmpty ? null : menu));

            return row;
        });
        firstName.setCellValueFactory(makeClientInfoCellFactory((ci) -> {
            return ci.getClient().getFirstName();
        }));
        surname.setCellValueFactory(makeClientInfoCellFactory((ci) -> {
            return ci.getClient().getSurname();
        }));
        lastName.setCellValueFactory(makeClientInfoCellFactory((ci) -> {
            return ci.getClient().getLastName();
        }));
        mail.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    public static <E extends Object> void refreshTable(TableView table, Collection<E> newRows) {
        table.getItems().clear();
        table.getItems().addAll(newRows);
    }

    private static Callback makeCellFactory(Function<Item, String> convert) {
        return new Callback<CellDataFeatures<Item, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Item, String> data) {
                return new SimpleStringProperty(convert.apply(data.getValue()));
            }
        };
    }

    private static Callback makeClientInfoCellFactory(Function<ClientInfo, String> convert) {
        return new Callback<CellDataFeatures<ClientInfo, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<ClientInfo, String> data) {
                return new SimpleStringProperty(convert.apply(data.getValue()));
            }
        };
    }

    private static Callback makeSaleCellFactory(Function<Sale, String> convert) {
        return new Callback<CellDataFeatures<Sale, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Sale, String> data) {
                return new SimpleStringProperty(convert.apply(data.getValue()));
            }
        };
    }

    private static Callback makeSoldItemCellFactory(Function<SoldItem, String> convert) {
        return new Callback<CellDataFeatures<SoldItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<SoldItem, String> data) {
                return new SimpleStringProperty(convert.apply(data.getValue()));
            }
        };
    }

    public static void initSoldItemsTable(
            TableView<SoldItem> soldItemsTable,
            TableColumn<SoldItem, String> nameI, TableColumn<SoldItem, String> descriptionI,
            TableColumn<SoldItem, String> categoryI, TableColumn<SoldItem, Size> sizeI,
            TableColumn<SoldItem, Double> priceI, TableColumn<SoldItem, Integer> amountI) {
        nameI.setCellValueFactory(makeSoldItemCellFactory((s) -> {
            return s.getItem().getName();
        }));
        descriptionI.setCellValueFactory(makeSoldItemCellFactory((s) -> {
            return s.getItem().getDescription();
        }));
        categoryI.setCellValueFactory(makeSoldItemCellFactory((s) -> {
            return s.getItem().getItemCategory().getName();
        }));
        sizeI.setCellValueFactory(makeSoldItemCellFactory((s) -> {
            return s.getItem().getSize().name();
        }));
        priceI.setCellValueFactory(makeSoldItemCellFactory((s) -> {
            return Double.toString(s.getItem().getPrice());
        }));
        amountI.setCellValueFactory(makeSoldItemCellFactory((s) -> {
            return Integer.toString(s.getItem().getAmount());
        }));
    }

    public static void initFromToTables(
            TableView<Item> fromPrTable,
            TableView<Item> toPrTable,
            TableColumn<Item, String> nameFrom, TableColumn<Item, String> nameTo,
            TableColumn<Item, String> categoryFrom, TableColumn<Item, String> categoryTo,
            TableColumn<Item, Size> sizeFrom, TableColumn<Item, Size> sizeTo,
            TableColumn<Item, Double> priceFrom, TableColumn<Item, Double> priceTo,
            TableColumn<Item, Integer> amountFrom, TableColumn<Item, Integer> amountTo,
            TableColumn<Item, Item> add, TableColumn<Item, Item> addAll, TableColumn<Item, Item> remove, TableColumn<Item, Item> removeAll,
            Consumer<Item> addC, Consumer<Item> addAllC, Consumer<Item> removeC, Consumer<Item> removeAllC) {
        setPropertyValueFactory(nameFrom, categoryFrom, sizeFrom, priceFrom, amountFrom);
        setPropertyValueFactory(nameTo, categoryTo, sizeTo, priceTo, amountTo);
        addButtonColumn(removeAll, ReadOnlyObjectWrapper<Item>::new, "Remove all", removeAllC);
        addButtonColumn(addAll, ReadOnlyObjectWrapper<Item>::new, "Add all", addAllC);
        addButtonColumn(remove, ReadOnlyObjectWrapper<Item>::new, "Remove", removeC);
        addButtonColumn(add, ReadOnlyObjectWrapper<Item>::new, "Add", addC);
    }

    private static void addButtonColumn(TableColumn<Item, Item> buttonColumn, Function<Item, ObservableValue<Item>> property, String name, Consumer buttonAction) {
        buttonColumn.setCellValueFactory(cellData -> property.apply(cellData.getValue()));

        buttonColumn.setCellFactory(col -> {
            Button button = new Button(name);
            TableCell<Item, Item> cell = new TableCell<Item, Item>() {
                @Override
                public void updateItem(Item item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(button);
                    }
                }
            };

            button.setOnAction(e -> buttonAction.accept(cell.getItem()));

            return cell;
        });
    }

    private static void setPropertyValueFactory(
            TableColumn<Item, String> name,
            TableColumn<Item, String> category,
            TableColumn<Item, Size> size,
            TableColumn<Item, Double> price,
            TableColumn<Item, Integer> amount) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        category.setCellValueFactory(makeCellFactory((t) -> {
            return t.getItemCategory().getName();
        }));
        size.setCellValueFactory(new PropertyValueFactory<>("size"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        // Set styles
        size.setStyle("-fx-alignment: CENTER;");
        price.setStyle("-fx-alignment: CENTER-RIGHT;");
        amount.setStyle("-fx-alignment: CENTER-RIGHT;");
    }
}
