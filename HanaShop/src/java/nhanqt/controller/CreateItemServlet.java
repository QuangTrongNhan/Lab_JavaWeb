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
import org.apache.log4j.Logger;

/**
 *
 * @author pc
 */
public class CreateItemServlet extends HttpServlet {
    private final String CREATE_PAGE = "createPage.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Logger log = Logger.getLogger(CreateItemServlet.class);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String productID = request.getParameter("txtProductID");
        String productName = request.getParameter("txtProductName");
        String image = request.getParameter("txtFile");
        String description = request.getParameter("txtDescription");
        float price =Float.parseFloat(request.getParameter("Price")); 
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String dateInsert = formatter.format(date);
        String catogoryID = request.getParameter("categoryID");
        int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
        String url = CREATE_PAGE;
        try {
            ProductDAO dao = new ProductDAO();
            boolean result = dao.insertProduct(productID, productName, image, description, price, dateInsert, catogoryID, quantity, 1);
            if(result){
                url = "AdminPageServlet?pageIndex=1";
            }
            else{
                request.setAttribute("CREATEPRODUCTERROR", "Product is duplicated!");
            }
            
        }catch(SQLException | NamingException e){
            log.error("Create error : " + e.toString());
        }finally{
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
