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
    private int Id;
    private int CustomerId;
    private int StatusId;
    private int ShipperId;
    private Date CreatedDate;
    private double Total;
    private String ReceivedAddress;
    private String Note;
    public String StatusName;
    public String ShipperName;
    public String CustomerName;
    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * @return the CustomerId
     */
    public int getCustomerId() {
        return CustomerId;
    }

    /**
     * @param CustomerId the CustomerId to set
     */
    public void setCustomerId(int CustomerId) {
        this.CustomerId = CustomerId;
    }

    /**
     * @return the StatusId
     */
    public int getStatusId() {
        return StatusId;
    }

    /**
     * @param StatusId the StatusId to set
     */
    public void setStatusId(int StatusId) {
        this.StatusId = StatusId;
    }

    /**
     * @return the ShipperId
     */
    public int getShipperId() {
        return ShipperId;
    }

    /**
     * @param ShipperId the ShipperId to set
     */
    public void setShipperId(int ShipperId) {
        this.ShipperId = ShipperId;
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
