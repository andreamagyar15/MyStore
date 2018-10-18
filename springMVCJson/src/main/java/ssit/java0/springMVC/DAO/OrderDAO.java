package ssit.java0.springMVC.DAO;

import ssit.java0.springMVC.domain.CartItem;
import ssit.java0.springMVC.domain.ProductType;

import java.util.List;

public interface OrderDAO {
    List<CartItem> listOrder(int orderId);
    int addToCart(int id, CartItem cartItem);
    void deleteFromCart(int id,int prodid);
    void checkout(int orderid);
    int createOrder(CartItem cartItem);
    void updateTotal(double price,int orderid);
    double getTotal(int id);
    void removeFromTotal(ProductType productType,int id, int orderid);
    Boolean checkOrderStatus(int orderid);

    boolean containsProduct(int id, int productid);

    void incrementQuantity(int id, int productid);


    void updateQuantity(int productid,int newQuantity);
}
