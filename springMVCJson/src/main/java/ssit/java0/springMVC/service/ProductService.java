package ssit.java0.springMVC.service;

import ssit.java0.springMVC.domain.Product;
import ssit.java0.springMVC.domain.ProductType;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    void createProduct(Product product, String prodType);

    Product getById(int id);

    List<Product> listByType(String type);
    void deleteProduct(String type, int id);
    void updateProduct(ProductType type, int id,Product product);

}
