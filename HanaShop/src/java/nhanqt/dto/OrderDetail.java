/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanqt.dto;

import java.io.Serializable;

/**
 *
 * @author pc
 */
public class OrderDetail implements Serializable{
    private String detailID;
    private String orderID;
    private String name;
    private Float price;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(String detailID, String orderID, String name, Float price, int quantity) {
        this.detailID = detailID;
        this.orderID = orderID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDetailID() {
        return detailID;
    }

    public void setDetailID(String detailID) {
        this.detailID = detailID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "detailID=" + detailID + ", orderID=" + orderID + ", name=" + name + ", price=" + price + ", quantity=" + quantity + '}';
    }
    
    
}
