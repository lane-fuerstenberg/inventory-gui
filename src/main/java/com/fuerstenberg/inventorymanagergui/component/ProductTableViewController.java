package com.fuerstenberg.inventorymanagergui.component;

import com.fuerstenberg.inventorymanagergui.backend.Inventory;
import com.fuerstenberg.inventorymanagergui.backend.Product;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for fxml productTableView
 */
public class ProductTableViewController implements Initializable {

    /**
     * fxml accessor
     */
    @FXML
    private TableView<Product> productTableView;

    /**
     * fxml accessor
     */
    @FXML
    private TableColumn<Product, Integer> productIdColumn;

    /**
     * fxml accessor
     */
    @FXML
    private TableColumn<Product, String> productNameColumn;

    /**
     * fxml accessor
     */
    @FXML
    private TableColumn<Product, Integer> productInventoryColumn;

    /**
     * fxml accessor
     */
    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    /**
     * fxml accessor
     */
    @FXML
    private TextField searchBar;

    /**
     * List of all products to be displayed by tableview
     */
    private ObservableList<Product> products;

    /**
     * RUNTIME ERROR: if setSearchBar is not called for controller then the class will completely fail. I worked around
     * it by assigning null in situations where searchBar is not used but this is a bad workaround.
     * @param searchBar searchbar to be assigned to TableView
     */
    public void setSearchBar(TextField searchBar) {
        this.searchBar = searchBar;
        FilteredList<Product> filteredData = new FilteredList<>(products, b -> true);

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(products -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                if (products.getName().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (Integer.toString(products.getId()).contains(searchKeyword)) {
                    return true;
                } else if ((Integer.toString(products.getStock()).contains(searchKeyword))) {
                    return true;
                } else if (Double.toString(products.getPrice()).contains(searchKeyword)) {
                    return true;
                } else if ((Integer.toString(products.getMax()).contains(searchKeyword))) {
                    return true;
                } else if ((Integer.toString(products.getMin()).contains(searchKeyword))) {
                    return true;
                }

                return false;
            });
        });

        productTableView.setItems(filteredData);
    }

    /**
     * @param location
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productIdColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        products = Inventory.getProducts();
    }
}
