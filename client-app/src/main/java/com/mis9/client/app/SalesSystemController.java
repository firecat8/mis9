/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mis9.client.app;

import com.mis9.bl.ServiceProvider;
import com.mis9.client.app.holder.CategoriesHolder;
import com.mis9.client.app.holder.CategoryEventHandler;
import com.mis9.client.app.holder.ClientEventHandler;
import com.mis9.client.app.holder.Holdable;
import com.mis9.client.app.holder.ItemsEventHandler;
import com.mis9.client.app.holder.SalesEventHandler;
import com.mis9.client.app.listener.IsCheckedListener;
import com.mis9.client.app.listener.PriceTextFieldListener;
import com.mis9.client.app.reporter.CsvReporter;
import com.mis9.client.app.utils.CompUtils;
import com.mis9.client.app.utils.TableViewUtils;
import com.mis9.domain.Client;
import com.mis9.domain.ClientInfo;
import com.mis9.domain.Item;
import com.mis9.domain.ItemCategory;
import com.mis9.domain.Sale;
import com.mis9.domain.Size;
import com.mis9.domain.SoldItem;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author gdimitrova
 */
public class SalesSystemController implements Initializable {

    //PRODUCTS VIEW
    @FXML
    private AnchorPane productsPane;

    @FXML
    private Label error;

    @FXML
    private Label sizeLabel, productNameLabel, descriptionLabel, priceLabel, amountLabel, productCategoryLabel, movedProductsLabel;

    @FXML
    private Button products, renameBtn, removeCategoryBtn, modifyBtn, addProductBtn;

    @FXML
    private ChoiceBox categoryChoiceBox, sizeChoiceBox, movedCategoryChoiceBox, productCategoryCBox;

    @FXML
    private TextField renameCategoryTxt, addCategoryTxt, productNameTxt, descriptionTxt, priceTxt, amountTxt;

    @FXML
    private CheckBox removeProductsCBox;

    @FXML
    private TableView<Item> prTable;

    @FXML
    private TableColumn<Item, String> name, description, category;

    @FXML
    private TableColumn<Item, Size> size;

    @FXML
    private TableColumn<Item, Double> price;

    @FXML
    private TableColumn<Item, Integer> amount;

    //Sale section
    @FXML
    private DatePicker saleDatePicker;

    @FXML
    private AnchorPane salesPane, productsSelectionPane, clientInfoPane;

    @FXML
    private Button sales, stepBtnClientInfo;

    @FXML
    private Label totalPriceLabel, mailSearchLabel, phoneSearchL;

    @FXML
    private ChoiceBox categoryChoiceFrom, clientCriteriaSearch;

    @FXML
    private TableView<Item> fromPrTable, toPrTable;

    @FXML
    private TableColumn<Item, String> nameFrom, nameTo;

    @FXML
    private TableColumn<Item, String> categoryFrom, categoryTo;

    @FXML
    private TableColumn<Item, Size> sizeFrom, sizeTo;

    @FXML
    private TableColumn<Item, Double> priceFrom, priceTo;

    @FXML
    private TableColumn<Item, Integer> amountFrom, amountTo;

    @FXML
    private TableColumn<Item, Item> add, addAll, remove, removeAll;

    @FXML
    private TableView<ClientInfo> clientsTable;

    @FXML
    private TableColumn<ClientInfo, String> firstName, surname, lastName, email, phone, address;

    @FXML
    private TextField firstNameTxt, surnameTxt, lastNameTxt, mailTxt, phoneTxt,
            mailSearch, phoneSearch;

    @FXML
    private TextArea addressTxt;

    //REPORTS VIEW
    private static final String SEPARATOR = ",";

    private static final String SALE_BEFORE_DATE = "1. Sales before chosen date.";

    private static final String SALES_FOR_PERIOD = "2. Sales for chosen period.";

    @FXML
    private Group salesBeforeDateGroup, fromToCalGroup;

    @FXML
    private AnchorPane reportsPane;

    @FXML
    private Button reports, export, loadData;

    @FXML
    private ChoiceBox reportsChoice, formats;

