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
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private GridPane gridPane;




    @FXML
    public void startAddPartForm() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addPartForm.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            setStageProperties(stage);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void startModifyPartForm() {
        try {
            Part part = partTableView.getSelectionModel().getSelectedItem();
            if (part == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Select an item from the table", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifyPartForm.fxml"));

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

    private static void setStageProperties(Stage stage) {
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("partTableView.fxml"));
            partTableView = fxmlLoader.load();
            gridPane.getChildren().add(partTableView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Part> getParts() {
        ObservableList<Part> parts = Inventory.getParts();
        parts.add(new InHouse(21321, "test", 12, 2, 1, 3));

        return parts;
    }
}
