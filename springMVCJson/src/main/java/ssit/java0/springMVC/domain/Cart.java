package ssit.java0.springMVC.domain;

import java.util.List;

public class Cart {
    private int orderId;
    private List<CartItem> cartItems;
    private double total;

    public Cart(int orderId, List<CartItem> cartItems, double total) {
        this.orderId = orderId;
        this.cartItems = cartItems;
        this.total = total;
    }

    public int getCartId() {
        return orderId;
    }

    public void setCartId(int cartId) {
        this.orderId = cartId;
    }

    public List<CartItem> getCartItem() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems= cartItems;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
