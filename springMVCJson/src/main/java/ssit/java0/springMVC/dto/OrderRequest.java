package ssit.java0.springMVC.dto;

import ssit.java0.springMVC.domain.CartItem;
import ssit.java0.springMVC.domain.Product;
import ssit.java0.springMVC.domain.ProductType;

public class OrderRequest {
    private Product product;
    private int quantity;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product cartItem) {
        this.product = cartItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
