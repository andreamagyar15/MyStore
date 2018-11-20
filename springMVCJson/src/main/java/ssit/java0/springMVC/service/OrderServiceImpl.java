package ssit.java0.springMVC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssit.java0.springMVC.DAO.OrderDAO;
import ssit.java0.springMVC.DAO.ProductDAO;
import ssit.java0.springMVC.domain.CartItem;
import ssit.java0.springMVC.domain.Product;
import ssit.java0.springMVC.domain.ProductType;
import ssit.java0.springMVC.dto.OrderRequest;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    private List<CartItem> cartItemList;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Override
    public List<OrderRequest> listOrder(int id) {
        List<OrderRequest> productList=new ArrayList<>();
        cartItemList=orderDAO.listOrder(id);
        for (CartItem cartItem:cartItemList) {
            OrderRequest orderRequest=new OrderRequest();
          Product product= productDAO.getProductById(cartItem.getProductType(),cartItem.getProductid());
          orderRequest.setProduct(product);
          orderRequest.setQuantity(cartItem.getQuantity());
          productList.add(orderRequest);
        }
        return productList;
    }

    @Override
    @Transactional
    public int addToCart(int id, CartItem cartItem) {
        if (id == 0) {
            int newID= orderDAO.createOrder(cartItem);
            orderDAO.addToCart(newID,cartItem);
            return newID;
        }else {
            if (orderDAO.containsProduct(id, cartItem.getProductid())) {
                orderDAO.incrementQuantity(id, cartItem.getProductid());
                return id;
            } else {
                return orderDAO.addToCart(id, cartItem);
            }
        }
    }

    @Override
    @Transactional
    public void deleteFromCart(int id,int prodid) {
        //orderDAO.removeFromTotal(productType,id,prodid);
        orderDAO.deleteFromCart(id,prodid);
    }

    @Override
    @Transactional
    public void checkout(int orderid) {
        orderDAO.checkout(orderid);
        decreaseQuantity(orderid);
    }

    @Override
    public Boolean checkStatus(int orderid) {
        return orderDAO.checkOrderStatus(orderid);
    }

    @Override
    public Double getTotal(int id) {
        double totalOrder=calculateTotal(listOrder(id));
        orderDAO.updateTotal(totalOrder,id);
        return orderDAO.getTotal(id);
    }

    public double calculateTotal(List<OrderRequest> orderRequests) {
        double total=0;
        for(int i=0;i<orderRequests.size();i++){
             int quantity=orderRequests.get(i).getQuantity();
             double price=orderRequests.get(i).getProduct().getPrice();
             total+=quantity*price;
        }
        return total;
    }

    @Override
    @Transactional
    public void updateQuantity(int orderid, int quantity) {
        orderDAO.updateQuantity(orderid,quantity);
    }

    public void decreaseQuantity(int orderid){
        cartItemList=orderDAO.listOrder(orderid);
        for (CartItem cartItem:cartItemList) {
            Product product = productDAO.getProductById(cartItem.getProductType(), cartItem.getProductid());
            productDAO.decreaseQuantityForProduct(cartItem.getProductid(),cartItem.getProductType(),cartItem.getQuantity());

        }
    }
}
