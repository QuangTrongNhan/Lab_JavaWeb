/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanqt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pc
 */
public class ServletDispatcher extends HttpServlet {

    private static final String LOGIN = "LoginServlet";
    private static final String ERROR = "error.html";
    private static final String LOGOUT = "LogoutServlet";
    private static final String DELETE_PRODUCT = "DeleteProduct";
    private static final String UPDATE_PRODUCT = "UpdateProduct";
    private static final String ADD_TO_CART = "AddToCartServlet";
    private static final String VIEW_CART = "viewCart.jsp";
    private static final String START_PAGE = "HomePageServlet";
    private static final String REMOVE_ITEM = "DeleteCartServlet";
    private static final String UPDATE_ITEM = "UpdateAmountServlet";
    private static final String CREATE_ITEM = "CreateItemServlet";
    private static final String PAY_ITEM = "PayProductServlet";
    private static final String VIEW_HISTORY = "ViewHistoryServlet";
    private static final String PAY_VNPAY = "index.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = ERROR;
        try {
            String action = request.getParameter("btnAction");
            if ("Login".equals(action)) {
                url = LOGIN;
            }
            if ("Logout".equals(action)) {
                url = LOGOUT;
            }
            if ("Delete".equals(action)) {
                url = DELETE_PRODUCT;
            }
            if ("Update".equals(action)) {
                url = UPDATE_PRODUCT;
            }
            if ("Add".equals(action)) {
                url = ADD_TO_CART;
            }
            if ("View Item".equals(action)) {
                url = VIEW_CART;
            }
            if ("start".equals(action)) {
                url = START_PAGE;
            }
            if ("DeleteItem".equals(action)) {
                url = REMOVE_ITEM;
            }
            if ("Edit".equals(action)) {
                url = UPDATE_ITEM;
            }
            if ("Create".equals(action)) {
                url = CREATE_ITEM;
            }
            if ("PAY".equals(action)) {
                url = PAY_ITEM;
            }
            if ("View Historty".equals(action)) {
                url = VIEW_HISTORY;
            }
            if ("PAY BY VNPAY".equals(action)) {
                String address = request.getParameter("txtAddress");
                HttpSession session = request.getSession();
                session.setAttribute("address", address);
                url = PAY_VNPAY;
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
