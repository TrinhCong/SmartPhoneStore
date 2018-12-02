/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import model.DBConnection;

/**
 *
 * @author tvcpr
 */
public class ProductController {
    
    private Connection connection;

    public ProductController(Connection connect) {
        connection = connect;
    }
    
   //menu ProductController    
    public void showFilterMenu(){
    	Scanner sc=new Scanner(System.in);
        int choice1;
        do {
        	System.out.println("-----Filter Products-----");
            System.out.println("1.Filter by Price");
            System.out.println("2.Filter by Category");
            System.out.println("3.Exit");
            System.out.println("-------------------------");
        	System.out.print("Please input a number: ");      	
        	choice1 = sc.nextInt();
        	switch (choice1) {
            	case 1:
            		filterByPrice();
            		break;
            	case 2:
            		filterByCategory();
            		break;
            	case 3:
            		System.exit(0);
            		break;
            	default:
            		System.out.println("\nYou type the wrong command! Please retype\n");
            		break;
            }
        } while (choice1!=3);        
    }
    
    //Loc theo gia    
    public void filterByPrice() {
    	Scanner sc=new Scanner(System.in);
        double minprice;
        double maxprice;
        String str1;
        String str2;
        do {
        	System.out.println("\n---Enter the price you want--- ");
        	// nhap minprice
        	System.out.println("Min Price: ");
    		str1=sc.nextLine();    		
    		if (str1.trim().equals("")) {
                System.out.println("\nPlease input something!!!\n");
                continue;
            }
    		try {
                minprice = Double.parseDouble(str1);
            } catch (NumberFormatException ex) {
                System.out.println("\nYou must input number. Please retype!!!\n");
                continue;
            }
    		// nhap maxprice
    		System.out.println("Max Price: ");
    		str2=sc.nextLine();   		
    		if (str2.trim().equals("")) {
                System.out.println("\nPlease input something!!!\n");
                continue;
            }
    		try {
                maxprice = Double.parseDouble(str2);
            } catch (NumberFormatException ex) {
                System.out.println("\nYou must input number. Please retype!!!\n");
                continue;
            }
    		
        	//bat loi minprice lon hon maxprice           	        	
        	if (minprice > maxprice) {
        		System.out.println("MinPrice < MaxPrice. Please retype!!!");
        		continue;
        	}
        	else {
        		break;
        	}       	
        } while (true);
        
        
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from Products where (Price>="+minprice+" and Price<="+maxprice+")");
            while(rs.next()){
                System.out.println("id: "+rs.getString(1)+"\tName: " + rs.getString(2) + "\tPrice: "+rs.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("\nError unknown!!!\n"+e);
        }    
        //xem chi tiet san pham
        int choice;
    	do {
    		System.out.println("\n---You want continue???---");
    		System.out.println("1.See product's details");
    		System.out.println("2.Back to menu FilterByPrice");
    		System.out.println("----------------------------");
    		System.out.print("Enter command you want: ");
    		choice = sc.nextInt();
        	switch (choice) {
            	case 1:
            		productDetails();
            		break;
            	case 2:
            		filterByPrice();
            		break;
            	default:
            		System.out.println("\nYou type the wrong command! Please retype\n");
            		break;
        	}
    	} while(true);
    }
    
        
   // loc theo thuong hieu
    public void filterByCategory() {
    	Scanner sc=new Scanner(System.in);
        Statement stm = null;
        ResultSet rs= null;
        int ID;
        String str1;
        do {
        	System.out.println("\n---Please choose category you want to see---\n");
        	System.out.println("1.Apple");
        	System.out.println("2.Samsung");
        	System.out.println("3.MobiiStar");
        	System.out.println("4.Nokia");
        	System.out.println("-------------------------");
        	System.out.print("Please input a number: ");                		
        	str1=sc.nextLine();    
        	System.out.print("\n");
    		if (str1.trim().equals("")) {
                System.out.println("\nPlease input something!!!\n");
                continue;
            }
    		try {
                ID = Integer.parseInt(str1);
            } catch (NumberFormatException ex) {
                System.out.println("\nYou must input number. Please retype!!!\n");
                continue;
            }
        	try {
                stm = connection.createStatement();
                rs = stm.executeQuery("Select Products.Id,Products.Name,Products.Price,Products.BrandId,BrandCategory.Name from Products,BrandCateGory where BrandCategory.Id ="+ID+" and Products.BrandId="+ID);
                while(rs.next()){
                    System.out.println("id: "+rs.getString(1)+"\tName: " + rs.getString(2) + "\tPrice: "+rs.getString(3) + "\tBrandId: "+rs.getString(4) + "\tBrandName: "+rs.getString(5));
                }
             } catch (SQLException e) {
                 System.out.println("\nError unknown!!!\n"+e);
             } 
        } while(true);
        
        // loi chua biet,chay tren filterbyprice thi chay dc
        /*int choice;
    	do {
    		System.out.println("\n---You want continue???---");
    		System.out.println("1.See product's details");
    		System.out.println("2.Back to menu FilterByCategory");
    		System.out.println("----------------------------");
    		System.out.print("Enter command you want: ");
    		choice = sc.nextInt();
        	switch (choice) {
            	case 1:
            		productDetails();
            		break;
            	case 2:
            		filterByCategory();
            		break;
            	default:
            		System.out.println("\nYou type the wrong command! Please retype\n");
            		break;
        	}
    	} while(true);*/
        
          	
    }
    
    // method xem chi tiet san pham
    public void productDetails() {
    	Scanner sc=new Scanner(System.in);
    	int ID;
    	String str;
    	
    	do {
    		System.out.print("\n");
        	System.out.print("Enter Product's ID you want to see details: ");
        	System.out.print("\n");
        	str=sc.nextLine();       	
        	if (str.trim().equals("")) {
                System.out.println("\nPlease input something!!!\n");
                continue;
            }
        	
        	try {
        		ID=Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("\nYou must input number. Please retype!!!\n");
                continue;
            }
        	
        	if (ID>0) {
        		break;
        	} else {
        		System.out.println("\nInput a number larger than 0!!!\n");
        		continue;
        	}
        	
    	} while (true);
    	
    	   	
    	try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("Select * from Products where ID="+ID);
            while(rs.next()){
                System.out.println("id: "+rs.getString(1)+"\nName: " + rs.getString(2) + "\nPrice: "+rs.getString(3) + "\nBrandId: "+rs.getString(4) + "\nBrandName: "+rs.getString(5)+"\n");
            }
        } catch (SQLException e) {
            System.out.println("\nError unknown!!!\n"+e);
        }
    	
    }
    
    
       
}
