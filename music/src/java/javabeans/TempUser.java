/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.io.Serializable;
import java.util.Date;
import util.Hashpassword;

/**
 *
 * @author sony
 */
public class TempUser implements Serializable  {
    
    private String name;
	private String email;
	private String token;
	private String password;
        private String salt;
        private Date date;
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
		this.password = password;
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
        
	public void setToken(String token) {
		this.token = token;
	}
        public String getToken() {
		return token;
	}
	public void setDate(Date date) {
		this.date = date;
	}
        public Date getDate() {
		return date;
	}
        
        public TempUser() {
            
        }	
	public TempUser(String name, String email, String password,String salt,Date date,String token) {
		
		this.name = name;
		this.email = email;
		this.password = password;
                this.salt=salt;
                this.date=date;
                this.token=token;
	}
        public String hashPassword(String password) {
            return Hashpassword.hashAndSaltPassword(password,salt);
        }
}
