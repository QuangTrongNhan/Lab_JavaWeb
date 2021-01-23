/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanqt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhanqt.cart.CartObject;
import nhanqt.dto.ProductDTO;

/**
 *
 * @author pc
 */
public class AddToCartServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String SOUT_OUT = "soutOut.html";

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

        String txtProductID = request.getParameter("txtProductID");
        String image = request.getParameter("image");
        String txtDescription = request.getParameter("txtDescription");
        float price = Float.parseFloat(request.getParameter("priceItem"));
        String txtProductName = request.getParameter("txtProductName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
//        String url = SHOPPING_PAGE;
        String getCurrentPage = request.getParameter("pageIndex");
        String url = "HomePageServlet?btnAction=Add&pageIndex=" + getCurrentPage;
        try {
            HttpSession session = request.getSession();
            Object checkUser = session.getAttribute("customer");
            if (checkUser == null) {
                url = LOGIN_PAGE;
            }

            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObject();
            }

            ProductDTO dto = new ProductDTO(txtProductID, txtProductName, image, txtDescription, price, 1);

            if (quantity <= 0) {
                url = SOUT_OUT;
            } else {
                cart.addItemToCart(dto);
                session.setAttribute("CART", cart);
            }
        } finally {
            response.sendRedirect(url);
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
