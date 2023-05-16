package com.fuerstenberg.inventorymanagergui.form;

import com.fuerstenberg.inventorymanagergui.backend.InHouse;
import com.fuerstenberg.inventorymanagergui.backend.Inventory;
import com.fuerstenberg.inventorymanagergui.backend.Outsourced;
import com.fuerstenberg.inventorymanagergui.backend.Part;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for fxml modifyPartForm
 */
public class ModifyPartFormController  {

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
     * fxml accessor
     */
    @FXML
    protected Button saveButton;

    /**
     * fxml accessor
     */
    @FXML
    protected Button cancelButton;

    /**
     * The current part being manipulated from inventory
     */
    private Part part;


    /**
     * Called when modifyPartForm is started to assign part
     * @param part part that is being modified
     */
    public void setPart(Part part) {
        this.part = part;
    }

    /**
     * RUNTIME ERROR: modifying part seems to require the index to replace the old part. Other methods of modifying did
     * not work.
     * Save the new data over the old part
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
            id = Integer.valueOf(idTextField.getText());
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


        if (inhouseRadio.isSelected() == true) {
            InHouse newPart = new InHouse(id, name, price, inventory, min, max);
            newPart.setMachineId(Integer.parseInt(misc));
            Inventory.updatePart(getPartIndex(part), newPart);

        } else {
            Outsourced newPart = new Outsourced(id, name, price, inventory, min, max);
            newPart.setCompanyName(misc);
            Inventory.updatePart(getPartIndex(part), newPart);
        }

        Stage stage = (Stage) vBox.getScene().getWindow();
        stage.close();
    }

    /**
     * @param part part searching for
     * @return index
     */
    public int getPartIndex(Part part) {
        for(int i = 0; i < Inventory.getParts().size(); i++) {
            if (part == Inventory.getParts().get(i)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * @param misc
     * @param isInHouse
     * @return
     */
    public Boolean isMiscFieldValid(String misc, Boolean isInHouse) {
        if (isInHouse) {
            return misc.matches("-?\\d+");
        }

        return true;
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
     * updates scene as inhouse
     */
    public void updateSceneInhouse() {
        miscLabel.setText("Machine ID");
    }

    /**
     * updates scene as outsourced
     */
    public void updateSceneOutsource() {
        miscLabel.setText("Company Name");
    }

    /**
     * closes stage
     */
    public void cancelButton() {
        Stage stage = (Stage) vBox.getScene().getWindow();
        stage.close();
    }


    /**
     * Populates form with assigned part
     */
    public void populateForm() {
        idTextField.setText(Integer.toString(part.getId()));
        nameTextField.setText(part.getName());
        priceTextField.setText(Double.toString(part.getPrice()));
        inventoryTextField.setText(Integer.toString(part.getStock()));
        maxTextField.setText(Integer.toString(part.getMax()));
        minTextField.setText(Integer.toString(part.getMin()));

        String miscField;
        if (part instanceof InHouse) {
            int machineId = ((InHouse) part).getMachineId();
            miscField = Integer.toString(machineId);
        } else {
            miscField = ((Outsourced) part).getCompanyName();
        }

        miscTextField.setText(miscField);
    }
}
