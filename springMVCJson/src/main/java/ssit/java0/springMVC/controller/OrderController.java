package ssit.java0.springMVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import ssit.java0.springMVC.domain.CartItem;
import ssit.java0.springMVC.domain.Information;
import ssit.java0.springMVC.domain.Product;
import ssit.java0.springMVC.dto.DeleteObject;
import ssit.java0.springMVC.dto.OrderRequest;
import ssit.java0.springMVC.service.OrderService;

import java.util.List;

//@CrossOrigin(origins = "*",allowedHeaders = "*", maxAge = 3600)
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Mapping: list the order details
     * @param id order id
     * @return cart elements
     */
    @RequestMapping(value="/order/all/{id}" ,method=RequestMethod.GET)
    public List<OrderRequest> listOrder(@PathVariable int id){
        try{
            if(orderService.checkStatus(id)==false  ){
                List<OrderRequest> orderList=orderService.listOrder(id);
                return orderList;
            }else {
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }

    /**
     * Mapping: add product to cart
     * @param id order id
     * @param cartItem
     * @return an object with the order id
     */
    @RequestMapping(value="/order/add/{id}" ,method=RequestMethod.POST)
    @ResponseBody
    public Information addToCart(@PathVariable int id,@RequestBody CartItem cartItem){
        int orderId=orderService.addToCart(id,cartItem);
        return new Information("order id",orderId);
    }

    /**
     *Mapping: Delete product from cart
     * @param id order id
     * @param prod product id
     */
    @RequestMapping(value="/order/delete/{id}/{prod}" ,method=RequestMethod.POST )
    public void deleteFromCart(@PathVariable int id, @PathVariable int prod){
        orderService.deleteFromCart(id,prod);
    }

    /**
     *Mapping: Get the order total price
     * @param id
     * @return order value
     */
    @RequestMapping(value="/order/total/{id}" ,method=RequestMethod.GET)
    public Double getTotal(@PathVariable int id){
        try{
            if(orderService.checkStatus(id)==false ) {
                Double totalOrder = orderService.getTotal(id);
                return totalOrder;
            }else{
                return 0.0;
            }
        }catch (Exception e){
            return null;
        }
    }

    /**
     *Mapping: Checkout order
     * @param id
     * @return object with the checkout status
     */
    @RequestMapping(value="/order/checkout/{id}", method=RequestMethod.POST)
    @ResponseBody
    public Information checkout(@PathVariable int id){
        try {
            if(orderService.checkStatus(id)==false && orderService.checkStatus(id)!=null){
                orderService.checkout(id);
                return new Information("Checked out");
            }else{
                return new Information("Error, the order was checked out");
            }
        }catch (EmptyResultDataAccessException e){
            return new Information("Incorrect order number");
        }
    }

    /**
     *Mapping: Update the order quantity
     * @param id order product id
     * @param quantity new quantity
     */
    @RequestMapping(value="/order/quantity/{id}/{quantity}", method=RequestMethod.POST)
    public void updateQuantity(@PathVariable int id,@PathVariable int quantity){
        orderService.updateQuantity(id,quantity);
    }

}
