/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.DBUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javabeans.Reset;
import javabeans.User;
import util.Hashpassword;

/**
 *
 * @author sony
 */
public class UserDB {
    
    public static String validateUser(String email) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        String query = "select * FROM user WHERE username = ? ";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, email);
	            
	            rs= ps.executeQuery();
	            while (rs.next())
	            {
                        return rs.getString("salt");
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
			return null;
	    }
         public static boolean selectUser(String email,String pwd,String salt) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        String query = "select * FROM user WHERE username = ? and password=?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, email);
	            ps.setString(2,Hashpassword.hashAndSaltPassword(pwd, salt));
	            rs= ps.executeQuery();
	            while (rs.next())
	            {
	             return true;
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return false;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
			return false;
	    }
	  public static User getUser(String email) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        String query = "SELECT * from user WHERE username = ?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, email);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	User user = new User();
	            	user.setName(rs.getString("name"));
	            	user.setEmail(rs.getString("username"));
	            	user.setUserType(rs.getString("type"));
                        user.setSalt(rs.getString("salt"));
	       
	               return user;
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	        return null;
	    }
	  public static List<User> getUsers() {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        List<User> users = new ArrayList();
	        String query = "SELECT * from user";
	        try {
	            ps = connection.prepareStatement(query);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	User user = new User();
	               	user.setName(rs.getString("name"));
	            	user.setEmail(rs.getString("username"));
	            	user.setUserType(rs.getString("type"));
	         
	                users.add(user);
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	        return users;
	    }

	 public static int addUser(User user) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        String query = "INSERT INTO user "
	                + "(username, password, type,name,salt) "
	                + "VALUES (?, ?, ?, ?, ?)";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, user.getEmail());
	            ps.setString(2, user.getPassword());
	            ps.setString(3, user.getUserType());
	            ps.setString(4, user.getName());
                    ps.setString(5, user.getSalt());
	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
	 
	 public static void updateUserType(User user) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        String query = "UPDATE user SET "
	                + "Type = ? "
	                + "WHERE Username = ? ";
	        try {
                    String UserTypeAC = "";
                    if(user.getUserType().equalsIgnoreCase("1")){
                    UserTypeAC = "Customer";
                    }
                    else{
                    
                        UserTypeAC = "Admin";
                    }
	            ps = connection.prepareStatement(query);
	            ps.setString(1, UserTypeAC);
	            ps.setString(2, user.getEmail());
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	          
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }

         public static void updatePassword(String email, String password,String salt) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        String query = "UPDATE user SET "
	                + "password = ?" 
	                + "WHERE username = ? ";
	        try {
	            ps = connection.prepareStatement(query);
                    //String salt=Hashpassword.getSalt();
	            ps.setString(1, Hashpassword.hashAndSaltPassword(password,salt));
	            ps.setString(2, email);
                    //ps.setString(3, salt);
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	          
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
             public static int addUserforReset(Reset reset) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
                
	        String query = "INSERT INTO resetPass "
	                               + "VALUES (?, ?, ?)";
	        try {
	            ps = connection.prepareStatement(query);
                    java.sql.Date sqlDate=new Date(reset.getDate().getTime());
	            
                    ps.setString(1, reset.getEmail());
	            ps.setString(2, reset.getToken());
                    ps.setDate(3, sqlDate);//tempuser.getDate().getTime());
                    
	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
             public static Reset getUserforReset(String token) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        String query = "SELECT * from resetPass WHERE token = ?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, token);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	Reset reset = new Reset();
	            	reset.setEmail(rs.getString("email"));
                        reset.setDate(rs.getDate("expiration"));
                        reset.setToken("token");
	            	return reset;
	            }
	        } catch (SQLException e) {
	            System.out.println(e);
	            return null;
	        } finally {
	            DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	        return null;
	    }
             
               public static int deleteUserforReset(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
                
	        String query = "DELETE from tempuser where email= ?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, email);
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
