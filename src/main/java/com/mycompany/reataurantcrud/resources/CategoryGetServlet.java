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
import java.util.*;
import java.sql.*;
        
/**
 *
 * @author HP
 */
@WebServlet(name = "CategoryGetServlet", urlPatterns = {"/getCategory"})
public class CategoryGetServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      
    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        PrintWriter out = response.getWriter();
        
         try {
            PreparedStatement p =DatabaseConnection.connect().prepareStatement("select * from fcategory");
            
            ResultSet rs = p.executeQuery();
            
                 List<Map<String, Object>> foodList = new ArrayList<>();
            while (rs.next()) {
            Map<String, Object> foodMap = new HashMap<>();
            foodMap.put("name", rs.getString("name"));
            foodMap.put("description", rs.getString("cat_des"));
            foodMap.put("priceRange", rs.getString("price_racge"));
            foodList.add(foodMap);
        }

        // Set response content type
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Write JSON string to response
       
          String json = new Gson().toJson(foodList);
            out.println(json);
                 
//      response.sendRedirect("login.html");
                 
            
        } catch ( SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
           
        }
    }

   
   }
