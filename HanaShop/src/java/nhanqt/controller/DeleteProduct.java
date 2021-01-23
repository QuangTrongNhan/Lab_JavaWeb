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
public class DeleteProduct extends HttpServlet {

    private final String DELETE_ERR = "deleteErr.html";
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
    Logger log = Logger.getLogger(DeleteProduct.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        final int lastIndex = 1000000000;
        String productid = request.getParameter("txtProductID");
        String userID = request.getParameter("txtUserID");
        String updateID = Integer.toString(RandomUtils.nextInt(1, lastIndex));
        //get date current
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String updateDate = formatter.format(date);
        String url = DELETE_ERR;
        boolean result = true;
        try {
            ProductDAO dao = new ProductDAO();
            //call delete
            result = dao.deleteProduct(productid);
            //call record delete
            //check duplicate   
            while (!result) {
                result = dao.inserRecord(updateID, userID, updateDate, productid);
                request.setAttribute("duplicate", "updateID is duplicate");
                url = HOME_PAGE;
            }
            url = HOME_PAGE;
        } catch (SQLException | NamingException e) {
            log.error("DeleteProduct error : " + e.toString());
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
