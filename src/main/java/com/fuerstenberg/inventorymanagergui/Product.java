package com.fuerstenberg.inventorymanagergui;


import javafx.collections.ObservableList;

public class Product {
    private ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private double stock;
    private int min;
    private int max;

    public Product(int id, String name, double stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getStock() {
        return stock;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public void deleteAssociatedPart(Part selectedAssociatedPart) {
        associatedParts.remove(selectedAssociatedPart);
    }

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
