/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javabeans.User;
import javabeans.product;

/**
 *
 * @author sony
 */
public class ProductDB {
    
    	  public static List<product> getProducts() {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        List<product> products = new ArrayList();
	        String query = "SELECT * from Productlist";
	        try {
                    String PricePrimary = "";
                    String Price = "";
	            ps = connection.prepareStatement(query);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	product productlist = new product();
                        productlist.setproductid(rs.getInt("ProductID"));
	               	productlist.setproductname(rs.getString("ProductName"));
	            	productlist.setproductcode(rs.getString("ProductCode"));
	            	productlist.setproductstatus(rs.getString("ProductStatus"));
                        PricePrimary = rs.getString("Price");
                        NumberFormat currency = NumberFormat.getCurrencyInstance();
                        Price = currency.format(Integer.parseInt(PricePrimary));
                        productlist.setprice(Price);
                        productlist.setdescription(rs.getString("Description"));
                        productlist.setimageurl(rs.getString("ImageUrl"));
                        productlist.setrating(rs.getString("Rating"));
	         
	                products.add(productlist);
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	        return products;
	    }
          
	public static product getProduct(String productID) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		String query = "SELECT * from Productlist WHERE ProductID = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, productID);
			rs = ps.executeQuery();
			while (rs.next()) {
				product Product = new product();
				Product.setproductid(rs.getInt("ProductID"));
				Product.setproductname(rs.getString("ProductName"));
				Product.setproductcode(rs.getString("ProductCode"));
                                Product.setproductstatus(rs.getString("ProductStatus"));
				Product.setimageurl(rs.getString("ImageUrl"));
				Product.setprice(rs.getString("Price"));
				
				return Product;
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs1);
			DBUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}
		return null;
	}
        
        public static int updateProduct(String productID, String productStatus, String productprice){
        
            ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
                String query
                        = "UPDATE productlist SET "
                + "productStatus = ?, "
                + "Price = ? "
                + "WHERE productID = ?";
                
        try {
            String productStatusinString = "";
            if(productStatus.endsWith("1"))
            {
            productStatusinString = "Available";
            }
            else
            {
            productStatusinString = "Out of stock";
            }
            ps = connection.prepareStatement(query);
            ps.setString(1, productStatusinString);
            ps.setString(2, productprice);
            ps.setString(3, productID);

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
      public static int insertProduct(product Product){
        
            ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
                String query
                        = "INSERT INTO productlist "
                + "(ProductName, ProductCode, ProductStatus, Price, Description, ImageUrl)"
                + "VALUES(?,?,?,?,?,?)";
                
        try {

            ps = connection.prepareStatement(query);
            ps.setString(1, Product.getproductname());
            ps.setString(2, Product.getproductcode());
            ps.setString(3, Product.getproductstatus());
            ps.setString(4, Product.getprice());
            ps.setString(5, Product.getdescription());
            ps.setString(6, Product.getimageurl());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
        
        public static int deleteProduct(String productID){
        
            ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
                String query
                        = "DELETE FROM productlist "
                + "WHERE ProductID = ?";
                
        try {
            ps = connection.prepareStatement(query);
            int productIdToDlete = Integer.parseInt(productID);
            ps.setInt(1, productIdToDlete);

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

}
