package com.fuerstenberg.inventorymanagergui.form;

import com.fuerstenberg.inventorymanagergui.MainApplication;
import com.fuerstenberg.inventorymanagergui.backend.*;
import com.fuerstenberg.inventorymanagergui.component.PartTableViewController;
import com.fuerstenberg.inventorymanagergui.component.ProductPartTableViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for modifyProductForm
 */
public class ModifyProductFormController implements Initializable {

    /**
     * fxml accessor
     */
    @FXML
    protected Label formLabel;

    /**
     * fxml accessor
     */
    @FXML
    protected VBox vBox;

    /**
     * fxml accessor
     */
    @FXML
    protected TextField idTextField;

    /**
     * fxml accessor
     */
    @FXML
    protected  TextField nameTextField;

    /**
     * fxml accessor
     */
    @FXML
    protected TextField inventoryTextField;

    /**
     * fxml accessor
     */
    @FXML
    protected TextField priceTextField;

    /**
     * fxml accessor
     */
    @FXML
    protected TextField maxTextField;

    /**
     * fxml accessor
     */
    @FXML
    protected TextField minTextField;

    /**
     * fxml accessor
     */
    @FXML
    protected TableView<Part> partTableView;

    /**
     * fxml accessor
     */
    @FXML
    private TableView<Part> productPartTableView;

    /**
     * fxml accessor
     */
    @FXML
    private GridPane gridPane;

    /**
     * fxml accessor
     */
    @FXML
    private TextField partSearchField;

    /**
     * fxml accessor
     */
    @FXML
    private GridPane productPartsGridPane;

    /**
     * Product being modified
     */
    private Product product;


    /**
     * Associated parts for the product being modified
     */
    private ObservableList<Part> associatedParts;


    /**
     * Remove an associated part from the product open
     */
    @FXML
    public void removeAssociatedPart() {
        Part part = productPartTableView.getSelectionModel().getSelectedItem();
        if (part == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Select an item from the table", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you wish to remove this part?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                associatedParts.remove(part);
            }
        });
    }

    /**
     * Overwrites product with new information on form and saves to inventory
     */
    @FXML
    public void saveData() {
        Boolean validData = true;
        int id = -1;
        String name = null;
        double price = -1;
        int inventory = -1;
        int max = -1;
        int min = -1;

        /*
        lazy retrieve code, I didn't see anything specifying how we need to vet our data, so I just decided to use
         try/catch to keep it quick and simple. I know the smarter method would be to prevent text data to be inputted
         in the first place for integer/double fields but that would take time for something that I don't think was a
         requirement
         */
        try {
            id = Inventory.createProductID();
            name = nameTextField.getText();
            price = Double.valueOf(priceTextField.getText());
            inventory = Integer.valueOf(inventoryTextField.getText());
            max = Integer.valueOf(maxTextField.getText());
            min = Integer.valueOf(minTextField.getText());

        } catch (Exception e) {
            validData = false;
        }


        //Handling invalid inputs
        //guard clause - exits if true
        if (!hasStockWithinMinMax(inventory, max, min) || validData == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory (Inv) must be a value between min and max and all values must be valid", ButtonType.OK);
            alert.showAndWait();
            return;
        }


        Product newProduct = new Product(id, name, inventory, min, max, price);
        for (Part p : associatedParts) {
            newProduct.addAssociatedPart(p);
        }

        Inventory.updateProduct(getProductIndex(product), newProduct);

        Stage stage = (Stage) vBox.getScene().getWindow();
        stage.close();
    }

    /**
     * @param product
     * @return product index in inventory
     */
    public int getProductIndex(Product product) {
        for (int i = 0; i < Inventory.getProducts().size(); i++) {
            if (Inventory.getProducts().get(i) == product) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Assigns product being modified on startup of form
     * @param product
     */
    @FXML
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Add selected part to associated parts of product
     */
    @FXML
    public void addPart() {
        Part part = partTableView.getSelectionModel().getSelectedItem();
        if (part == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Select an item from the table", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        associatedParts.add(part);
    }


    /**
     * @param inventory
     * @param max
     * @param min
     * @return
     */
     static boolean hasStockWithinMinMax(int inventory, int max, int min) {
        return inventory > min && inventory < max;
    }


    /**
     * Closes stage
     */
    public void cancelButton() {
        Stage stage = (Stage) vBox.getScene().getWindow();
        stage.close();
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
        try {
            associatedParts = FXCollections.observableArrayList();

            FXMLLoader partTableLoader = new FXMLLoader(MainApplication.class.getResource("component/partTableView.fxml"));
            partTableView = partTableLoader.load();
            PartTableViewController partController = partTableLoader.getController();
            partController.setSearchBar(partSearchField);
            gridPane.getChildren().add(partTableView);


            //productPartTableView
            FXMLLoader productPartLoader = new FXMLLoader(MainApplication.class.getResource("component/productPartTableView.fxml"));
            productPartTableView = productPartLoader.load();
            ProductPartTableViewController productPartController = productPartLoader.getController();
            productPartController.setParts(associatedParts);
            //no search bar will be used
            productPartController.setSearchBar(null);
            productPartsGridPane.getChildren().add(productPartTableView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Populates form with selected product information on startup of form
     */
    public void populateForm() {
        idTextField.setText(Integer.toString(product.getId()));
        nameTextField.setText(product.getName());
        priceTextField.setText(Double.toString(product.getPrice()));
        inventoryTextField.setText(Integer.toString(product.getStock()));
        maxTextField.setText(Integer.toString(product.getMax()));
        minTextField.setText(Integer.toString(product.getMin()));


        for (Part p : product.getAllAssociatedParts()) {
            associatedParts.add(p);
        }

    }
}
