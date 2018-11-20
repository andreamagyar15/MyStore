package ssit.java0.springMVC.controllerTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import ssit.java0.springMVC.DAO.ProductDAO;
import ssit.java0.springMVC.controller.ProductController;
import ssit.java0.springMVC.domain.Product;
import ssit.java0.springMVC.service.ProductService;
@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
    private MockMvc mockMvc;
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;
    @Mock
    private ProductDAO productDAO;
    @Test
    public void createProductTest(){
        Product product=new Product();

    }
    //TODO: test
}
