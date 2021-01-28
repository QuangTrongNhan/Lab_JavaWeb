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
public class CreateAccountError implements Serializable{
    private String confirmNotMatched;

    public CreateAccountError() {
    }

    
    
    public CreateAccountError(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    @Override
    public String toString() {
        return "CreateAccountError{" + "confirmNotMatched=" + confirmNotMatched + '}';
    }
    
    
}
