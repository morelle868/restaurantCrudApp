/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.reataurantcrud.resources;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.*;

/**
 *
 * @author HP
 */
@WebServlet(name = "Food1", urlPatterns = {"/Food1"})
@MultipartConfig
public class Food1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/restaurant", "root", "");
            PreparedStatement p = conn.prepareStatement("SELECT * FROM foods");
            ResultSet rs = p.executeQuery();

            List<Map<String, Object>> foodList = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> foodMap = new HashMap<>();
                foodMap.put("categoryId", rs.getInt("categoryId"));
                foodMap.put("foodName", rs.getString("foodName"));
                foodMap.put("foodPrice", rs.getString("foodPrice"));
                foodMap.put("foodDes", rs.getString("foodDes"));
                foodMap.put("foodImage", rs.getString("foodImage"));

                foodList.add(foodMap);
            }

            // Set response content type
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Write JSON string to response
            PrintWriter out = response.getWriter();
            System.out.println(out);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Handle or log the exception
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("foodName");
        String price = request.getParameter("foodPrice");
        String description = request.getParameter("foodDes");
        int categoryIdParam = Integer.parseInt(request.getParameter("categoryId"));
        
        Part filePart = request.getPart("foodImage");
        String fileName = filePart.getSubmittedFileName();
        InputStream fileContent = filePart.getInputStream();

        String uploadPath = request.getServletContext().getRealPath("") + File.separator + "uploads";
        File fileSaveDir = new File(uploadPath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        File uploads = new File(uploadPath);
        String filePath = "uploads"+File.separator+fileName;
        File file = new File(uploads, fileName);
        try (InputStream input = fileContent) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        try {
            PreparedStatement p = DatabaseConnection.connect().prepareStatement("select * from foods where foodName='" + name + "' ");
            ResultSet Rs = p.executeQuery();

            if (Rs.next()) {
                System.out.println("food already exist");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Food already exists');");
            } else {
                PreparedStatement insertFood = DatabaseConnection.connect().prepareStatement("insert into foods (foodName,foodPrice,foodDes,foodImage, categoryId) values(?,?,?,?,?)");
                insertFood.setString(1, name);
                insertFood.setString(2, price);
                insertFood.setString(3, description);
                insertFood.setString(4, filePath);
               
             // insertFood.setString(4,fullPath);

                insertFood.setInt(5, categoryIdParam);

                int insertRow = insertFood.executeUpdate();
                response.sendRedirect("foodlist.html");

                if (insertRow > 0) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('food created with success');");
                } else {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Could not create food');");
                }

            }

        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("hey");
            // Handle database errors here
            // You may want to display an error message to the user or log the error
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
