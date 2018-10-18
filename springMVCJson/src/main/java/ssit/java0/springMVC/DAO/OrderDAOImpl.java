package ssit.java0.springMVC.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ssit.java0.springMVC.domain.CartItem;
import ssit.java0.springMVC.domain.Product;
import ssit.java0.springMVC.domain.ProductType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class OrderDAOImpl implements OrderDAO {
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * List the order
     * @param orderId order id
     * @return all cart elements with id
     */
    @Override
    public List<CartItem> listOrder(int orderId) {
        String sql = "SELECT * FROM orderdetail WHERE orderid="+orderId;
        return jdbcTemplate.query(sql, new RowMapper<CartItem>() {
            @Override
            public CartItem mapRow(ResultSet resultSet, int i) throws SQLException {
                CartItem cart=new CartItem();
                cart.setProductid(resultSet.getInt("productid"));
                cart.setQuantity(resultSet.getInt("quantity"));
                String prodtype=resultSet.getString("productType");
                if(prodtype==null){
                    cart.setProductType(null);
                }else{
                    cart.setProductType(ProductType.valueOf(prodtype));
                }
                return cart;
            }
        });
    }
    /**
     *  Add a product to cart
     * @param id the new product id
     * @param cartItem object with product details
     * @return new cart item id
     */
    @Override
    public int addToCart(int id, CartItem cartItem) {
        String sql = "INSERT INTO orderdetail (productid,quantity,productType,orderid) VALUES ('" + cartItem.getProductid() + "','" + cartItem.getQuantity() + "','" + cartItem.getProductType() + "','" + id + "')";
        jdbcTemplate.update(sql);
        return id;
    }

    /**
     * Delete a product from cart
     * @param id order id
     * @param prodid product id
     */
    @Override
    public void deleteFromCart(int id,int prodid) {
        String sql = "DELETE FROM orderdetail WHERE orderid='"+id+"' AND productid='"+prodid+"'";
        jdbcTemplate.update(sql);
    }

    /**
     * Checkout an order
     * @param orderid order id
     */
    @Override
    public void checkout(int orderid) {
        String sql="UPDATE orders SET status=true WHERE id="+orderid;
        jdbcTemplate.update(sql);
    }

    /**
     * Creates an order in DB
     * @param cartItem object with the new cart element
     * @return the new order id
     */
    @Override
    public int createOrder(CartItem cartItem) {
        String sql="INSERT INTO orders (customerid,status) VALUES (0,false) returning id"  ;
        int id=jdbcTemplate.queryForObject(sql, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("id");
            }
        });
           return id;
    }

    /**
     * Update the total price
     * @param price order price
     * @param orderid order id
     */
    @Override
    public void updateTotal(double price,int orderid) {
        String sql="UPDATE orders SET total="+price+" WHERE id="+orderid;
        jdbcTemplate.update(sql);
    }

    /**
     * Get the order total
     * @param id order id
     * @return the total of the order
     */
    @Override
    public double getTotal(int id) {
        try {
            String sql = "SELECT total FROM orders WHERE id=" + id;
            double totalPrice = jdbcTemplate.queryForObject(sql, new RowMapper<Double>() {
                @Override
                public Double mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getDouble("total");
                }
            });
            return totalPrice;
        }catch (EmptyResultDataAccessException e){
            return 0;
        }
    }

    /**
     * Remove from total
     * @param productType product type
     * @param id product id
     * @param orderid order is
     */
    @Override
    public void removeFromTotal(ProductType productType,int id, int orderid) {
        Double price=productDAO.getProductById(productType,id).getPrice();
        String sql="UPDATE orders SET total=total-"+price+" WHERE id="+orderid;
        jdbcTemplate.update(sql);
    }

    /**
     * Check the order status
     * @param orderid order id
     * @return the order status, true if it was checked out before
     * @throws EmptyResultDataAccessException
     * @throws NullPointerException
     */
    @Override
    public Boolean checkOrderStatus(int orderid) throws EmptyResultDataAccessException,NullPointerException{

            String sql="SELECT status FROM orders WHERE id="+orderid;
            boolean status=jdbcTemplate.queryForObject(sql, new RowMapper<Boolean>() {
                @Override
                public Boolean mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getBoolean("status");
                }
            });
            return status;
    }

    /**
     * Check if the order contains the product
     * @param id order id
     * @param productid product id
     * @return order contains the product
     */
    @Override
    public boolean containsProduct(int id, int productid) {
        String sql="SELECT * FROM orderdetail WHERE orderid="+id+" AND productid="+productid;
        System.out.println(sql);
       try {
           int i = jdbcTemplate.queryForObject(sql, new RowMapper<Integer>() {
               @Override
               public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                   return resultSet.getInt("orderid");
               }
           });
           return true;
       }catch (EmptyResultDataAccessException e) {
           return false;
       }
    }

    /**
     * Increments the product quantity in order DB
     * @param id order id
     * @param productid product id
     */
    @Override
    public void incrementQuantity(int id, int productid) {
        String sql="UPDATE orderdetail SET quantity=quantity+1 WHERE orderid="+id +" AND productid="+productid;
        jdbcTemplate.update(sql);
    }

    /**
     * Update the quantity
     * @param productid product id
     * @param newQuantity new quantity value
     */
    @Override
    public void updateQuantity(int productid, int newQuantity) {
        String sql="UPDATE orderdetail SET quantity="+newQuantity+" WHERE productid="+productid;
        jdbcTemplate.update(sql);
    }

}
