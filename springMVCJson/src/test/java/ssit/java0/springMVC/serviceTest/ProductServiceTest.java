package ssit.java0.springMVC.serviceTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import ssit.java0.springMVC.DAO.ProductDAO;
import ssit.java0.springMVC.domain.Product;
import ssit.java0.springMVC.service.ProductService;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    private MockMvc mockMvc;
    Product product;
    @InjectMocks
    ProductService productService;
    @Mock
    private ProductDAO productDAO;
    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }
    // TODO : test
}

