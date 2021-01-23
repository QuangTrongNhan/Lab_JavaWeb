/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanqt.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author pc
 */
public class OrderDTO implements Serializable {

    private String orderID;
    private String userID;
    private float totalPrice;
    private Date orderDate;
    private String address;

    public OrderDTO() {
    }

    public OrderDTO(String orderID, String userID, float totalPrice, Date orderDate, String address) {
        this.orderID = orderID;
        this.userID = userID;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.address = address;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "orderID=" + orderID + ", userID=" + userID + ", totalPrice=" + totalPrice + ", orderDate=" + orderDate + ", address=" + address + '}';
    }
    
    
    
}
