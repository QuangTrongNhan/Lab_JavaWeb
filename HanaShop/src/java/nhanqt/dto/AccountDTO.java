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
public class AccountDTO implements Serializable{
    private String userID;
    private String password;
    private String fullname;
    private String roleID;
    private boolean status;

    public AccountDTO(String userID, String password, String fullname, String roleID, boolean status) {
        this.userID = userID;
        this.password = password;
        this.fullname = fullname;
        this.roleID = roleID;
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AccountDTO{" + "userID=" + userID + ", password=" + password + ", fullname=" + fullname + ", roleID=" + roleID + ", status=" + status + '}';
    }
    
    
}
