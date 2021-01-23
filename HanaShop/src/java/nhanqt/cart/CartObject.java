/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanqt.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import nhanqt.dto.ProductDTO;

/**
 *
 * @author pc
 */
public class CartObject implements Serializable {

    Map<String, ProductDTO> items;

    public Map<String, ProductDTO> getItems() {
        return items;
    }

    public void addItemToCart(ProductDTO dto) {
        //if not exist then new
        if (this.items == null) {
            this.items = new HashMap<>();
        }

        if (this.items.containsKey(dto.getProductID())) {
            int quantity = this.items.get(dto.getProductID()).getQuantity();
            dto.setQuantity(quantity + 1);
        }
        

        this.items.put(dto.getProductID(), dto);
    }

    //remove item from cart
    public void removeItemFromCart(String productID) {
        
        if (this.items == null) {
            return;
        }
            
        if (this.items.containsKey(productID)) {
            this.items.remove(productID);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
    public void removeAllCart() {
        
        if (this.items == null) {
            return;
        }
            
        this.items.clear();
    }
    
    //update item from cart
    public void update(int quantity,String productID){
        if(this.items == null){
            return;
        }
        if(this.items.containsKey(productID)){
            this.items.get(productID).setQuantity(quantity);
        }
    }
}
