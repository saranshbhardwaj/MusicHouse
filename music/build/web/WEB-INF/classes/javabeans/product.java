/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.io.Serializable;

/**
 *
 * @author sony
 */
public class product implements Serializable{
        
        private int productid;
        private String productname;
	private String productcode;
	private String productstatus;
        private String price;
	private String description;
	private String imageurl;
        private String rating;
        
        public int getproductid() {
		return productid;
	}
	public void setproductid(int productid) {
		this.productid = productid;
	}
        
        public String getproductname() {
		return productname;
	}
	public void setproductname(String productname) {
		this.productname = productname;
	}
        
        public String getproductcode() {
		return productcode;
	}
	public void setproductcode(String productcode) {
		this.productcode = productcode;
	}
        
        public String getproductstatus() {
		return productstatus;
	}
	public void setproductstatus(String productstatus) {
		this.productstatus = productstatus;
	}
        public String getprice() {
		return price;
	}
	public void setprice(String price) {
		this.price = price;
	}
        public String getdescription() {
		return description;
	}
        
        
        
	public void setdescription(String description) {
		this.description = description;
	}
        public String getimageurl() {
		return imageurl;
	}
	public void setimageurl(String imageurl) {
		this.imageurl = imageurl;
	}
        public String getrating() {
		return rating;
	}
	public void setrating(String rating) {
		this.rating = rating;
	}
        public product() {
		super();
	}
        
        public product(String productname, String productcode, String productstatus, String price, String description, String imageurl,String rating) {
		super();
		this.productname = productname;
		this.productcode = productcode;
		this.productstatus = productstatus;
                this.price = price;
                this.imageurl = imageurl;
                this.rating = rating;
                this.description = description;
	}
    
}
