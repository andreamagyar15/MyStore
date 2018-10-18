package ssit.java0.springMVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ssit.java0.springMVC.domain.Product;
import ssit.java0.springMVC.domain.ProductType;
import ssit.java0.springMVC.dto.CreateProductRequest;
import ssit.java0.springMVC.service.ProductService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin(origins = "*",allowedHeaders = "*", maxAge = 3600)
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * Mapping: list all product
     * @return list of the products
     */
    @RequestMapping("/products")
    public List<Product> listProduct(){
        List<Product> productList=productService.getAll();
        return productList;
    }

    /**
     * Mapping : get a product by id
     * @param id product id
     * @return product from DB
     */
    @RequestMapping("/products/{id}")
    public Product getProduct(@PathVariable int id){
        Product product=productService.getById(id);
        return product;
    }
    /**
     *
     * @param type product type
     * @return list of products with the same type
     */
    @RequestMapping("/products/categories/{type}")
    public List<Product> listProductByTyoe(@PathVariable String type){
        List<Product> productList=productService.listByType(type);
        return productList;
    }

    /**
     *
     * @param createProductRequest object with product details
     */
    @RequestMapping(value="/secured/products" ,method=RequestMethod.POST)
    public void createProduct(CreateProductRequest createProductRequest){
        Product product=getNewProduct(createProductRequest);
        if(createProductRequest.getProdType().name().equals("TSHIRTS")||createProductRequest.getProdType().name().equals("SHOES")||createProductRequest.getProdType().name().equals("BAGS")||createProductRequest.getProdType().name().equals("HATS")){
            productService.createProduct(product,createProductRequest.getProdType().name());
        }
    }

    /**
     * Mapping: delete a product from DB
     * @param type
     * @param id
     */
    @RequestMapping(value="/secured/products/delete/{type}/{id}",method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable String type, @PathVariable int id){
        productService.deleteProduct(type,id);
    }

    /**
     * Mapping: update product details
     * @param id
     * @param createProductRequest
     */
    @RequestMapping(value="/secured/products/update/{id}",method=RequestMethod.POST)
    public void updateProduct(@PathVariable int id,CreateProductRequest createProductRequest){
        Product newProd=getNewProduct(createProductRequest);
        productService.updateProduct(createProductRequest.getProdType(),id,newProd);
    }

    public Product getNewProduct(CreateProductRequest createProductRequest){
        Product prod=new Product();
        prod.setTitle(createProductRequest.getTitle());
        prod.setDescription(createProductRequest.getDescription());
        prod.setSize(createProductRequest.getSize());
        prod.setPrice(createProductRequest.getPrice());
        prod.setImgUrl(createProductRequest.getImgUrl());
        prod.setAmount(createProductRequest.getAmount());
        prod.setArrival(createProductRequest.getArrival());
        return prod;
    }
}
