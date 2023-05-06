package com.fuerstenberg.inventorymanagergui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public void test() throws IOException {
        System.out.println("test");
        Parent root = FXMLLoader.load(getClass().getResource("addPartForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
