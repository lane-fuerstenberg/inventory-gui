package com.fuerstenberg.inventorymanagergui.component;

import com.fuerstenberg.inventorymanagergui.backend.Inventory;
import com.fuerstenberg.inventorymanagergui.backend.Part;
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
 * Controller for fxml productPartTableView
 */
public class ProductPartTableViewController implements Initializable {

    /**
     * fxml accessor
     */
    @FXML
    private TableView<Part> productPartTableView;

    /**
     * fxml accessor
     */
    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    /**
     * fxml accessor
     */
    @FXML
    private TableColumn<Part, String> partNameColumn;

    /**
     * fxml accessor
     */
    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;

    /**
     * fxml accessor
     */
    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    /**
     * fxml accessor
     */
    @FXML
    private TextField searchBar;

    /**
     * List of parts used to display for tableview
     */
    private ObservableList<Part> parts;

    /**
     * Assigns search bar
     * @param searchBar searchbar to be assigned
     */
    public void setSearchBar(TextField searchBar) {
        if (searchBar == null) {
            productPartTableView.setItems(parts);
            return;
        }

        this.searchBar = searchBar;
        FilteredList<Part> filteredData = new FilteredList<>(parts, b -> true);

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(parts -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                if (parts.getName().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (Integer.toString(parts.getId()).contains(searchKeyword)) {
                    return true;
                } else if ((Integer.toString(parts.getStock()).contains(searchKeyword))) {
                    return true;
                } else if (Double.toString(parts.getPrice()).contains(searchKeyword)) {
                    return true;
                } else if ((Integer.toString(parts.getMax()).contains(searchKeyword))) {
                    return true;
                } else if ((Integer.toString(parts.getMin()).contains(searchKeyword))) {
                    return true;
                }

                return false;
            });
        });

        productPartTableView.setItems(filteredData);
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
        partIdColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        parts = Inventory.getParts();
    }


    /**
     * @param parts parts to be assigned
     */
    public void setParts(ObservableList<Part> parts) {
        this.parts = parts;
    }
}
