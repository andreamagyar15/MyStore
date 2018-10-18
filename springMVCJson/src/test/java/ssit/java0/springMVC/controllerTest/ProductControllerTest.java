package ssit.java0.springMVC.controllerTest;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import ssit.java0.springMVC.controller.ProductController;
import ssit.java0.springMVC.service.ProductService;
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductControllerTest {
    private MockMvc mockMvc;
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;
    //TODO: test
}
