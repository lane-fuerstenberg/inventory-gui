package com.fuerstenberg.inventorymanagergui;

import com.fuerstenberg.inventorymanagergui.backend.InHouse;
import com.fuerstenberg.inventorymanagergui.backend.Inventory;
import com.fuerstenberg.inventorymanagergui.backend.Part;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    public void startAddPartForm() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("partForm.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            partFormController.init(root, stage, partFormController.FormType.ADD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void startModifyPartForm() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("partForm.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            partFormController.init(root, stage, partFormController.FormType.MODIFY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //part table
        partIdColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        partTableView.setItems(getParts());

        //product table
    }

    public ObservableList<Part> getParts() {
        ObservableList<Part> parts = Inventory.getParts();
        parts.add(new InHouse(21321, "test", 12, 2, 1, 3));

        return parts;
    }
}
