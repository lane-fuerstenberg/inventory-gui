package com.fuerstenberg.inventorymanagergui.backend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    ObservableList<Part> allParts;
    ObservableList<Product> allProducts;

    private Inventory() {
        allParts = FXCollections.observableArrayList();
        allProducts = FXCollections.observableArrayList();
    }

    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public Part lookupPart(int partId) {
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }

        return null;
    }

    public Product lookupProduct(int productId) {
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }

        return null;
    }

    public ObservableList<Part> lookupPart(String partName) {
        //ObservableList<Part> p = new ObservableList<Part>();
        //string version
        return null;
    }

    public ObservableList<Product> lookupProduct(String productName) {
        //string version lookup
        return null;
    }

    public void updatePart(int index, Part newPart) {
        allParts.set(index, newPart);
    }

    public void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }
}
