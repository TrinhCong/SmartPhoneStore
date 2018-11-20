/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author tvcpr
 */
public class BillDetail {

    private int ID;
    private int BillID;
    private int ProductID;
    private int Quantity;
    private double Price;
    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the BillID
     */
    public int getBillID() {
        return BillID;
    }

    /**
     * @param BillID the BillID to set
     */
    public void setBillID(int BillID) {
        this.BillID = BillID;
    }

    /**
     * @return the ProductID
     */
    public int getProductID() {
        return ProductID;
    }

    /**
     * @param ProductID the ProductID to set
     */
    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    /**
     * @return the Quantity
     */
    public int getQuantity() {
        return Quantity;
    }

    /**
     * @param Quantity the Quantity to set
     */
    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    /**
     * @return the Price
     */
    public double getPrice() {
        return Price;
    }

    /**
     * @param Price the Price to set
     */
    public void setPrice(double Price) {
        this.Price = Price;
    }
}
