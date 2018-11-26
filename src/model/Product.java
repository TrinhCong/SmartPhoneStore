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

public class Product {

    private int Id;
    private String Name;
    private double Price;
    private String ImagePath;
    private int Quantity;
    private String Dimension;
    private double Weight;
    private String Color;
    private boolean Warranty;
    private String SoundType;
    private Date StartPromotion;
    private Date EndPromotion;
    private String ConnectionType;
    private String Memory;
    private double PromotionPrice;
    private double Battery;
    private double OS;
    private double SDCard;
    private double Camera;
    private double CategoryId;
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

    /**
     * @return the ImagePath
     */
    public String getImagePath() {
        return ImagePath;
    }

    /**
     * @param ImagePath the ImagePath to set
     */
    public void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
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
     * @return the Dimension
     */
    public String getDimension() {
        return Dimension;
    }

    /**
     * @param Dimension the Dimension to set
     */
    public void setDimension(String Dimension) {
        this.Dimension = Dimension;
    }

    /**
     * @return the Weight
     */
    public double getWeight() {
        return Weight;
    }

    /**
     * @param Weight the Weight to set
     */
    public void setWeight(double Weight) {
        this.Weight = Weight;
    }

    /**
     * @return the Color
     */
    public String getColor() {
        return Color;
    }

    /**
     * @param Color the Color to set
     */
    public void setColor(String Color) {
        this.Color = Color;
    }

    /**
     * @return the Warranty
     */
    public boolean isWarranty() {
        return Warranty;
    }

    /**
     * @param Warranty the Warranty to set
     */
    public void setWarranty(boolean Warranty) {
        this.Warranty = Warranty;
    }

    /**
     * @return the SoundType
     */
    public String getSoundType() {
        return SoundType;
    }

    /**
     * @param SoundType the SoundType to set
     */
    public void setSoundType(String SoundType) {
        this.SoundType = SoundType;
    }

    /**
     * @return the StartPromotion
     */
    public Date getStartPromotion() {
        return StartPromotion;
    }

    /**
     * @param StartPromotion the StartPromotion to set
     */
    public void setStartPromotion(Date StartPromotion) {
        this.StartPromotion = StartPromotion;
    }

    /**
     * @return the EndPromotion
     */
    public Date getEndPromotion() {
        return EndPromotion;
    }

    /**
     * @param EndPromotion the EndPromotion to set
     */
    public void setEndPromotion(Date EndPromotion) {
        this.EndPromotion = EndPromotion;
    }

    /**
     * @return the ConnectionType
     */
    public String getConnectionType() {
        return ConnectionType;
    }

    /**
     * @param ConnectionType the ConnectionType to set
     */
    public void setConnectionType(String ConnectionType) {
        this.ConnectionType = ConnectionType;
    }

    /**
     * @return the Memory
     */
    public String getMemory() {
        return Memory;
    }

    /**
     * @param Memory the Memory to set
     */
    public void setMemory(String Memory) {
        this.Memory = Memory;
    }

    /**
     * @return the PromotionPrice
     */
    public double getPromotionPrice() {
        return PromotionPrice;
    }

    /**
     * @param PromotionPrice the PromotionPrice to set
     */
    public void setPromotionPrice(double PromotionPrice) {
        this.PromotionPrice = PromotionPrice;
    }

    /**
     * @return the Battery
     */
    public double getBattery() {
        return Battery;
    }

    /**
     * @param Battery the Battery to set
     */
    public void setBattery(double Battery) {
        this.Battery = Battery;
    }

    /**
     * @return the OS
     */
    public double getOS() {
        return OS;
    }

    /**
     * @param OS the OS to set
     */
    public void setOS(double OS) {
        this.OS = OS;
    }

    /**
     * @return the SDCard
     */
    public double getSDCard() {
        return SDCard;
    }

    /**
     * @param SDCard the SDCard to set
     */
    public void setSDCard(double SDCard) {
        this.SDCard = SDCard;
    }

    /**
     * @return the Camera
     */
    public double getCamera() {
        return Camera;
    }

    /**
     * @param Camera the Camera to set
     */
    public void setCamera(double Camera) {
        this.Camera = Camera;
    }

    /**
     * @return the CategoryId
     */
    public double getCategoryId() {
        return CategoryId;
    }

    /**
     * @param CategoryId the CategoryId to set
     */
    public void setCategoryId(double CategoryId) {
        this.CategoryId = CategoryId;
    }
    
}
