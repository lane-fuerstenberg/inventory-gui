package com.fuerstenberg.inventorymanagergui.backend;

/**
 * Outsourced instance of part class
 */
public class Outsourced extends Part {
    /**
     * The companies name for the outsourced part
     */
    private String companyName;

    /**
     * @param id the id of the part
     * @param name name of part
     * @param price price of part
     * @param stock inventory amount of part
     * @param min minimum inventory requirement
     * @param max maximum inventory requirement
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
    }

    /**
     * @param companyName name to assign
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the company name
     */
    public String getCompanyName() {
        return companyName;
    }
}