    @FXML
    private TableView<Sale> saleTable;

    @FXML
    private TableColumn<Sale, Sale> saleColumn;

    @FXML
    private TableView<SoldItem> soldItemsTable;

    @FXML
    private TableColumn<SoldItem, String> nameI, descriptionI, categoryI;

    @FXML
    private TableColumn<SoldItem, Size> sizeI;

    @FXML
    private TableColumn<SoldItem, Double> priceI;

    @FXML
    private TableColumn<SoldItem, Integer> amountI;

    @FXML
    private DatePicker datePicker, fromCalendar, toCalendar;

    //Other
    private DateFormat date_formater = new SimpleDateFormat("HH:mm:ss> ");

    private ServiceProvider svcProvider = ServiceProvider.INSTANCE;

    private Item selected;

    private int productsData = 0, reportsData = 0;

    private CategoriesHolder categoriesHolder = new CategoriesHolder(),
            categoriesHolderFrom = new CategoriesHolder();

    private static final String RED_TEXT = "-fx-text-fill: red 100";

    private static final String BLACK_TEXT = "-fx-text-fill: black";

    private static final String ALL_CATEGORIES = "All categories";

    private static final String EMAIL = "E-mail";

    private static final String PHONE = "Phone";

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Products view
        error.setStyle(RED_TEXT);
        setProductsViewVisible();

        amountTxt.setTextFormatter(CompUtils.createTextFormatter());
        priceTxt.textProperty().addListener(new PriceTextFieldListener(priceTxt));
        //Buttons
        CompUtils.setVisible(false, renameBtn, modifyBtn, removeCategoryBtn, addProductBtn);
        //TextFields
        CompUtils.setVisible(false, renameCategoryTxt);
        //Labels
        CompUtils.setVisible(false, movedProductsLabel);
        //ChoiceBoxes
        CompUtils.setVisible(false, movedCategoryChoiceBox);
        //CheckBoxes
        CompUtils.setVisible(false, removeProductsCBox);
        //
        setProductsPartianViewVisibility(false);
        //
        removeProductsCBox.selectedProperty().addListener(new IsCheckedListener(removeProductsCBox, this::setMovedCatVisibility));
        //columns and table
        TableViewUtils.initProductsTable(prTable, this::selectItem, this::delete,
                name, description, category, size, price, amount);

