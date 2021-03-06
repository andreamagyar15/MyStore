package ssit.java0.springMVC.DAO;

import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.jdbc.core.JdbcTemplate;
        import org.springframework.jdbc.core.RowMapper;
        import org.springframework.stereotype.Repository;
        import ssit.java0.springMVC.domain.Product;
        import ssit.java0.springMVC.domain.ProductType;

import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.List;
@Repository
public class ProductDAOimpl implements ProductDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     *  * @return all product from DB
     */
    @Override
    public List<Product> getAll() {
        String sql="SELECT * from allproductview";
        return jdbcTemplate.query(sql,
                new RowMapper<Product>() {
                    @Override
                    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                        Product product=new Product();
                        product.setId(resultSet.getInt("id"));
                        product.setTitle(resultSet.getString("title"));
                        product.setDescription(resultSet.getString("description"));
                        product.setAmount(resultSet.getInt("amount"));
                        product.setArrival(resultSet.getDate("arrived"));
                        product.setPrice(resultSet.getDouble("price"));
                        product.setSize(resultSet.getInt("size"));
                        product.setImage(resultSet.getBytes("img"));
                        return product;
                    }
                });
    }

    /**
     *
     * @param product new object
     * @param prodType the type of the object
     * @return new product object
     */
    @Override
    public Product createProduct(Product product, ProductType prodType) {
        String sql="INSERT INTO "+prodType.name().toLowerCase()+" (title,description,size,price,arrived,amount,imageid) VALUES (?,?,?,?,?,?,?) returning id";
        int prodId=  jdbcTemplate.queryForObject(sql,
                new RowMapper<Integer>() {
                    @Override
                    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                        return resultSet.getInt(1);

                    }
                }, product.getTitle(),product.getDescription(),product.getSize(),product.getPrice(),product.getArrival(),product.getAmount(),product.getImageId());
        product.setId(prodId);
        return product;
    }

    /**
     *
     * @param type of product
     * @return a list of products the same type
     */
    @Override
    public List<Product> getProductsByType(ProductType type) {
        String sql = "SELECT * FROM "+type.name().toLowerCase()+"view";
        return jdbcTemplate.query(sql, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setTitle(resultSet.getString("title"));
                product.setDescription(resultSet.getString("description"));
                product.setAmount(resultSet.getInt("amount"));
                product.setArrival(resultSet.getDate("arrived"));
                product.setPrice(resultSet.getDouble("price"));
                product.setSize(resultSet.getInt("size"));
                product.setImage(resultSet.getBytes("img"));
                return product;
            }
        });
    }

    /**
     * Delete a product form DB
     * @param prodType product type
     * @param id product id form DB
     */
    @Override
    public void deleteProduct(ProductType prodType,int id) {
        String sql = "DELETE FROM "+prodType.name().toLowerCase()+" WHERE id="+id;
        jdbcTemplate.update(sql);
    }

    /**
     *Update the product values
     * @param prodType product type
     * @param id product id from DB
     * @param product object with new values
     */
    @Override
    public void updateProduct(ProductType prodType,int id,Product product) {
        String sql="UPDATE "+prodType.name().toLowerCase()+" SET title='"+product.getTitle()+"', description='"+product.getDescription()+"',amount='"+product.getAmount()+"',arrived='"+product.getArrival()+"',price='"+product.getPrice()+"',size='"+product.getSize()+"' WHERE id="+id;
        jdbcTemplate.update(sql);
    }

    /**
     * Get a product from DB
     * @param productType product type
     * @param id product id
     * @return the product object with id
     */
    @Override
    public Product getProductById(ProductType productType, int id) {
        String sql="SELECT * FROM "+productType.name().toLowerCase()+"view"+" WHERE id="+id;
        return jdbcTemplate.queryForObject(sql, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setTitle(resultSet.getString("title"));
                product.setDescription(resultSet.getString("description"));
                product.setAmount(resultSet.getInt("amount"));
                product.setArrival(resultSet.getDate("arrived"));
                product.setPrice(resultSet.getDouble("price"));
                product.setSize(resultSet.getInt("size"));
                product.setImage(resultSet.getBytes("img"));
                return product;
            }
        });
    }

    /**
     *Decrease the quantity of the product
     * @param productid product id
     * @param productType product type
     * @param quantity to be removed
     */
    @Override
    public void decreaseQuantityForProduct(int productid, ProductType productType, int quantity) {
        String sql="UPDATE "+productType.name()+" SET amount=amount-"+quantity+" WHERE id="+productid ;
        jdbcTemplate.update(sql);
    }

    /**
     * Upload image as byte array
     * @param name image name
     * @param imgByte byte array of the image
     * @return the uploaded image id
     */
    @Override
    public int uploadImageInDB(String name, byte[] imgByte) {
        String sql="INSERT INTO images (imgname,img) VALUES (?,?) returning id";
        int imageId=  jdbcTemplate.queryForObject(sql,
                new RowMapper<Integer>() {
                    @Override
                    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                        return resultSet.getInt(1);
                    }
                },name,imgByte);
        return imageId;
    }

    @Override
    public List<Product> getSortedByDate() {
        String sql="SELECT * from allproductview ORDER BY arrived DESC";
        return jdbcTemplate.query(sql,
                new RowMapper<Product>() {
                    @Override
                    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                        Product product=new Product();
                        product.setId(resultSet.getInt("id"));
                        product.setTitle(resultSet.getString("title"));
                        product.setDescription(resultSet.getString("description"));
                        product.setAmount(resultSet.getInt("amount"));
                        product.setArrival(resultSet.getDate("arrived"));
                        product.setPrice(resultSet.getDouble("price"));
                        product.setSize(resultSet.getInt("size"));
                        product.setImage(resultSet.getBytes("img"));
                        return product;
                    }
                });
    }

}
