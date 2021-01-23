/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanqt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhanqt.dao.ProductDAO;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author pc
 */
public class UpdateProduct extends HttpServlet {

//    private final String ERROR_UPDATE = "errorUpdate.html";
    private final String HOME_PAGE = "AdminPageServlet";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Logger log = Logger.getLogger(UpdateProduct.class);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        final int lastIndex = 100000000;
        String productName = request.getParameter("txtProductName");
        String productID = request.getParameter("txtProductID");
        String file = request.getParameter("txtFile");
        String description = request.getParameter("txtDescription");
        String price = request.getParameter("txtPrice");
        String categoryID = request.getParameter("listCategoryID");
        String quantity = request.getParameter("txtQuantity");
        String status = request.getParameter("status");
        String updateID = Integer.toString(RandomUtils.nextInt(1, lastIndex));
        String userID = request.getParameter("txtUserID");
        //get date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String updateDate = formatter.format(date);
        String url = HOME_PAGE;
        try {
            //create obj ProductDAO
            ProductDAO dao = new ProductDAO();

            //when updating parameter "file" will be get browser and this time it is empty so this condition process when file is empty!
            if (file.isEmpty()) {
                file = dao.getImage(productID);
            }

            //call dao
            boolean result = dao.updateProduct(productName, productID, file, description, price, categoryID, quantity, status);
            while (!result) {
                //check duplicate               
                result = dao.inserRecord(updateID, userID, updateDate, productID);
                request.setAttribute("duplicate", "updateID is duplicate");
                url = HOME_PAGE;
            }
        } catch (SQLException | NamingException e) {
            log.error("UpdateProduct error : " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
            out.close();
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
