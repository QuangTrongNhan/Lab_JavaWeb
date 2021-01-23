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
public class PayByVNPayServlet extends HttpServlet {

    private final String PAY_SUCCESS = "paySuccess.html";
    private final String PAY_VNPAY_FAILED = "payVNPayErr.html";
//    private final String PAY_VNPAY = "index.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Logger log = Logger.getLogger(PayByVNPayServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        final int lastIndex = 1000000000;
        String url = PAY_VNPAY_FAILED;
        HttpSession session = request.getSession();
        AccountDTO dto = (AccountDTO) session.getAttribute("customer");
        String userID = dto.getUserID();
        String orderDetail = Integer.toString(RandomUtils.nextInt(1, lastIndex));
        //random orderID of tblOrder
        String orderID = Integer.toString(RandomUtils.nextInt(1, lastIndex));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String datePay = formatter.format(date);
        try {
            CartObject cart = (CartObject) session.getAttribute("CART");
            float totalPrice = 0;
            for (ProductDTO productDTO : cart.getItems().values()) {
                float price = productDTO.getPrice();
                int quantity = productDTO.getQuantity();
                totalPrice = price * quantity;
            }
            String address = (String) session.getAttribute("address");
            ProductDAO dao = new ProductDAO();
            boolean result = dao.payProduct(orderID, userID, totalPrice, datePay, address);
            if (result) {
                for (ProductDTO productDTO : cart.getItems().values()) {
                    String name = productDTO.getProductName();
                    float price = productDTO.getPrice();
                    int quantity = productDTO.getQuantity();
                    boolean resultOrderDetail = dao.insertOrderDetail(orderDetail, orderID, name, price, quantity);
                    String productID = productDTO.getProductID();
                    int quantityCurrent = dao.getQuantity(productID) - quantity;
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
        } catch (SQLException | NamingException e) {
            log.error("Pay error : " + e.toString());
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
