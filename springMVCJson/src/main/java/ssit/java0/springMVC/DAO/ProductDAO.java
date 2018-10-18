package ssit.java0.springMVC.DAO;

import ssit.java0.springMVC.domain.Product;
import ssit.java0.springMVC.domain.ProductType;

import java.util.List;

public interface ProductDAO {
    public List<Product> getAll();
    public Product createProduct(Product product, ProductType prodType);

    List<Product> getProductsByType(ProductType type);
    public void deleteProduct(ProductType prodType, int id);
    void updateProduct(ProductType prodType,int id,Product product);
    Product getProductById(ProductType productType,int id);
    void decreaseQuantityForProduct(int productid, ProductType productType, int quantity);
}
