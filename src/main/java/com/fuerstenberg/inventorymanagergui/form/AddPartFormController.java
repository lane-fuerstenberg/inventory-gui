package com.fuerstenberg.inventorymanagergui.form;

import com.fuerstenberg.inventorymanagergui.backend.InHouse;
import com.fuerstenberg.inventorymanagergui.backend.Inventory;
import com.fuerstenberg.inventorymanagergui.backend.Outsourced;
import com.fuerstenberg.inventorymanagergui.backend.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for fxml addPartForm
 */
public class AddPartFormController  {

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
    protected RadioButton inhouseRadio;

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
    protected TextField miscTextField;

    /**
     * fxml accessor
     */
    @FXML
    protected Label miscLabel;


    /**
     * RUNTIME ERROR: Experienced issues with saving the data to inventory and having it populate in the actual tableview
     * Saves data in form to Inventory
     */
    public void saveData() {
        Boolean validData = true;
        int id = -1;
        String name = null;
        double price = -1;
        int inventory = -1;
        int max = -1;
        int min = -1;
        String misc = null;
        Boolean isInHouse = inhouseRadio.isSelected();

        /*
        lazy retrieve code, I didn't see anything specifying how we need to vet our data, so I just decided to use
         try/catch to keep it quick and simple. I know the smarter method would be to prevent text data to be inputted
         in the first place for integer/double fields but that would take time for something that I don't think was a
         requirement
         */
        try {
            id = Inventory.createPartID();
            name = nameTextField.getText();
            price = Double.valueOf(priceTextField.getText());
            inventory = Integer.valueOf(inventoryTextField.getText());
            max = Integer.valueOf(maxTextField.getText());
            min = Integer.valueOf(minTextField.getText());
            misc = miscTextField.getText();

        } catch (Exception e) {
            validData = false;
        }

        //Handling invalid inputs
        //guard clause - exits if true
        if (!hasStockWithinMinMax(inventory, max, min) || !validData || !isMiscFieldValid(misc, isInHouse)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory (Inv) must be a value between min and max and all values must be valid", ButtonType.OK);
            alert.showAndWait();
            return;
        }


        if (isInHouse == true) {
            InHouse part = new InHouse(id, name, price, inventory, min, max);
            part.setMachineId(Integer.valueOf(misc));
            Inventory.addPart(part);
        } else {
            Outsourced part = new Outsourced(id, name, price, inventory, min, max);
            part.setCompanyName(misc);
            Inventory.addPart(part);
        }

        Stage stage = (Stage) vBox.getScene().getWindow();
        stage.close();
    }

    /**
     * @param misc the field to validate
     * @param isInHouse boolean determining conditions for valid data
     * @return
     */
    public Boolean isMiscFieldValid(String misc, Boolean isInHouse) {
        if (isInHouse) {
            return misc.matches("-?\\d+");
        }

        return true;
    }


    /**
     * @param inventory number of inventory
     * @param max maximum for inventory
     * @param min minimum for inventory
     * @return
     */
     static boolean hasStockWithinMinMax(int inventory, int max, int min) {
        return inventory > min && inventory < max;
    }

    /**
     * Updates scene for inhouse
     */
    public void updateSceneInhouse() {
        miscLabel.setText("Machine ID");
    }

    /**
     * updates scene for outsource
     */
    public void updateSceneOutsource() {
        miscLabel.setText("Company Name");
    }

    /**
     * Close the stage when cancel is selected
     */
    public void cancelButton() {
        Stage stage = (Stage) vBox.getScene().getWindow();
        stage.close();
    }
}
