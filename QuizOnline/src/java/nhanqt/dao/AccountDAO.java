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
import java.util.List;
import javax.naming.NamingException;
import nhanqt.dto.AccountDTO;
import nhanqt.utils.DBUtilities;

/**
 *
 * @author pc
 */
public class AccountDAO implements Serializable {

    public AccountDTO checkLogin(String email, String password)
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
                String sql = "select email, name,role,status\n"
                        + "from tblAccount\n"
                        + "where email = ? and password = ? and status = 'new'";
                //Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                //excute query
                rs = stm.executeQuery();
                if (rs.next()) {
                    account = new AccountDTO(rs.getString("email"), 
                            rs.getString("name"), 
                            "", 
                            rs.getString("role"), 
                            rs.getString("status"));
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

    public boolean createAccount(String email, String name, String password, String role, String status)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        //create account variable to check success or fail
        try {
            //make connect
            con = DBUtilities.makeConnection();
            //create SQL string
            if (con != null) {
                //create sql variable to excute query
                String sql = "insert into tblAccount (email,name,password,role,status) \n"
                        + "                        values(?,?,?,?,?)";
                //Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, name);
                stm.setString(3, password);
                stm.setString(4, role);
                stm.setString(5, status);
                //excute query
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
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
