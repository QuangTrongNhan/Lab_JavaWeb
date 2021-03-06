/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanqt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhanqt.dao.ProductDAO;
import nhanqt.dto.ProductDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author pc
 */
public class AdminPageServlet extends HttpServlet {

    private final String HOME_PAGE = "adminPage.jsp";
    Logger loger = Logger.getLogger(AdminPageServlet.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Logger log = Logger.getLogger(AdminPageServlet.class);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = HOME_PAGE;

        try {
            ProductDAO dao = new ProductDAO();

            final int PAGE_SIZE = 5;
            List<Boolean> listStatus = dao.getStatus();
            List<String> listCategoryID = dao.getCategoryID();
            int pageIndex;
            try {
                pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
            } catch (NumberFormatException e) {
                pageIndex = 1;
            }
            int totalPage = dao.countPage(PAGE_SIZE);
            String raw_page = request.getParameter("pageIndex");

            if (totalPage > 0) {
                request.setAttribute("totalPage", totalPage);
            }

            if (raw_page != null) {
                pageIndex = Integer.parseInt(raw_page);
            }
            request.setAttribute("pageIndex", pageIndex);
            List<ProductDTO> listProduct = dao.getAllProduct(pageIndex, PAGE_SIZE);

            if (listProduct != null) {
                request.setAttribute("product", listProduct);
            }

            if (listStatus != null) {
                request.setAttribute("status", listStatus);
            }

            if (listCategoryID != null) {
                request.setAttribute("listCategoryID", listCategoryID);
            }

        } catch (SQLException | NamingException e) {
            log.error("LoadPage Admin error : " + e.toString());
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
