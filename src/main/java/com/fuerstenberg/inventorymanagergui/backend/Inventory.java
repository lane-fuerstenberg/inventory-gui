package com.fuerstenberg.inventorymanagergui.backend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts;
    private static ObservableList<Product> allProducts;

    public static void init() {
        allParts = FXCollections.observableArrayList();
        allProducts = FXCollections.observableArrayList();
    }

    public static ObservableList<Part> getParts() {
        return allParts;
    }

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId) {
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }

        return null;
    }

    public static Product lookupProduct(int productId) {
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }

        return null;
    }

    public static ObservableList<Part> lookupPart(String partName) {
        //ObservableList<Part> p = new ObservableList<Part>();
        //string version
        return null;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        //string version lookup
        return null;
    }

    public static void updatePart(int index, Part newPart) {
        allParts.set(index, newPart);
    }

    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }
}
