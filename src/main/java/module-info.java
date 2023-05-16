module com.fuerstenberg.inventorymanagergui {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.fuerstenberg.inventorymanagergui to javafx.fxml;
    exports com.fuerstenberg.inventorymanagergui;

    opens com.fuerstenberg.inventorymanagergui.backend to javafx.fxml;
    exports com.fuerstenberg.inventorymanagergui.backend;

    opens com.fuerstenberg.inventorymanagergui.form to javafx.fxml;
    exports com.fuerstenberg.inventorymanagergui.form;

    opens com.fuerstenberg.inventorymanagergui.component to javafx.fxml;
    exports com.fuerstenberg.inventorymanagergui.component;
}