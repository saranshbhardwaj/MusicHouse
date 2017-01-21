/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javabeans;

import java.io.Serializable;
import java.text.NumberFormat;

/**
 *
 * @author sony
 */
public class LineItem implements Serializable{
    
    private product product;
    private int quantity;

    public LineItem() {}

    public void setProduct(product p) {
        product = p;
    }

    public product getProduct() {
        return product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        double total = Double.parseDouble(product.getprice()) * quantity;
        return total;
    }

    public String getTotalCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(this.getTotal());
    
    }
}
