/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.io.Serializable;
import util.Hashpassword;
/**
 *
 * @author sony
 */
public class User implements Serializable{
    
        private String name;
	private String email;
	private String userType;
	private String password;
	private String salt;
    
        public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = Hashpassword.hashAndSaltPassword(password,this.salt);
	}
        public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
        	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
        
        	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
        public User(String name, String email, String userType, String password, String salt, boolean flag) {
		super();
		this.name = name;
		this.email = email;
		this.userType = userType;
                this.salt=salt;
                if(!flag) {
                    this.password = password;
                    System.out.println("false " + password);
                } else {
                    this.password = Hashpassword.hashAndSaltPassword(password,salt);
                    System.out.println("true " + password);
                }
	}
	
	public User(String name, String email, String userType) {
		super();
		this.name = name;
		this.email = email;
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", userType=" + userType + "]";
	}
}
