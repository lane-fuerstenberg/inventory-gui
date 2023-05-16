package com.fuerstenberg.inventorymanagergui.backend;

/**
 * Represents InHouse instance of a part with machine ID
 */
public class InHouse extends Part {
    /**
     * The ID of the machine
     */
    private int machineId;

    /**
     * Constructor creating InHouse part instance
     * @param id part id
     * @param name part name
     * @param price parts price
     * @param stock inventory or number of parts in stock
     * @param min minimum number of parts required to be in stock
     * @param max maximum number of parts required to be in stock
     */
    public InHouse(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
    }


    /**
     * Sets machine ID instance attribute
     * @param machineId
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }


    /**
     * Return instance ID
     * @return instance ID
     */
    public int getMachineId() {
        return machineId;
    }
}
