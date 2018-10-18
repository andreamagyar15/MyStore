package ssit.java0.springMVC.service;

import com.fasterxml.jackson.databind.util.EnumValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssit.java0.springMVC.DAO.ProductDAO;
import ssit.java0.springMVC.domain.Product;
import ssit.java0.springMVC.domain.ProductType;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<Product> getAll() {
        return productDAO.getAll();
    }
    @Override
    public void createProduct(Product product, String prodType) {
        try{
            ProductType enumType=ProductType.valueOf(prodType.toUpperCase());
            productDAO.createProduct(product,enumType);
        }catch (Exception e){

        }
    }
    @Override
    public Product getById(int id){
        List<Product> productList=getAll();
        List<Product> productFound=productList.stream().filter((Product product) -> {
            return product.getId()== id;})
                .collect(Collectors.toList());
      return  productFound.isEmpty()? null:productFound.get(0);
    }

    @Override
    public List<Product> listByType(String type) {
        try{
            ProductType enumType=ProductType.valueOf(type.toUpperCase());
            List<Product> productsByType=productDAO.getProductsByType(enumType);
            return productsByType;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void deleteProduct(String type, int id) {
        try{
            ProductType enumType=ProductType.valueOf(type.toUpperCase());
            productDAO.deleteProduct(enumType,id);
        }catch (Exception e){

        }
    }

    @Override
    public void updateProduct(ProductType type, int id,Product product) {
        try{
            ProductType enumType=ProductType.valueOf(type.name().toUpperCase());
            productDAO.updateProduct(enumType,id,product);

        }catch (Exception e){

        }

    }
}
