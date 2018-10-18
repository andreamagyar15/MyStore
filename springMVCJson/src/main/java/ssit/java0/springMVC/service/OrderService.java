package ssit.java0.springMVC.service;

import ssit.java0.springMVC.domain.CartItem;
import ssit.java0.springMVC.domain.Product;
import ssit.java0.springMVC.dto.OrderRequest;

import java.util.List;

public interface OrderService {
    List<OrderRequest> listOrder(int id);
    int addToCart(int id,CartItem cartItem);
    void deleteFromCart(int id,int prodid);
    void checkout(int orderid);
    Boolean checkStatus(int orderid);
    Double getTotal(int id);
    double calculateTotal(List<OrderRequest> orderRequestList);
    void updateQuantity(int orderid,int quantity);

}
