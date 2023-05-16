package com.fuerstenberg.inventorymanagergui;

import com.fuerstenberg.inventorymanagergui.backend.Product;
import com.fuerstenberg.inventorymanagergui.component.ProductTableViewController;
import com.fuerstenberg.inventorymanagergui.backend.Inventory;
import com.fuerstenberg.inventorymanagergui.backend.Part;
import com.fuerstenberg.inventorymanagergui.component.PartTableViewController;
import com.fuerstenberg.inventorymanagergui.form.ModifyPartFormController;
import com.fuerstenberg.inventorymanagergui.form.ModifyProductFormController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for fxml mainForm
 */
public class MainController implements Initializable {

    /**
     * fxml accessor
     */
    @FXML
    private TableView<Part> partTableView;

    /**
     * fxml accessor
     */
    @FXML
    private TableView<Product> productTableView;

    /**
     * fxml accessor
     */
    @FXML
    private GridPane partGridPane;

    /**
     * fxml accessor
     */
    @FXML
    private GridPane productGridPane;

    /**
     * fxml accessor
     */
    @FXML
    private TextField partSearchField;

    /**
     * fxml accessor
     */
    @FXML
    private TextField productSearchField;


    /**
     * Starts up the addPartForm and shows to user
     */
    @FXML
    public void startAddPartForm() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("form/addPartForm.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            setStageProperties(stage);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts up modifyPartForm and shows to user
     */
    @FXML
    public void startModifyPartForm() {
        try {
            Part part = partTableView.getSelectionModel().getSelectedItem();
            if (part == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Select an item from the table", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("form/modifyPartForm.fxml"));

            Parent root = fxmlLoader.load();
            ModifyPartFormController controller = fxmlLoader.getController();
            controller.setPart(part);
            controller.populateForm();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            setStageProperties(stage);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * RUNTIME ERROR: I needed to call the forms controller and assign product with the controller to make sure the
     * modify forms can know which table entry was selected. Without it the forms are all empty.
     * Starts up modifyProductForm and shows to user
     */
    @FXML
    public void startModifyProductForm() {
        try {
            Product product = productTableView.getSelectionModel().getSelectedItem();
            if (product == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Select an item from the table", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("form/modifyProductForm.fxml"));

            Parent root = fxmlLoader.load();
            ModifyProductFormController controller = fxmlLoader.getController();
            controller.setProduct(product);
            controller.populateForm();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            setStageProperties(stage);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes selected row from tableview
     */
    @FXML
    private void deleteFromTableView() {
        Part part = partTableView.getSelectionModel().getSelectedItem();
        if (part == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Select an item from the table", ButtonType.OK);
            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you wish to delete the selected entry?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    Inventory.getParts().remove(part);
                }
            });
        }
    }

    /**
     * Deletes selected row from productTableView
     */
    @FXML
    private void deleteFromTableViewProduct() {
        Product product = productTableView.getSelectionModel().getSelectedItem();
        if (product == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Select an item from the table", ButtonType.OK);
            alert.showAndWait();

        } else {
            if (product.getAllAssociatedParts().size() != 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Remove all parts from product to delete", ButtonType.OK);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you wish to delete the selected entry?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.YES) {
                        Inventory.getProducts().remove(product);
                    }
                });
            }
        }
    }


    /**
     * Starts up addProductForm
     */
    @FXML
    public void startAddProductForm() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("form/addProductForm.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            setStageProperties(stage);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stage properties that are shared between all forms that are displayed
     * @param stage
     */
    private static void setStageProperties(Stage stage) {
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
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
            FXMLLoader partTableLoader = new FXMLLoader(getClass().getResource("component/partTableView.fxml"));
            partTableView = partTableLoader.load();
            PartTableViewController partController = partTableLoader.getController();
            partController.setSearchBar(partSearchField);
            partGridPane.getChildren().add(partTableView);

            FXMLLoader productTableLoader = new FXMLLoader(getClass().getResource("component/productTableView.fxml"));
            productTableView = productTableLoader.load();
            ProductTableViewController productController = productTableLoader.getController();
            productController.setSearchBar(productSearchField);
            productGridPane.getChildren().add(productTableView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
