/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanqt.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import nhanqt.dto.AccountDTO;
import nhanqt.utils.DBUtilities;

/**
 *
 * @author pc
 */
public class AccountDAO implements Serializable {

    //function to check login
    public AccountDTO checkLogin(String userID, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //create account variable to check success or fail
        AccountDTO account = null;
        try {
            //make connect
            con = DBUtilities.makeConnection();
            //create SQL string
            if (con != null) {
                //create sql variable to excute query
                String sql = "select userID,fullName,roleID,status "
                        + "from tblUsers\n"
                        + "where userID = ? and password = ?";
                //Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                //excute query
                rs = stm.executeQuery();
                if (rs.next()) {
                    account = new AccountDTO(rs.getString("userID"),
                            "", rs.getString("fullname"),
                            rs.getString("roleID"), rs.getBoolean("status"));
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return account;
    }

    public AccountDTO checkLoginByGg(String userID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //create account variable to check success or fail
        AccountDTO account = null;
        try {
            //make connect
            con = DBUtilities.makeConnection();
            //create SQL string
            if (con != null) {
                //create sql variable to excute query
                String sql = "select userID,fullName from tblUsers\n"
                        + "wHERE userID = ?";
                //Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                //excute query
                rs = stm.executeQuery();
                if(rs.next()){
                    account = new AccountDTO(rs.getString("userID"), "", 
                            rs.getString("fullname"), "", true);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return account;
    }

    public boolean saveGgAccount(String userID, String fullname, String roleID, Boolean status)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //create account variable to check success or fail
        try {
            //make connect
            con = DBUtilities.makeConnection();
            //create SQL string
            if (con != null) {
                //create sql variable to excute query
                String sql = "insert into tblUsers(userID,password,fullName,roleID,status)\n"
                        + "values(?,'', ?, ?, ?)";
                //Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, fullname);
                stm.setString(3, roleID);
                stm.setBoolean(4, status);
                //excute query
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
