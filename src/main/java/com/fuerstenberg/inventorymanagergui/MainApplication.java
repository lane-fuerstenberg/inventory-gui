package com.fuerstenberg.inventorymanagergui;

import com.fuerstenberg.inventorymanagergui.backend.InHouse;
import com.fuerstenberg.inventorymanagergui.backend.Inventory;
import com.fuerstenberg.inventorymanagergui.backend.Part;
import com.fuerstenberg.inventorymanagergui.backend.Product;
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
import java.net.URL;

/**
 * FUTURE ENHANCEMENT: UI for modifyPart forms is slightly off and does not look how I wanted it to look
 *
 * FUTURE ENHANCEMENT: Lots of methods are copied and pasted in situations where it is not required
 * such as ModifyProductFormController.hasStockWithinMinMax()
 *
 * FUTURE ENHANCEMENT: Would have been better to make the Controllers more modular and use inheritance with them but
 * getting fxml to recognize the sub-instances correctly proved to be a challenge, and ultimately it may have just
 * complicated many problems down the line.
 *
 * FUTURE ENHANCEMENT: TableViews all have extremely high coupling with their forms because they require
 * searchbars to be assigned, and they have GridView data assigned in their fxml forms. This is poor practice.
 *
 * FUTURE ENHANCEMENT: For some reason when I refactored classes to go to "component" and "form" directories, they no
 * longer had access to resources directly from getClass().getResource() method call. I had to call
 * MainApplication.class().getResource() to access resources, but I think this was a poor workaround and I ultimately
 * could not find the reason for this behavior.
 *
 * FUTURE ENHANCEMENT: Error handling is extremely lazy and if this was a long term project should have been handled
 * more gracefully. Using try/catch and a single generic message for error handling is poor practice but according to
 * rubric I thought it was sufficient (hopefully I don't jinx this and find out I am wrong on this assumption)
 *
 * FUTURE ENHANCEMENT: The rubric did not list an expected behavior for the situation where a part is removed that
 * already belongs to a product, only the situation where you are deleting a product with parts assigned. I am not
 * sure if this was an oversight but not allowing parts to be deleted that already belong to products seems important
 * and would be another good future enhancement.
 */
public class MainApplication extends Application {

    /**
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            Inventory.init();


            Parent root = FXMLLoader.load(getClass().getResource("form/mainForm.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Javadoc folder is located inside root directory at ./javadoc
    /**
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}