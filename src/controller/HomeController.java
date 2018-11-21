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
public class HomeController {
    
    private Connection connection;

    public HomeController(Connection connect) {
        connection = connect;
    }
    public void ShowMenu(){
        System.out.println("Cửa sổ trang chủ");
    }
}
