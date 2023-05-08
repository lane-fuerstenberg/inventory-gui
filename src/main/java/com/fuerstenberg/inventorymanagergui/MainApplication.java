package com.fuerstenberg.inventorymanagergui;

import com.fuerstenberg.inventorymanagergui.backend.InHouse;
import com.fuerstenberg.inventorymanagergui.backend.Inventory;
import com.fuerstenberg.inventorymanagergui.backend.Part;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    protected Inventory inventory;

    @Override
    public void start(Stage stage) throws IOException {
        try {
            Inventory.init();

            Parent root = FXMLLoader.load(getClass().getResource("mainForm.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}