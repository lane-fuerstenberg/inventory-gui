module com.fuerstenberg.inventorymanagergui {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.fuerstenberg.inventorymanagergui to javafx.fxml;
    exports com.fuerstenberg.inventorymanagergui;
}