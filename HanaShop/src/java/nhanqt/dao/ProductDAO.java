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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nhanqt.dto.OrderDTO;
import nhanqt.dto.OrderDetail;
import nhanqt.dto.ProductDTO;
import nhanqt.utils.DBUtilities;

/**
 *
 * @author pc
 */
public class ProductDAO implements Serializable {

    public List<ProductDTO> getAllProduct(int pageIndex, int pageSize)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //create product variable to check success or fail
        List<ProductDTO> product = null;
        try {
            //make connect
            con = DBUtilities.makeConnection();
            //create SQL string
            if (con != null) {
                String sql = "SELECT productID, productName, price, quantity,image,description,createDate,categoryID,status \n"
                        + "FROM tblProduct ORDER BY createDate\n"
                        + "OFFSET (?*?-?) ROWS FETCH NEXT ? ROWS ONLY";
                stm = con.prepareStatement(sql);
                stm.setInt(1, pageIndex);
                stm.setInt(2, pageSize);
                stm.setInt(3, pageSize);
                stm.setInt(4, pageSize);
                rs = stm.executeQuery();
                if (rs != null) {
                    List<ProductDTO> list = new ArrayList<>();
                    while (rs.next()) {
                        list.add(new ProductDTO(
                                rs.getString("productID"),
                                rs.getString("productName"),
                                rs.getString("image"),
                                rs.getString("description"),
                                rs.getFloat("price"),
                                rs.getString("createDate"),
                                rs.getString("categoryID"),
                                rs.getInt("quantity"),
                                rs.getBoolean("status")));
                    }
                    return list;
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
        return product;
    }

    public boolean deleteProduct(String productID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //make connect
            con = DBUtilities.makeConnection();
            //create SQL string
            if (con != null) {
                String sql = "update tblProduct \n"
                        + "set status = 0\n"
                        + "where productID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, productID);
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

    public List<Boolean> getStatus()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<Boolean> list = null;
        try {
            //make connect
            con = DBUtilities.makeConnection();
            //create SQL string
            if (con != null) {
                String sql = "select distinct status from tblProduct";
                stm = con.prepareCall(sql);
                rs = stm.executeQuery();
                if (rs != null) {
                    list = new ArrayList<>();
                    while (rs.next()) {
                        list.add(rs.getBoolean("status"));
                    }
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
        return list;
    }

    public List<String> getCategoryID()
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<String> list = null;
        try {
            //make connect
            con = DBUtilities.makeConnection();
            //create SQL string
            if (con != null) {
                String sql = "select distinct categoryID from category";
                stm = con.prepareCall(sql);
                rs = stm.executeQuery();
                if (rs != null) {
                    list = new ArrayList<>();
                    while (rs.next()) {
                        list.add(rs.getString("categoryID"));
                    }
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
        return list;
    }

    public boolean inserRecord(String updateID, String userID,
            String updateDate, String productID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //make connect
            con = DBUtilities.makeConnection();
            //create SQL string
            if (con != null) {
                String sql = "insert into tblRecord (updateID,userID,updateDate,productID)\n"
                        + "values(?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, updateID);
                stm.setString(2, userID);
                stm.setString(3, updateDate);
                stm.setString(4, productID);
                int row = stm.executeUpdate();
                if (row < 0) {
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

    public boolean updateProduct(String productName, String productID,
            String image, String description,
            String price, String categoryID,
            String quantity, String status)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //make connect
            con = DBUtilities.makeConnection();
            //create SQL string
            if (con != null) {
                String sql = "update tblProduct\n"
                        + "set productName=?,"
                        + "image=?,description=?,"
                        + "price=?,categoryID=?,quantity=?,status=?\n"
                        + "where productID=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, productName);
                stm.setString(2, image);
                stm.setString(3, description);
                stm.setString(4, price);
                stm.setString(5, categoryID);
                stm.setString(6, quantity);
                stm.setString(7, status);
                stm.setString(8, productID);
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

    public String getImage(String productID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String result = "";
        try {
            //make connect
            con = DBUtilities.makeConnection();
            //create SQL string
            if (con != null) {
                String sql = "select image from tblProduct\n"
                        + "where productID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, productID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getString("image");
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
        return result;
    }

    public int countPage(int pageSize) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String query = "select Count(productID) from tblProduct";
            con = DBUtilities.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(query);
                rs = stm.executeQuery();

                int count = 0;
                if (rs.next()) {
                    count = rs.getInt(1);
                }
                int numOfPage = count / pageSize;
                if (count % pageSize != 0) {
                    numOfPage++;
                }
                return numOfPage;
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
        return 0;
    }

    public List<ProductDTO> getProductCustomer(int pageIndex, int pageSize)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //create product variable to check success or fail
        List<ProductDTO> product = null;
        try {
            //make connect
            con = DBUtilities.makeConnection();
            //create SQL string
            if (con != null) {
                String sql = "SELECT productID,productName,image,description,price,quantity  \n"
                        + "FROM tblProduct where status = '1' ORDER BY createDate\n"
                        + "OFFSET (?*?-?) ROWS FETCH NEXT ? ROWS ONLY";
                stm = con.prepareStatement(sql);
                stm.setInt(1, pageIndex);
                stm.setInt(2, pageSize);
                stm.setInt(3, pageSize);
                stm.setInt(4, pageSize);
                rs = stm.executeQuery();
                if (rs != null) {
                    product = new ArrayList<>();
                    while (rs.next()) {
                        product.add(new ProductDTO(rs.getString("productID"),
                                rs.getString("productName"),
                                rs.getString("image"),
                                rs.getString("description"),
                                rs.getFloat("price"),
                                rs.getInt("quantity")
                        ));
                    }
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
        return product;
    }

    public int getQuantity(String productID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //create product variable to check success or fail
        int result = 0;
        try {
            //make connect
            con = DBUtilities.makeConnection();
            //create SQL string
            if (con != null) {
                String sql = "select quantity from tblProduct\n"
                        + "where productID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, productID);

                rs = stm.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        result = rs.getInt(1);
                    }
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
        return result;
    }

    public boolean insertProduct(String productID, String productName, String image, String description, float price, String createDate, String categoryID,
            int quantity, int status)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //make connect
            con = DBUtilities.makeConnection();
            //create SQL string
            if (con != null) {
                String sql = "insert into tblProduct (productID,productName,image,description,price,createDate,categoryID,quantity,status)\n"
                        + "values (?,?,?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, productID);
                stm.setString(2, productName);
                stm.setString(3, image);
                stm.setString(4, description);
                stm.setFloat(5, price);
                stm.setString(6, createDate);
                stm.setString(7, categoryID);
                stm.setInt(8, quantity);
                stm.setInt(9, status);
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

    public boolean payProduct(String orderID, String userID, float totalPrice, String orderDate, String address)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //make connect
            con = DBUtilities.makeConnection();
            //create SQL string
            if (con != null) {
                String sql = "insert into tblOrder (orderID,userID,totalPrice,orderDate,address)\n"
                        + "values(?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                stm.setString(2, userID);
                stm.setFloat(3, totalPrice);
                stm.setString(4, orderDate);
                stm.setString(5, address);

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

    public boolean insertOrderDetail(String detailID, String orderID, String name, float price, int quantity)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //make connect
            con = DBUtilities.makeConnection();
            //create SQL string
            if (con != null) {
                String sql = "insert into tblOrderDetail(detailID,orderID,name,price,quantity)\n"
                        + "values(?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, detailID);
                stm.setString(2, orderID);
                stm.setString(3, name);
                stm.setFloat(4, price);
                stm.setInt(5, quantity);

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

    public boolean updateQuantity(int quantity, String productID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        //create product variable to check success or fail
        try {
            //make connect
            con = DBUtilities.makeConnection();
            //create SQL string
            if (con != null) {
                String sql = "UPDATE tblProduct set quantity = ?\n"
                        + "where productID = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setString(2, productID);
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

    public List<OrderDTO> getOrderHistory(String userID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDTO> orderList = null;
        //create product variable to check success or fail
        try {
            //make connect
            con = DBUtilities.makeConnection();
            //create SQL string
            if (con != null) {
                String sql = "select orderID,userID,totalPrice,orderDate,address\n"
                        + "from tblOrder\n"
                        + "where userID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs != null) {
                    List<OrderDTO> list = new ArrayList<>();
                    while (rs.next()) {
                        list.add(new OrderDTO(rs.getString(1), rs.getString(2),
                                rs.getFloat(3), rs.getDate(4), rs.getString(5)));
                    }
                    return list;
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
        return orderList;
    }

    public List<OrderDetail> viewDetail(String orderID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDetail> orderList = null;
        //create product variable to check success or fail
        try {
            //make connect
            con = DBUtilities.makeConnection();
            //create SQL string
            if (con != null) {
                String sql = "select detailID,orderID,name,price,quantity\n"
                        + "from tblOrderDetail\n"
                        + "where orderID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, orderID);
                rs = stm.executeQuery();
                if (rs != null) {
                    List<OrderDetail> list = new ArrayList<>();
                    while (rs.next()) {
                        list.add(new OrderDetail(rs.getString("detailID"), rs.getString("orderID")
                                , rs.getString("name"), rs.getFloat("price"), rs.getInt("quantity")));
                    }
                    return list;
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
        return orderList;
    }
}
