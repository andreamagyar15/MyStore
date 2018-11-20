package ssit.java0.springMVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ssit.java0.springMVC.domain.Product;
import ssit.java0.springMVC.dto.CreateProductRequest;
import ssit.java0.springMVC.dto.ImageObjectBinary;
import ssit.java0.springMVC.dto.UpdateProductRequest;
import ssit.java0.springMVC.service.ProductService;

import java.io.IOException;
import java.util.List;

//@CrossOrigin(origins = "*",allowedHeaders = "*", maxAge = 3600)
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
    public void createProduct(  CreateProductRequest createProductRequest) throws IOException {
        Product product=getNewProduct(createProductRequest);
        ImageObjectBinary imageObj=getNewImage(createProductRequest);
        if(createProductRequest.getProdType().name().equals("TSHIRTS")||createProductRequest.getProdType().name().equals("SHOES")||createProductRequest.getProdType().name().equals("BAGS")||createProductRequest.getProdType().name().equals("HATS")){
            int imageid=productService.uploadImage(imageObj);
            product.setImageId(imageid);
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
     * @param
     */
    @RequestMapping(value="/secured/products/update/{id}",method=RequestMethod.POST)
    public void updateProduct(@PathVariable int id,UpdateProductRequest updateProductRequest){
        Product newProd=getUpdatedProduct(updateProductRequest);
        productService.updateProduct(updateProductRequest.getProdType(),id,newProd);
    }
    @RequestMapping(value="/products/sortbydate",method = RequestMethod.GET)
    public List<Product> listSortedByDate(){
        return productService.getSortedByDate();
    }

    public Product getUpdatedProduct(UpdateProductRequest updateProductRequest){
        Product prod=new Product();
        prod.setTitle(updateProductRequest.getTitle());
        prod.setDescription(updateProductRequest.getDescription());
        prod.setSize(updateProductRequest.getSize());
        prod.setPrice(updateProductRequest.getPrice());
        prod.setAmount(updateProductRequest.getAmount());
        prod.setArrival(updateProductRequest.getArrival());
        return prod;
    }
    public Product getNewProduct(CreateProductRequest createProductRequest){
        Product prod=new Product();
        prod.setTitle(createProductRequest.getTitle());
        prod.setDescription(createProductRequest.getDescription());
        prod.setSize(createProductRequest.getSize());
        prod.setPrice(createProductRequest.getPrice());
        prod.setAmount(createProductRequest.getAmount());
        prod.setArrival(createProductRequest.getArrival());
        return prod;
    }
    private ImageObjectBinary getNewImage(CreateProductRequest createProductRequest) {
        ImageObjectBinary imageObjectBinary =new ImageObjectBinary();
        imageObjectBinary.setImageName(createProductRequest.getImageName());
        imageObjectBinary.setImageFileBinary(createProductRequest.getImageInput());
        return  imageObjectBinary;
    }
}
