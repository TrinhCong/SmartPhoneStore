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
public class Shipper {
    private int Id;
    private String Name;
    private String PhoneNumber1;
    private String PhoneNumber2;
    
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
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the PhoneNumber1
     */
    public String getPhoneNumber1() {
        return PhoneNumber1;
    }

    /**
     * @param PhoneNumber1 the PhoneNumber1 to set
     */
    public void setPhoneNumber1(String PhoneNumber1) {
        this.PhoneNumber1 = PhoneNumber1;
    }

    /**
     * @return the PhoneNumber2
     */
    public String getPhoneNumber2() {
        return PhoneNumber2;
    }

    /**
     * @param PhoneNumber2 the PhoneNumber2 to set
     */
    public void setPhoneNumber2(String PhoneNumber2) {
        this.PhoneNumber2 = PhoneNumber2;
    }
    
}
