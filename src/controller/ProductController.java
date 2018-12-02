/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enums.EnumPosition;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tvcpr
 */
public class ProductController extends BaseController {

    //menu ProductController    
    public ProductController(Connection connect) {
        super(connect);
    }

    public void showFilterMenu() {
        makeSpace(EnumPosition.DASH_TOP);
        System.out.println("-----Filter Products-----");
        System.out.println("1.Filter by Price");
        System.out.println("2.Filter by Brand");
        System.out.println("3.Back to previous menu");
        System.out.println("-------------------------");
    }

    public void manageFilterMenu() {
        int choice;
        do {
            showFilterMenu();
            choice = enterNumber("Option");
            switch (choice) {
                case 1:
                    filterByPrice();
                    break;
                case 2:
                    filterByBrand();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 3);
    }

    //Loc theo gia    
    public void filterByPrice() {
        double minprice;
        double maxprice;
        do {
            minprice = enterNumber("Min price");
            maxprice = enterNumber("Max price");
            //bat loi minprice lon hon maxprice           	        	
            if (minprice > maxprice) {
                System.out.println("Min price must be less or equal than max price!\n Re-enter:");
            } else {
                break;
            }
        } while (true);

        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("select * from Products where (Price>=" + minprice + " and Price<=" + maxprice + ")");
            System.out.println("\nResult list:");
            while (rs.next()) {
                System.out.println("id: " + rs.getString(1) + "\tName: " + rs.getString(2) + "\tPrice: " + rs.getString(3));
            }
        } catch (SQLException e) {
            exitByError();
        }
        //xem chi tiet san pham
        int choice;
        do {
            System.out.println("\nOptions:");
            System.out.println("  1.See product's details");
            System.out.println("  2.Continue filter by price");
            System.out.println("  3.Back to previous menu");
            System.out.println("----------------------------");
            choice = enterNumber("Option");
            switch (choice) {
                case 1:
                    productDetails();
                    break;
                case 2:
                    filterByPrice();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 3);
    }

    // loc theo thuong hieu
    public void filterByBrand() {
        Statement stm;
        ResultSet rs;
        int ID;
        boolean isValid;
        boolean isStop;

        do {
            isStop = false;
            try {
                stm = connection.createStatement();
                System.out.println("\n---Brand Selection Menu---\n");
                rs = stm.executeQuery("Select * from BrandCategory");
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        System.out.println("ID: " + rs.getString(1) + " - " + rs.getString(2));
                    }
                    System.out.println("-------------------------");
                    do {
                        isValid = false;
                        ID = enterNumber("ID");
                        rs = stm.executeQuery("Select * from BrandCategory where Id=" + ID);
                        if (rs.isBeforeFirst()) {
                            isValid = true;
                            rs = stm.executeQuery("Select Products.Id,Products.Name,Products.Price,Products.BrandId,BrandCategory.Name from Products,BrandCategory where BrandCategory.Id =" + ID + " and Products.BrandId=" + ID);
                            if (rs.isBeforeFirst()) {
                                System.out.println("\nFilter result list:");
                                while (rs.next()) {
                                    System.out.println("  ID: " + rs.getString(1) + "\tName: " + rs.getString(2) + "\tPrice: " + rs.getString(3) + "\tBrandId: " + rs.getString(4) + "\tBrandName: " + rs.getString(5));
                                }

                            } else {
                                System.out.println("\nNo product found!\n");
                            }
                        } else {
                            System.out.println("Brand is not exist!\nRe-enter:");
                        }
                    } while (!isValid);

                } else {
                    System.out.println("No brand exist!\n Back to main menu!");
                }
            } catch (SQLException e) {
                exitByError();
            }
            System.out.println("\nDo you want to continue? (Y/N)");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("y")) {
                isStop = true;
            }
        } while (!isStop);

    }

    // method xem chi tiet san pham
    public void productDetails() {
        int ID = enterNumber("ID");
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("Select * from Products where ID=" + ID);
            while (rs.next()) {
                System.out.println("id: " + rs.getString(1) + "\nName: " + rs.getString(2) + "\nPrice: " + rs.getString(3) + "\nBrandId: " + rs.getString(4) + "\nBrandName: " + rs.getString(5) + "\n");
            }
        } catch (SQLException e) {
            exitByError();
        }

    }

    public void showProductEditor() {
        makeSpace(EnumPosition.DASH_TOP);
        System.out.println("-----Products infomation Editor-------");
        showAll();
        System.out.println("Options:");
        System.out.println("\t1.Add Product");
        System.out.println("\t2.Edit Product");
        System.out.println("\t3.Delete Product");
        System.out.println("\t4.Watch Product Detail");
        System.out.println("\t5.Back to previous page");
    }

    public void manageMenu() {
        int choice;
        do {
            showProductEditor();
            choice = enterNumber("Option");
            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    edit();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    showDetail();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Option is invalid!");
                    break;
            }
        } while (choice != 5);
    }

    public void showAll() {
        try {

            Statement st = connection.createStatement();
            ResultSet r = st.executeQuery("select * from Products");
            if (r.isBeforeFirst()) {
                System.out.println("\nStore's product list:");
                while (r.next()) {
                    System.out.println("\tid: " + r.getString(1) + "\tName: " + r.getString(2));
                }

            } else {
                makeSpace(EnumPosition.DASH_TOP);
                System.out.println("Store has no product!");
            }

        } catch (SQLException ex) {
            exitByError();
        }
        System.out.println("\n");
    }

    public void add() {
        System.out.println("Add Product done");
    }

    public void delete() {
        System.out.println("Delete Product done");
    }

    public void edit() {
        System.out.println("Edit Product done");
    }
    public void showDetail(){
        int Id=enterNumber("Product Id");
        showDetailById(Id);
    }
    public void showDetailById(int Id) {
        try {
            Statement st = connection.createStatement();
            ResultSet r = st.executeQuery("select p.*,b.Name as BrandName from Products p,BrandCategory b where p.BrandId=b.Id and  p.Id=" + Id);
            if (r.isBeforeFirst()) {
                while (r.next()) {
                    System.out.println("Product detail:");
                    System.out.println(" ID: " + r.getString(1)
                            + "\n Name: " + r.getString(2)
                            + "\n Brand Name: " + r.getString(21)
                            + "\n Price: " + r.getString(3)
                            + "\n ImagePath: " + r.getString(4)
                            + "\n Quantity: " + r.getString(5)
                            + "\n Dimension: " + r.getString(6)
                            + "\n Weight: " + r.getString(7)
                            + "\n Color: " + r.getString(8)
                            + "\n Warranty: " + r.getString(9)
                            + "\n SoundType: " + r.getString(10)
                            + "\n StartPromotion: " + r.getString(11)
                            + "\n EndPromotion: " + r.getString(12)
                            + "\n ConnectionType: " + r.getString(13)
                            + "\n Memory: " + r.getString(14)
                            + "\n PromotionPrice: " + r.getString(15)
                            + "\n Battery: " + r.getString(16)
                            + "\n OS: " + r.getString(17)
                            + "\n SDCard: " + r.getString(18)
                            + "\n Camera: " + r.getString(19)
                            + "\n CategoryId: " + r.getString(20));
                }
            }
            else
            {
                System.out.println("The product has Id="+Id+" doesn't exist!");
            }

        } catch (SQLException ex) {
            exitByError();
        }
    }
}
