/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import java.util.ArrayList;
import model.Bill;
import model.BillDetail;
import model.Customer;
import model.Product;

/**
 *
 * @author tvcpr
 */
public class BillInfo {

    public BillInfo() {
        Customer = new Customer();
        Bill = new Bill();
        BillDetails = new ArrayList<>();
    }
    public Customer Customer;
    public Bill Bill;
    public ArrayList<BillDetail> BillDetails;

    public boolean addToCart(Product product, int quantity) {
        boolean isExist = false;
        for (BillDetail item : BillDetails) {
            if (item.getProductId() == product.getId()) {
                isExist = true;
                item.setQuantity(item.getQuantity() + quantity);
                item.setPrice(item.getPrice() + product.getPrice() * quantity);
                break;
            }
        }
        if (!isExist) {
            BillDetail bdItem = new BillDetail();
            bdItem.setProductId(product.getId());
            bdItem.setQuantity(quantity);
            bdItem.setPrice(product.getPrice() * quantity);
            bdItem.ProductName = product.getName();
            BillDetails.add(bdItem);
        }
        return true;
    }

    public boolean removeFromCart(Product product, int quantity) {
        BillDetail removeItem=null;
        boolean isExist=false;
        for (BillDetail item : BillDetails) {
            if (item.getProductId() == product.getId()) {
                if (item.getQuantity() >= quantity) {
                    item.setQuantity(item.getQuantity() - quantity);
                    item.setPrice(item.getPrice() - product.getPrice() * quantity);
                }
                else{
                    removeItem=item;
                }
                
                break;
            }
        }
        if(removeItem!=null)
            BillDetails.remove(removeItem);
        return isExist;
    }
}
