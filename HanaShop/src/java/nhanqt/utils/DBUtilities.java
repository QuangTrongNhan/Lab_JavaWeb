/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanqt.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 *
 * @author pc
 */
public class DBUtilities implements Serializable{
    public static Connection makeConnection()
            throws NamingException, SQLException{
        //1.get current context
        Context context = new InitialContext();       
        //2.get server context
        Context tomcatContext = (Context)context.lookup("java:comp/env");
        //3. get Data Source
        javax.sql.DataSource ds = (javax.sql.DataSource)tomcatContext.lookup("SE140052");
        //4.make connection
        Connection con = ds.getConnection();
        return con;
    }
}
