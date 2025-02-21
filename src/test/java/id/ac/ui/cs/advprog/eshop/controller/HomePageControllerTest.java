package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class HomePageControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private HomePageController homePageController;

    @Test
    void testHomePage() {
        Model model = mock(Model.class);
        String viewName = homePageController.createProductPage(model);
        assertEquals("HomePage", viewName);
    }
}