        //choiceboxes
        sizeChoiceBox.setItems(FXCollections.observableArrayList(
                Size.values()
        ));
        sizeChoiceBox.setValue(Size.NONE);
        categoryChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                setError(null);
                if (newValue == null || newValue.isBlank()) {
                    TableViewUtils.refreshTable(prTable, Collections.EMPTY_SET);
                    return;
                }
                ObservableList names = FXCollections.observableArrayList(categoriesHolder.getNames());
                refreshTable(prTable, newValue, names, newValue, categoriesHolder);
                if (categoriesHolder.getNames().size() > 1) {
                    ObservableList list = FXCollections.observableArrayList(names);
                    list.remove(newValue);
                    addItems(movedCategoryChoiceBox, list, list.get(0));
                }
            }
        });
        // Sales view
        TableViewUtils.initFromToTables(fromPrTable, toPrTable, nameFrom, nameTo, categoryFrom, categoryTo, sizeFrom, sizeTo, priceFrom, priceTo, amountFrom, amountTo,
                add, addAll, remove, removeAll, this::add, this::addAll, this::remove, this::removeAll);

        categoryChoiceFrom.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                setError(null);
                if (newValue == null || newValue.isBlank()) {
                    TableViewUtils.refreshTable(fromPrTable, Collections.EMPTY_SET);
                    return;
                }
                ObservableList names = FXCollections.observableArrayList(categoriesHolderFrom.getNames());
                refreshTable(fromPrTable, newValue, names, newValue, categoriesHolderFrom);
            }
        });
        clientCriteriaSearch.setItems(FXCollections.observableArrayList(
                EMAIL, PHONE
        ));

        clientCriteriaSearch.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                setError(null);
                switch (newValue) {
                    case EMAIL:
                        setCriteriaSearchVisibility(true, false);
                        break;
                    case PHONE:
                        setCriteriaSearchVisibility(false, true);
                        break;
                }
            }
        });
        clientCriteriaSearch.setValue(EMAIL);

        phoneSearch.setTextFormatter(CompUtils.createTextFormatter());
        phoneTxt.setTextFormatter(CompUtils.createTextFormatter());
        TableViewUtils.initClientsTable(clientsTable, this::selectClient, firstName, surname, lastName, email, phone, address);

        // Reports view
        TableViewUtils.initSalesBeforeDateTable(saleTable, new Consumer<Sale>() {
            @Override
            public void accept(Sale t) {
                TableViewUtils.refreshTable(soldItemsTable, ItemsEventHandler.onLoadAllSoldItemsEvent(t));
            }
        }, saleColumn);
        TableViewUtils.initSoldItemsTable(soldItemsTable, nameI, descriptionI, categoryI, sizeI, priceI, amountI);

        reportsChoice.setItems(FXCollections.observableArrayList(
                SALE_BEFORE_DATE, SALES_FOR_PERIOD
        ));
        reportsChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                setError(null);

                switch (newValue) {
                    case SALE_BEFORE_DATE:
                        setGroupVisibility(salesBeforeDateGroup, fromToCalGroup);
                        break;
                    case SALES_FOR_PERIOD:
                        setGroupVisibility(fromToCalGroup, salesBeforeDateGroup);
                        break;
                }
            }
        });
        reportsChoice.setValue(SALE_BEFORE_DATE);
        LocalDate now = LocalDate.now();
        datePicker.setValue(now);
        saleDatePicker.setValue(now);
        toCalendar.setValue(now);
        fromCalendar.setValue(LocalDate.of(now.getYear() - 3, now.getMonth(), now.getDayOfMonth()));

        formats.setItems(FXCollections.observableArrayList(
                "xls", "xlsx", "csv"
        ));
        formats.setValue("xls");
        fillChoiceBox(loadCategoriesData(), categoryChoiceBox, categoriesHolder, removeCategoryBtn);
        synchronizeProductsView();

    }

    private List<ItemCategory> loadCategoriesData() {
        List<ItemCategory> categories = new ArrayList<>();
        try {
            svcProvider.beginTransaction();
            categories = svcProvider.getItemCategoryDao().loadAll();
            svcProvider.commit();
        } catch (Exception ex) {
            Logger.getLogger(SalesSystemController.class.getName()).log(Level.SEVERE, null, ex);
            if (svcProvider.getTransaction() != null) {
                svcProvider.rollback();
            }
        }
        return categories;
    }

    @FXML
    private void onProductsButtonClicked(Event event) {
        setError(null);
        fillChoiceBox(loadCategoriesData(), categoryChoiceBox, categoriesHolder, removeCategoryBtn);
        synchronizeProductsView();
        setProductsViewVisible();
    }

    private void setGroupVisibility(Group g, Group... groups) {
        CompUtils.setVisible(true, g);
        CompUtils.setVisible(false, groups);
    }

    @FXML
    private void onSalesButtonClicked(Event event) {
        setError(null);
        refreshSalesPane();
        setSalesViewVisible();
    }

    @FXML
    private void onExportClicked(Event event) {

        Object fileFormat = formats.getSelectionModel().getSelectedItem();
        // Reporter reporter = ReporterResolver.resolve(fileFormat.toString());
        CsvReporter<Sale> reporter = new CsvReporter<Sale>(this::writeSaleRow);

        FileChooser fileChooser = new FileChooser();

        //Set extension filter to .xlsx files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(reporter.getReportType(), reporter.getFileExtention());
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(App.mainStage);

        //If file is not null, write to file using output stream.
        if (file != null) {
            try (FileOutputStream outputStream = new FileOutputStream(file.getAbsolutePath())) {
                reporter.generateReport(outputStream, getTable());
            } catch (IOException ex) {
                Logger.getLogger(SalesSystemController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private TableView getTable() {

        return saleTable;
    }

    private String writeSaleRow(Integer i, Sale sale) {
        return TableViewUtils.format.format(new Date(sale.getSaleDate())) + SEPARATOR + writeClientRow(i, sale.getClient());
    }

    private String writeClientRow(Integer i, Client c) {
        return c.getFirstName() + SEPARATOR + c.getSurname() + SEPARATOR + c.getLastName();
    }

    @FXML
    private void onLoadDataClicked(Event event) {
        setError(null);
        Object selectedItem = reportsChoice.getSelectionModel().getSelectedItem();
        switch (selectedItem.toString()) {
            case SALE_BEFORE_DATE:
                TableViewUtils.refreshTable(saleTable, SalesEventHandler.onLoadAllSalesBeforeEvent(datePicker.getValue()));
                break;
            case SALES_FOR_PERIOD:
                TableViewUtils.refreshTable(saleTable, SalesEventHandler.onLoadAllSalesBetweenEvent(fromCalendar.getValue(), toCalendar.getValue()));
                break;
        }
    }

    @FXML
    private void onNextStepButtonClicked(Event event) {
        setError(null);
        setSalesPanesVisibility(false, true);
    }

    @FXML
    private void onConfirmSaleClicked(Event event) {
        setError(null);
        String errors = collecErrors();
        if (!errors.isEmpty()) {
            setError(errors);
            return;
        }
        ClientInfo ci = ClientEventHandler.onClientSaveEvent(firstNameTxt.getText(), surnameTxt.getText(), lastNameTxt.getText(),
                mailTxt.getText(), phoneTxt.getText(), addressTxt.getText());
        if (ci == null) {
            setError("Couldn't proceed sale. Problem during saving/loading of client info. ");
            return;
        }
        ObservableList<Item> items = toPrTable.getItems();
        Sale sale = ClientEventHandler.onSaleSaveEvent(ci.getClient(), saleDatePicker.getValue(), sumAmount(items), sum(items));
        if (sale == null) {
            setError("Couldn't proceed sale. Problem during saving sale. ");
            return;
        }
        if (!ItemsEventHandler.onSoldItemSaveEvent(sale, items, categoriesHolderFrom)) {
            setError("Couldn't proceed sale. Problem during saving sale items. ");
            return;
        }
        refreshSalesPane();
        setSalesViewVisible();
    }

    @FXML
    private void onSearchClientsClicked(Event event) {
        setError(null);
        Object criteriaBy = clientCriteriaSearch.getSelectionModel().getSelectedItem();
        switch (criteriaBy.toString()) {
            case EMAIL:
                searchClients(mailTxt, "Not valid e-mail.", ClientEventHandler::onSearchByEmailEvent);
                break;
            case PHONE:
                searchClients(phoneTxt, "Not valid phone number.", ClientEventHandler::onSearchByPhoneEvent);
                break;
        }
    }

    private boolean hasErrors(String text, String errorText) {
        if (text.isBlank()) {
            setError(errorText);
            return true;
        }
        return false;
    }

    private void searchClients(TextField textField, String errorText, Function<String, ClientInfo> search) {
        ArrayList<ClientInfo> ciList = new ArrayList<>();
        if (!hasErrors(textField.getText(), errorText)) {
            ClientInfo ci = search.apply(textField.getText());
            ciList.add(ci);
            refreshClientsTable(new ArrayList<>(ciList));
        }
    }

    @FXML
    private void onLoadAllClientsClicked(Event event) {
        setError(null);
        refreshClientsTable(ClientEventHandler.onLoadAllEvent());
    }

    private void refreshClientsTable(List<ClientInfo> loadedCI) {
        clientsTable.getItems().clear();
        clientsTable.getItems().addAll(FXCollections.observableArrayList(loadedCI));
    }

    @FXML
    private void onPrevStepBtnClicked(Event event) {
        setSalesPanesVisibility(true, false);
    }

    @FXML
    private void onReportsButtonClicked(Event event) {
        setReportsViewVisible();
    }

    private void refreshSalesPane() {
        setSalesPanesVisibility(true, false);
        categoriesHolderFrom.clear();
        fillChoiceBox(loadCategoriesData(), categoryChoiceFrom, categoriesHolderFrom);
        ObservableList<Object> newList = FXCollections.observableArrayList(categoriesHolderFrom.getNames());
        if (newList.size() > 1 && !newList.contains(ALL_CATEGORIES)) {
            newList.add(ALL_CATEGORIES);
        }
        toPrTable.getItems().clear();
        setTotalPrice();
        refreshTable(fromPrTable, categoryChoiceFrom.getSelectionModel().getSelectedItem(), newList, null, categoriesHolderFrom);
    }

    private void setCriteriaSearchVisibility(boolean isEmail, boolean isPhone) {
        mailSearchLabel.setVisible(isEmail);
        mailSearch.setVisible(isEmail);
        phoneSearchL.setVisible(isPhone);
        phoneSearch.setVisible(isPhone);
    }

    private void setProductsViewVisible() {
        setError(null);
        setViewVisibility(true, false, false);
    }

    private void setSalesViewVisible() {
        setError(null);
        setViewVisibility(false, true, false);
    }

    private void setReportsViewVisible() {
        setError(null);
        setViewVisibility(false, false, true);
    }

    private void setSalesPanesVisibility(boolean productsSelectionPaneVisible, boolean clientInfoPaneVisible) {
        setViewVisibility(productsSelectionPane, productsSelectionPaneVisible);
        setViewVisibility(clientInfoPane, clientInfoPaneVisible);
    }

    @FXML
    private void onModifyButtonClicked(Event event) {
        setError(null);
        String errors = collecErrortMessages();
        if (!errors.isEmpty()) {
            setError(errors);
            return;
        }
        if (updateItem()) {
            synchronizeProductsView();
            return;
        }
        setError("Can't modify. Every product has unique name and size and belong to one category.");
    }

    private boolean updateItem() {
        return ItemsEventHandler.onUpdateEvent(
                selected,
                productNameTxt.getText(),
                (ItemCategory) categoriesHolder.getEntity(productCategoryCBox.getSelectionModel().getSelectedItem()),
                descriptionTxt.getText(),
                Size.valueOf(sizeChoiceBox.getSelectionModel().getSelectedItem().toString()),
                Double.valueOf(priceTxt.getText()),
                Integer.valueOf(amountTxt.getText()),
                categoriesHolder);
    }

    @FXML
    private void onAddProductButtonClicked(Event e) {
        setError(null);
        String errors = collecErrortMessages();
        if (!errors.isEmpty()) {
            setError(errors);
            return;
        }
        String itemName = productNameTxt.getText();
        Size itemSize = Size.valueOf(sizeChoiceBox.getSelectionModel().getSelectedItem().toString());
        Integer itemAmount = Integer.valueOf(amountTxt.getText());
        if (!ItemsEventHandler.onAddEvent(productNameTxt.getText(),
                (ItemCategory) categoriesHolder.getEntity(productCategoryCBox.getSelectionModel().getSelectedItem()),
                descriptionTxt.getText(), itemSize,
                Double.valueOf(priceTxt.getText()),
                itemAmount,
                categoriesHolder)) {
            Item loaded = ItemsEventHandler.onLoadItem(itemName, itemSize);
            if (itemAmount > 0 && loaded.getAmount() == 0) {
                if (updateItem()) {
                    synchronizeProductsView();
                    return;
                }
            }
            setError("Can't save. Every product has unique name and size and belong to one category.");
            return;
        }
        synchronizeProductsView();
    }

    @FXML
    private void onRemoveCategoryButtonClicked(Event e) {
        setError(null);
        if (CategoryEventHandler.onRemoveEvent(categoryChoiceBox, categoriesHolder, removeProductsCBox.isSelected(),
                movedCategoryChoiceBox.getSelectionModel().getSelectedItem())) {
            synchronizeProductsView();
            return;
        }
        setError("Can't remove category.");
    }

    private void setMixedVisibility() {
        int count = categoriesHolder.getNames().size();
        setProductsPartianViewVisibility(count != 0);
        setCategoryViewVisibility(count != 0);
        setMovedCatVisibility(count > 1, removeProductsCBox.selectedProperty().get());
    }

    @FXML
    private void onRenameCategoryButtonClicked(Event event) {
        if (hasErrors(renameCategoryTxt)) {
            return;
        }
        CategoryEventHandler.onUpdateEvent(
                categoriesHolder,
                categoriesHolder.getEntity(
                        categoryChoiceBox.getSelectionModel().getSelectedItem()
                ),
                renameCategoryTxt.getText()
        );
        synchronizeProductsView();
    }

    @FXML
    private void onAddCategoryButtonClicked(Event event) {
        if (hasErrors(addCategoryTxt)) {
            return;
        }
        CategoryEventHandler.onAddEvent(addCategoryTxt.getText(), categoryChoiceBox, categoriesHolder);
        synchronizeProductsView();
        clearProducts();
    }

    private void setViewVisibility(boolean productsPaneVisible, boolean saleSectionVisible, boolean reportsVisible) {
        setViewVisibility(products, productsPane, productsPaneVisible);
        setViewVisibility(sales, salesPane, saleSectionVisible);
        setViewVisibility(reports, reportsPane, reportsVisible);
    }

    private void synchronizeProductsView() {
        setMixedVisibility();
        synchronizeCategoryCheckBoxes();
    }

    private void setViewVisibility(Button btn, AnchorPane pane, boolean visible) {
        btn.setStyle(visible ? RED_TEXT : BLACK_TEXT);
        setViewVisibility(pane, visible);
    }

    private void setViewVisibility(AnchorPane pane, boolean visible) {
        pane.setVisible(visible);
        pane.setManaged(visible);
    }

    private void fillChoiceBox(List<ItemCategory> list, ChoiceBox choiceBox, Holdable holdable, Button removeBtn) {
        if (list.isEmpty()) {
            setProductsPartianViewVisibility(false);
            return;
        }
        fillChoiceBox(list, choiceBox, holdable);
        removeBtn.setVisible(true);
    }

    private void fillChoiceBox(List<ItemCategory> list, ChoiceBox choiceBox, Holdable holdable) {
        holdable.clear();
        if (list.isEmpty()) {
            return;
        }
        list.forEach((ic) -> {
            ic.getItems().removeIf(i -> i.getAmount() == 0);
        });
        holdable.addAll(list);
        List names = holdable.getNames();
        choiceBox.setItems(FXCollections.observableArrayList(names));
        choiceBox.setValue(names.get(0));
    }

    private String collecErrortMessages() {
        String errors = "", missing = "Missing";
        if (productNameTxt.getText().isBlank()) {
            errors += " name,";
        }
        if (descriptionTxt.getText().isBlank()) {
            errors += " description,";
        }
        if (amountTxt.getText().isBlank()) {
            errors += " amount,";
        }
        if (priceTxt.getText().isBlank()) {
            errors += " price.";
        } else if (Double.valueOf(priceTxt.getText()) > 200.00) {
            if (!errors.equals("")) {
                errors += " and";
            }
            errors += " price is bigger than 200.";
        }
        if (!errors.equals("")) {
            missing += errors;
            return missing;
        }
        return errors;
    }

    private String collecErrors() {
        String errors = "", missing = "Missing";
        if (firstNameTxt.getText().isBlank()) {
            errors += " first name,";
        }
        if (surnameTxt.getText().isBlank()) {
            errors += " surname,";
        }
        if (lastName.getText().isBlank()) {
            errors += " last name,";
        }
        if (address.getText().isBlank()) {
            errors += " address,";
        }
        if (phone.getText().isBlank()) {
            errors += " phone,";
        }
        if (mailTxt.getText().isBlank()) {
            errors += " e-mail,";
        } else if (!validateEmail(mailTxt.getText())) {
            errors += "not a valid email";
        }

        if (!errors.equals("")) {
            missing += errors.substring(0, errors.length() - 2) + ".";
            return missing;
        }
        return errors;
    }

    private void clearProducts() {
        addCategoryTxt.clear();
        productNameTxt.clear();
        descriptionTxt.clear();
        priceTxt.clear();
        amountTxt.clear();
        sizeChoiceBox.setValue(Size.NONE);
        CompUtils.setVisible(false, modifyBtn);
    }

    private void setProductsPartianViewVisibility(boolean isVisible) {
        CompUtils.setVisible(isVisible, productNameLabel, descriptionLabel, amountLabel, priceLabel, productCategoryLabel, sizeLabel);
        CompUtils.setVisible(isVisible, productNameTxt, descriptionTxt, priceTxt, amountTxt);
        CompUtils.setVisible(isVisible, productCategoryCBox);
        CompUtils.setVisible(isVisible, sizeChoiceBox);
        CompUtils.setVisible(isVisible, addProductBtn);
        CompUtils.setVisible(!prTable.getItems().isEmpty(), modifyBtn);
    }

    private void setCategoryViewVisibility(boolean isVisible) {
        CompUtils.setVisible(isVisible, removeCategoryBtn, renameBtn);
        CompUtils.setVisible(isVisible, renameCategoryTxt);

        CompUtils.setVisible(isVisible, removeProductsCBox);
        boolean hasOneCategory = categoryChoiceBox.getItems().size() == 1;
        if (hasOneCategory) {
            removeProductsCBox.setSelected(true);
            removeProductsCBox.setDisable(true);
            return;
        }
        removeProductsCBox.setDisable(false);
    }

    private void setMovedCatVisibility(boolean isVisible, boolean isChecked) {
        boolean hasCategories = categoryChoiceBox.getItems().size() > 1;
        boolean visible = !isChecked && isVisible && hasCategories;
        CompUtils.setVisible(visible, movedProductsLabel);
        CompUtils.setVisible(visible, movedCategoryChoiceBox);
    }

    private void selectItem(Item t) {
        productNameTxt.setText(t.getName());
        productCategoryCBox.setValue(t.getItemCategory().getName());
        descriptionTxt.setText(t.getDescription());
        sizeChoiceBox.setValue(t.getSize());
        priceTxt.setText(Double.toString(t.getPrice()));
        amountTxt.setText(Integer.toString(t.getAmount()));
        addProductBtn.setVisible(true);
        modifyBtn.setVisible(true);
        selected = t;
    }

    private void delete(Item t) {
        setError(null);
        if (ItemsEventHandler.onRemoveEvent(categoriesHolder, t)) {
            synchronizeProductsView();
            if (selected.equals(t)) {
                selected = null;
                clearProducts();
            }
            return;
        }
        setError("Couldn't delete product " + t.getName());
    }

    private void synchronizeCategoryCheckBoxes() {
        ObservableList newList = FXCollections.observableArrayList(categoriesHolder.getNames());
        if (newList.isEmpty()) {
            CompUtils.clear(categoryChoiceBox, productCategoryCBox, movedCategoryChoiceBox);
            return;
        }
        if (newList.size() > 1 && !newList.contains(ALL_CATEGORIES)) {
            newList.add(ALL_CATEGORIES);
        }
        print(newList);
        Object selectedCategory = categoryChoiceBox.getValue();
        refreshTable(prTable, selectedCategory, newList, renameCategoryTxt.getText(), categoriesHolder);
        addItems(categoryChoiceBox, newList, selectedCategory);
        ObservableList items = categoryChoiceBox.getItems();
        addItems(productCategoryCBox, items, selectedCategory);
        if (items.size() < 2) {
            movedCategoryChoiceBox.getItems().clear();
            return;
        }
        ObservableList list = FXCollections.observableArrayList(items);
        list.remove(selectedCategory);
        addItems(movedCategoryChoiceBox, list, list.get(0));
    }

    private void refreshTable(TableView table, Object selectedCategory, ObservableList loadedList, String defaultName, CategoriesHolder holdable) {
        if (selectedCategory.equals(ALL_CATEGORIES)) {
            ObservableList<Object> list = FXCollections.observableArrayList();
            loadedList.forEach((name) -> {
                list.addAll(holdable.getEntity(name).getItems());
            });
            TableViewUtils.refreshTable(table, list);
        } else {
            selectedCategory = loadedList.contains(selectedCategory) ? selectedCategory : defaultName;
            TableViewUtils.refreshTable(table, holdable.getEntity(selectedCategory).getItems());
        }
    }

    private boolean hasErrors(TextField txt) {
        setError(null);
        String name = txt.getText();
        if (name == null || name.isBlank()) {
            setError("Missing name of category. Please write one.");
            return true;
        }
        if (categoriesHolder.getNames().contains(name)) {
            setError("Exists category with that name. Please write another.");
            return true;
        }
        return false;
    }

    private void addItems(ChoiceBox to, ObservableList from, Object selected) {
        to.getItems().clear();
        to.getItems().addAll(from);
        to.setValue(selected);
    }

    private void print(Collection c) {
        c.forEach((o) -> {
            print(o);
        });
        System.err.println();
    }

    private void print(Object o) {
        System.err.println(" " + o.toString());
    }

    private void setError(String msg) {
        error.setText(msg != null ? date_formater.format(Calendar.getInstance().getTime()) + msg : "");
    }

    private void addAll(Item t) {
        moveAll(t, fromPrTable.getItems(), toPrTable.getItems());
    }

    private void removeAll(Item t) {
        moveAll(t, toPrTable.getItems(), fromPrTable.getItems());
    }

    private void moveAll(Item t, ObservableList<Item> from, ObservableList<Item> to) {
        Item copy = t.makeCopy();
        int i = findIndex(to, t);
        if (i == -1) {
            to.add(copy);
        } else {
            Item forUpdate = to.get(i);
            copy.setAmount(copy.getAmount() + forUpdate.getAmount());
            to.set(i, copy);
        }
        from.remove(t);
        setTotalPrice();
    }

    private void setTotalPrice() {
        Double sum = sum(toPrTable.getItems());
        Integer amount = sumAmount(toPrTable.getItems());
        sum = new BigDecimal(sum).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        totalPriceLabel.setText("Total Price: " + sum + " EUR\tAmount:" + amount);
        stepBtnClientInfo.setVisible(sum > 0.00);
    }

    private void remove(Item t) {
        moveOne(t, toPrTable.getItems(), fromPrTable.getItems());
    }

    private void add(Item t) {
        moveOne(t, fromPrTable.getItems(), toPrTable.getItems());
    }

    private void moveOne(Item t, ObservableList<Item> from, ObservableList<Item> to) {
        Item copy = t.makeCopy();
        copy.setAmount(1);
        int i = findIndex(to, t);
        if (i == -1) {
            to.add(copy);
        } else {
            Item forUpdate = to.get(i);
            copy.setAmount(copy.getAmount() + forUpdate.getAmount());
            to.set(i, copy);
        }
        if (t.getAmount() == 1) {
            from.remove(t);
        } else {
            int j = findIndex(from, t);
            Item forUpdate = from.get(j);
            forUpdate.setAmount(forUpdate.getAmount() - 1);
            from.set(j, forUpdate);
        }
        setTotalPrice();
    }

    private Double sum(ObservableList<Item> items) {
        return items.stream()
                .mapToDouble(x -> x.getPrice() * x.getAmount())
                .sum();
    }

    private Integer sumAmount(ObservableList<Item> items) {
        return items.stream()
                .mapToInt(x -> x.getAmount())
                .sum();
    }

    private int findIndex(ObservableList<Item> listItems, Item t) {
        for (int i = 0; i < listItems.size(); i++) {
            if (isExist(listItems.get(i), t)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isExist(Item c, Item t) {
        return c.getName().equals(t.getName()) && c.getSize().equals(t.getSize());
    }

    private void selectClient(ClientInfo t) {
        Client client = t.getClient();
        firstNameTxt.setText(client.getFirstName());
        surnameTxt.setText(client.getSurname());
        lastNameTxt.setText(client.getLastName());
        mailTxt.setText(t.getEmail());
        phoneTxt.setText(t.getPhone());
        addressTxt.setText(t.getAddress());
    }
}
