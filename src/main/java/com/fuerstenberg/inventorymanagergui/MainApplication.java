package com.fuerstenberg.inventorymanagergui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Control > Layout > Scene > Stage

        //control
        //init product table
        TableView<Product> productTable = new TableView<>();

        productTable.setPrefWidth(400);

        TableColumn idCol = new TableColumn<Product, Integer>("ID");
        productTable.getColumns().add(idCol);
        TableColumn nameCol = new TableColumn<Product, String>("Name");
        productTable.getColumns().add(nameCol);
        TableColumn stockCol = new TableColumn<Product, Double>("Stock");
        productTable.getColumns().add(stockCol);
        TableColumn minCol = new TableColumn<Product, Integer>("Minimum");
        productTable.getColumns().add(minCol);
        TableColumn maxCol = new TableColumn<Product, Integer>("Maximum");
        productTable.getColumns().add(maxCol);

        //init part table

        TableView<Part> partTable = new TableView<>();


        //new Product(111, "test", 10.1, 1, 2)
        productTable.getItems().add(new Product(111, "test", 10.1, 1, 2));

        //layout

        VBox singlePaneProduct = getSinglePaneProduct(productTable);


        VBox singlePanePart = new VBox();
        singlePanePart.getChildren().add(partTable);


        HBox doublePane = new HBox();
        doublePane.setPrefWidth(450);
        doublePane.setSpacing(10);
        doublePane.getChildren().add(singlePaneProduct);
        doublePane.getChildren().add(singlePanePart);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(doublePane);
        //layout.getChildren().add(tableView1);


        //scene
        Scene scene = new Scene(mainLayout);

        //stage
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setWidth(800);
        stage.setHeight(500);
        stage.show();
    }

    private static VBox getSinglePaneProduct(TableView<Product> productTable) {
        VBox singlePaneProduct = new VBox();
        singlePaneProduct.getChildren().add(productTable);

        HBox buttonRowProduct = new HBox();
        Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                VBox leftPane = new VBox();

                //leftGrid control init
                Text idText = new Text("ID");
                TextField idTextField = new TextField();

                Text nameText = new Text("Name");
                TextField nameTextField = new TextField();

                Text invText = new Text("Inv");
                TextField invTextField = new TextField();

                Text priceText = new Text("Price");
                TextField priceTextField = new TextField();

                Text maxText = new Text("Max");
                TextField maxTextField = new TextField();

                Text minText = new Text("Min");
                TextField minTextField = new TextField();




                GridPane leftGrid = new GridPane();
                leftGrid.add(idText, 0, 0, 1, 1);
                leftGrid.add(idTextField, 1, 0, 1, 1);
                leftGrid.add(nameText, 0, 1, 1, 1);
                leftGrid.add(nameTextField, 1, 1, 1, 1);
                leftGrid.add(invText, 0, 2, 1, 1);
                leftGrid.add(invTextField, 1, 2, 1, 1);
                leftGrid.add(priceText, 0, 3, 1, 1);
                leftGrid.add(priceTextField, 1, 3, 1, 1);
                leftGrid.add(maxText, 0, 4, 1, 1);
                leftGrid.add(maxTextField, 1, 4, 1, 1);
                leftGrid.add(minText, 2, 4, 1, 1);
                leftGrid.add(minTextField, 3, 4, 1, 1);

                leftGrid.setHgap(10);
                leftGrid.setVgap(20);

                leftPane.getChildren().add(leftGrid);
                VBox rightPane = new VBox();

                HBox mainPane = new HBox();
                mainPane.getChildren().add(leftPane);
                mainPane.getChildren().add(rightPane);
                mainPane.setAlignment(Pos.CENTER);


                //scene initiating
                Scene scene = new Scene(mainPane);
                //stage initiating
                Stage productStage = new Stage();
                productStage.setScene(scene);
                productStage.initModality(Modality.APPLICATION_MODAL);
                productStage.setWidth(800);
                productStage.setHeight(500);
                productStage.show();
            }
        });
        buttonRowProduct.getChildren().add(addButton);


        Button modifyButton = new Button("Modify");
        modifyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("modify test");
            }
        });
        buttonRowProduct.getChildren().add(modifyButton);

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("delete test");
            }
        });
        buttonRowProduct.getChildren().add(deleteButton);
        singlePaneProduct.getChildren().add(buttonRowProduct);
        return singlePaneProduct;
    }

    public static void main(String[] args) {
        launch();
    }
}