/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;

/**
 *
 * @author tvcpr
 */
public class ProductController extends BaseController{
    

    public ProductController(Connection connect) {
        super(connect);
    }
    public void showFilterMenu(){
        System.out.println("4.Lọc sản phẩm");
        
    }
}
