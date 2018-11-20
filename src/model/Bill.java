/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author tvcpr
 */
public class Bill {
private int ID;
    private int CustomerID;
    private int StatusID;
    private int ShipperID;
    private Date CreatedDate;
    private double Total;
    private String ReceivedAddress;
    private String Note;
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
     * @return the CustomerID
     */
    public int getCustomerID() {
        return CustomerID;
    }

    /**
     * @param CustomerID the CustomerID to set
     */
    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    /**
     * @return the StatusID
     */
    public int getStatusID() {
        return StatusID;
    }

    /**
     * @param StatusID the StatusID to set
     */
    public void setStatusID(int StatusID) {
        this.StatusID = StatusID;
    }

    /**
     * @return the ShipperID
     */
    public int getShipperID() {
        return ShipperID;
    }

    /**
     * @param ShipperID the ShipperID to set
     */
    public void setShipperID(int ShipperID) {
        this.ShipperID = ShipperID;
    }

    /**
     * @return the CreatedDate
     */
    public Date getCreatedDate() {
        return CreatedDate;
    }

    /**
     * @param CreatedDate the CreatedDate to set
     */
    public void setCreatedDate(Date CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

    /**
     * @return the Total
     */
    public double getTotal() {
        return Total;
    }

    /**
     * @param Total the Total to set
     */
    public void setTotal(double Total) {
        this.Total = Total;
    }

    /**
     * @return the ReceivedAddress
     */
    public String getReceivedAddress() {
        return ReceivedAddress;
    }

    /**
     * @param ReceivedAddress the ReceivedAddress to set
     */
    public void setReceivedAddress(String ReceivedAddress) {
        this.ReceivedAddress = ReceivedAddress;
    }

    /**
     * @return the Note
     */
    public String getNote() {
        return Note;
    }

    /**
     * @param Note the Note to set
     */
    public void setNote(String Note) {
        this.Note = Note;
    }
    
    
}
