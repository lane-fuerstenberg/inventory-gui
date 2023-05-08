package com.fuerstenberg.inventorymanagergui;

import com.fuerstenberg.inventorymanagergui.backend.InHouse;
import com.fuerstenberg.inventorymanagergui.backend.Inventory;
import com.fuerstenberg.inventorymanagergui.backend.Outsourced;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class partFormController {
    public enum FormType {MODIFY, ADD};

    private static Parent root;
    private static Stage stage;
    private static FormType formType;

    @FXML
    private RadioButton inhouseRadio;

    @FXML
    private TextField idTextField;

    @FXML
    private  TextField nameTextField;

    @FXML
    private TextField inventoryTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField maxTextField;

    @FXML
    private TextField minTextField;

    @FXML
    private TextField miscTextField;

    @FXML
    private Label miscLabel;

    public static void init(Parent root, Stage stage, FormType formType) {
        partFormController.root = root;
        partFormController.stage = stage;
        partFormController.formType = formType;
    }

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
            id = Integer.valueOf(idTextField.getText());
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


        if (inhouseRadio.isSelected() == true) {
            Inventory.addPart(new InHouse(id, name, price, inventory, min, max));
        } else {
            Inventory.addPart(new Outsourced(id, name, price, inventory, min, max));
        }

        //todo: idk what the last field is for i need to go back later

        clearTextFields();
    }

    private void clearTextFields() {
        idTextField.setText("");
        nameTextField.setText("");
        inventoryTextField.setText("");
        priceTextField.setText("");
        maxTextField.setText("");
        minTextField.setText("");
        miscTextField.setText("");
    }

    private static boolean hasStockWithinMinMax(int inventory, int max, int min) {
        return inventory > min && inventory < max;
    }

    public void updateSceneInhouse() {
        miscLabel.setText("Machine ID");
    }

    public void updateSceneOutsource() {
        miscLabel.setText("Company Name");
    }

    public void cancelButton() {
        stage.close();
    }
}
