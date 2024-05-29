/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.reataurantcrud.resources;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import java.sql.*;

/**
 *
 * @author HP
 */
@WebServlet(name = "FoodServlet", urlPatterns = {"/food"})
public class FoodServlet extends HttpServlet {
    
   @Override

protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        PrintWriter out = response.getWriter();
    try {
     Class.forName("com.mysql.jdbc.Driver");
       
        PreparedStatement p = DatabaseConnection.connect().prepareStatement("SELECT * FROM foods");
        ResultSet rs = p.executeQuery();
         

        List<Map<String, Object>> foodList = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> foodMap = new HashMap<>();
            foodMap.put("categoryId", rs.getInt("categoryId"));
            foodMap.put("foodName", rs.getString("foodName"));
            foodMap.put("foodPrice", rs.getString("foodPrice"));
            foodMap.put("foodDes", rs.getString("foodDes"));
            foodMap.put("foodImage", rs.getString("foodImage"));
            foodMap.put("id",rs.getInt("id"));
            foodList.add(foodMap);
        }
        // Set response content type
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Write JSON string to response
       
          String json = new Gson().toJson(foodList);
            out.println(json);
            
    } catch (ClassNotFoundException | SQLException e) {
        System.out.println("error occured");
        e.printStackTrace();
      
    }
}
}