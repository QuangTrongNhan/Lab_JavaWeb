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
import javax.servlet.http.HttpSession;
import nhanqt.cart.CartObject;
import nhanqt.dao.ProductDAO;
import nhanqt.dto.AccountDTO;
import nhanqt.dto.ProductDTO;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author pc
 */
public class PayProductServlet extends HttpServlet {

    private final String PAY_SUCCESS = "paySuccess.html";
    private final String PAY_FAILED = "payFailed.html";
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
    Logger log = Logger.getLogger(PayProductServlet.class);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        final int lastIndex = 1000000000;
        //random orderID of tblOrder
        String orderID = Integer.toString(RandomUtils.nextInt(1, lastIndex));
        //random detailID of tblOrderDetail
        String orderDetail = Integer.toString(RandomUtils.nextInt(1, lastIndex));
        String userID = request.getParameter("txtUserID");
        Float totalPrice = Float.parseFloat(request.getParameter("txtTotal"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String datePay = formatter.format(date);
        String address = request.getParameter("txtAddress");
        
        HttpSession session = request.getSession();
        String url = PAY_SUCCESS;
        try {
            ProductDAO dao = new ProductDAO();
            boolean result = dao.payProduct(orderID, userID, totalPrice, datePay, address);
            if (result) {
                CartObject cart = (CartObject) session.getAttribute("CART");
                //get name of cart
                for (ProductDTO productDTO : cart.getItems().values()) {
                    String name = productDTO.getProductName();
                    String productID = productDTO.getProductID();
                    float price = productDTO.getPrice();
                    int quantity = productDTO.getQuantity();
                    if (quantity < 1) {
                        url = SOUT_OUT;
                    }
                    int quantityCurrent = dao.getQuantity(productID) - quantity;
                    if (quantityCurrent < 1) {
                        url = SOUT_OUT;
                    } else {
                        boolean resultOrderDetail = dao.insertOrderDetail(orderDetail, orderID, name, price, quantity);
                        if (resultOrderDetail) {
                            if (quantity <= 0) {
                                url = "HomePageServlet?indexPage=1";
                            } else {
                                boolean updateQuantity = dao.updateQuantity(quantityCurrent, productID);
                                if (updateQuantity) {
                                    url = PAY_SUCCESS;
                                    cart.removeAllCart();
                                }
                            }
                        }
                    }
                }
            } else {
                url = PAY_FAILED;
            }
        } catch (SQLException | NamingException e) {
            log.error("Pay error : " + e.toString());
        }finally {
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
