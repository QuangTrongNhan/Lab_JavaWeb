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
public class ProductDTO implements Serializable{
    private String productID;
    private String productName;
    private String image;
    private String description;
    private float price;
    private String createDate;
    private String categoryID;
    private int quantity;
    private boolean status;

    public ProductDTO(String productID, String productName, String image, String description, float price, String createDate, String categoryID, int quantity, boolean status) {
        this.productID = productID;
        this.productName = productName;
        this.image = image;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.categoryID = categoryID;
        this.quantity = quantity;
        this.status = status;
    }

    public ProductDTO(String productID, String productName, String image, String description, float price, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.image = image;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
    
    
    
    public ProductDTO() {
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "productID=" + productID + ", productName=" + productName + ", image=" + image + ", description=" + description + ", price=" + price + ", createDate=" + createDate + ", categoryID=" + categoryID + ", quantity=" + quantity + ", status=" + status + '}';
    }
    
    
}
