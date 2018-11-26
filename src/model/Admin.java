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
public class Admin {

    private int Id;
    private String UserName;
    private String DisplayName;
    private String PassWord;
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
     * @return the UserName
     */
    public String getUserName() {
        return UserName;
    }

    /**
     * @param UserName the UserName to set
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    /**
     * @return the DisplayName
     */
    public String getDisplayName() {
        return DisplayName;
    }

    /**
     * @param DisplayName the DisplayName to set
     */
    public void setDisplayName(String DisplayName) {
        this.DisplayName = DisplayName;
    }

    /**
     * @return the PassWord
     */
    public String getPassWord() {
        return PassWord;
    }

    /**
     * @param PassWord the PassWord to set
     */
    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }
}
