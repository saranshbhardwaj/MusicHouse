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
import javabeans.TempUser;

/**
 *
 * @author sony
 */
public class TempUserDB {
    
    	 public static int addTempUser(TempUser tempuser) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
                
	        String query = "INSERT INTO tempuser(username,email,password,token,salt) "
	                               + "VALUES (?, ?, ?, ?, ?)";
	        try {
	            ps = connection.prepareStatement(query);
                    ps.setString(1, tempuser.getName());
                    ps.setString(2, tempuser.getEmail());
	            ps.setString(3, tempuser.getPassword());
                    
                    ps.setString(4, tempuser.getToken());
                    ps.setString(5, tempuser.getSalt());
                    System.out.println("Insert password " + tempuser.getPassword());
                    System.out.println(query);
	            return ps.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return 0;
	        } finally {
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
        public static TempUser getTempUser(String token) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        String query = "SELECT * from tempuser WHERE token = ?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, token);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	TempUser tempuser = new TempUser();
	            	tempuser.setName(rs.getString("username"));
	            	tempuser.setEmail(rs.getString("email"));
                        tempuser.setPassword(rs.getString("password"));
                        tempuser.setDate(rs.getDate("IssueDate"));
                        tempuser.setToken(token);
                        tempuser.setSalt(rs.getString("salt"));
	            	return tempuser;
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
        
     public static int deleteUser(String email) {
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
