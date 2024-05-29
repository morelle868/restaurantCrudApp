/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reataurantcrud.resources;
import java.util.*;
import java.sql.*;
/**
 *
 * @author Morelle
 */
public class DatabaseConnection {
    private String url = "jdbc:mysql://localhost/restaurant";
    private String user = "root";
    private String pass = "";
    
    private static Connection connex;
    
    private DatabaseConnection(){
    try{
    
        Class.forName("com.mysql.jdbc.Driver");
    }catch(Exception e){
        System.out.println("Driver erreur :"+e.getMessage());
    }
    
    try{
        connex = DriverManager.getConnection(url,user,pass);
    }catch(Exception e){
        System.out.println("Driver erreur :"+e.getMessage());
    }
    }
    
    public static Connection connect(){
        if(connex == null){
        DatabaseConnection dbConnect = new DatabaseConnection();
        }
        return connex;
    }
}
