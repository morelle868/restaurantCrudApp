/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.reataurantcrud.resources;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.sql.*;

/**
 *
 * @author HP
 */
@WebServlet(name = "UpdateDelete", urlPatterns = {"/UpdateDelete"})
@MultipartConfig

public class UpdateDelete extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            uploadFood(request, response);
        } else if ("delete".equals(action)) {
            deleteFood(request, response);
        }
    }

    private void uploadFood(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("categoryId"));
        String name = request.getParameter("foodName");
        String description = request.getParameter("foodPrice");
        String price = request.getParameter("foodDes");
        Part filePart = request.getPart("foodImage");
        String fileName = filePart.getSubmittedFileName();
        InputStream fileContent = filePart.getInputStream();
        
         String uploadPath = request.getServletContext().getRealPath("") + File.separator + "uploads";
        
        String updateSQL = "UPDATE foods SET foodName = ?, foodPrice= ?, foodDes = ? , foodImage = ?, categoryId = ? WHERE id = ?";
        
          File uploads = new File(uploadPath);
        String filePath = "uploads"+File.separator+fileName;
        File file = new File(uploads, fileName);
        try (InputStream input = fileContent) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        try (
                PreparedStatement preparedStatement =DatabaseConnection.connect().prepareStatement(updateSQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, price);
            preparedStatement.setString(4, filePath);
         
            preparedStatement.setInt(5,id );

            preparedStatement.executeUpdate();
            System.out.println("update sucessful");
            response.sendRedirect("foodlist.html");

        } catch (SQLException e) {
            System.out.println("unable to update food");
            response.sendRedirect("dashbord.html");
            throw new ServletException("unable to update food", e);
            
        }
    }

    private void deleteFood(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String deleteSQL = "DELETE FROM Foods WHERE id = ?";

        try (
                PreparedStatement preparedStatement =DatabaseConnection.connect().prepareStatement(deleteSQL)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            response.sendRedirect("foodlist.html");

        } catch (SQLException e) {
            throw new ServletException("unable to delete food", e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
