package com.fuerstenberg.inventorymanagergui.backend;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The product instance class
 */
public class Product {
    /**
     * List of all parts associated with product
     */
    private ObservableList<Part> associatedParts;
    /**
     * ID of product
     */
    private int id;

    /**
     * Name of product
     */
    private String name;

    /**
     * Price of product
     */
    private double price;

    /**
     * Inventory of product
     */
    private int stock;

    /**
     * Minimum number of product
     */
    private int min;

    /**
     * Max inventory of product
     */
    private int max;

    /**
     * @param id ID of product
     * @param name name of product
     * @param stock inventory of product
     * @param min min inventory of product
     * @param max max inventory of product
     * @param price price of product
     */
    public Product(int id, String name, int stock, int min, int max, double price) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.price = price;
        associatedParts = FXCollections.observableArrayList();
    }


    /**
     * @param id ID of product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param name name to assign to product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param price price to assign to product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @param stock stock to assign to product
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @param min integer minimum to assign
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @param max integer maximum to assign
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @return gets ID of product
     */
    public int getId() {
        return id;
    }


    /**
     * @return gets name of product
     */
    public String getName() {
        return name;
    }


    /**
     * @return gets price of product
     */
    public double getPrice() {
        return price;
    }


    /**
     * @return gets stock of product
     */
    public int getStock() {
        return stock;
    }


    /**
     * @return gets minimum of product
     */
    public int getMin() {
        return min;
    }


    /**
     * @return gets maximum of product
     */
    public int getMax() {
        return max;
    }


    /**
     * @param part adds part to associated parts
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * @param selectedAssociatedPart removes this part from associated parts
     */
    public void deleteAssociatedPart(Part selectedAssociatedPart) {
        associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * @return all associated parts with product
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
