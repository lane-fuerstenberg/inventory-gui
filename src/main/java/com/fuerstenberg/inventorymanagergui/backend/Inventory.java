package com.fuerstenberg.inventorymanagergui.backend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Inventory class is actively used to store all part and product information from the start of running main application
 * This class requires init() to be called statically before it is accessible
 */
public class Inventory {
    /**
     * Array instance storing all parts for the session
     */
    private static ObservableList<Part> allParts;

    /**
     * Array instance storing all products for the session
     */
    private static ObservableList<Product> allProducts;

    /**
     * Increment for ID generation
     */
    private static int idCounterPart = 0;

    /**
     * Increment for ID generation
     */
    private static int idCounterProduct = 0;

    /**
     * Creates part ID
     * @return part ID
     */
    public static synchronized Integer createPartID()
    {
        return ++idCounterPart;
    }

    /**
     * Creates product ID
     * @return product ID
     */
    public static synchronized Integer createProductID()
    {
        return ++idCounterProduct;
    }

    /**
     * Instantiates part and product arraylists
     */
    public static void init() {
        allParts = FXCollections.observableArrayList();
        allProducts = FXCollections.observableArrayList();
    }

    /**
     * Gets all parts
     * @return All parts in inventory
     */
    public static ObservableList<Part> getParts() {
        return allParts;
    }

    /**
     * Gets all product
     * @return All products in inventory
     */
    public static ObservableList<Product> getProducts() {
        return allProducts;
    }

    /**
     * Adds a part to inventory
     * @param newPart The part to add to inventory
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Adds a product to inventory
     * @param newProduct The product to add to inventory
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Looks up part by part ID
     * @param partId the parts ID code
     * @return searched part
     */
    public static Part lookupPart(int partId) {
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }

        return null;
    }

    /**
     * Look up product by product ID
     * @param productId the products ID code
     * @return searched product
     */
    public static Product lookupProduct(int productId) {
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }

        return null;
    }


    /**
     * Looks up and returns a list of all matching parts
     * @param partName the part name to search by
     * @return List of all matching parts
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> newParts = FXCollections.observableArrayList();
        for (Part p : getParts()) {
            if (p.getName() == partName) {
                newParts.add(p);
            }
        }

        return newParts;
    }

    /**
     * Looks up and returns a list of all matching products
     * @param productName the name of the product to search by
     * @return List of all matching products
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> newProducts = FXCollections.observableArrayList();
        for (Product p : getProducts()) {
            if (p.getName() == productName) {
                newProducts.add(p);
            }
        }

        return newProducts;
    }

    /**
     * Updates part in inventory
     * @param index index to update the part at
     * @param newPart the new part to replace with
     */
    public static void updatePart(int index, Part newPart) {
        allParts.set(index, newPart);
    }

    /**
     * Updates product in inventory
     * @param index index to update product at
     * @param newProduct the new product to replace with
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }
}
