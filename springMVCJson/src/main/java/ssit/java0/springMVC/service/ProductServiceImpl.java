package ssit.java0.springMVC.service;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssit.java0.springMVC.DAO.ProductDAO;
import ssit.java0.springMVC.domain.Product;
import ssit.java0.springMVC.domain.ProductType;
import ssit.java0.springMVC.dto.ImageObjectBinary;

import java.io.IOException;
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
    @Transactional
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
    @Transactional
    public void deleteProduct(String type, int id) {
        try{
            ProductType enumType=ProductType.valueOf(type.toUpperCase());
            productDAO.deleteProduct(enumType,id);
        }catch (Exception e){

        }
    }

    @Override
    @Transactional
    public void updateProduct(ProductType type, int id,Product product) {
        try{
            ProductType enumType=ProductType.valueOf(type.name().toUpperCase());
            productDAO.updateProduct(enumType,id,product);

        }catch (Exception e){

        }

    }

    /**
     * Get the image array as String, convert in byte array, send to DB
     * @return the add image id
     * @throws IOException
     */
    @Override
    public int uploadImage(ImageObjectBinary imageBinary) throws IOException {
        String imageArray=imageBinary.getImageFileBinary();
        String img=imageArray.substring(23);
        byte[] stringim=Base64.decode(img);
       // ByteArrayInputStream imgByte=new ByteArrayInputStream(stringim);
        int imageId=productDAO.uploadImageInDB(imageBinary.getImageName(),stringim);
       // Image image=ImageIO.read(new ByteArrayInputStream(stringim));
       // BufferedImage bufIm=new BufferedImage(((BufferedImage) image).getWidth(),((BufferedImage) image).getHeight(),BufferedImage.TYPE_INT_RGB);
       // Graphics2D imggr=bufIm.createGraphics();
       // imggr.drawImage(image,null,null);
       // File file= new File("D:\\JAVA2018\\springMVCJson\\src\\main\\resources\\static\\img",imageBinary.getImageName()+".jpg");
       // ImageIO.write(bufIm,"jpg",file);
        return imageId;
    }

    @Override
    public List<Product> getSortedByDate() {
        List<Product> productList =productDAO.getSortedByDate();
        return productList;
    }
}
