package ssit.java0.springMVC.domain;

public class Cart {
    private int orderId;
    private CartItem cartItem;
    private double total;

    public Cart(int orderId, CartItem cartItem, double total) {
        this.orderId = orderId;
        this.cartItem = cartItem;
        this.total = total;
    }

    public int getCartId() {
        return orderId;
    }

    public void setCartId(int cartId) {
        this.orderId = cartId;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
