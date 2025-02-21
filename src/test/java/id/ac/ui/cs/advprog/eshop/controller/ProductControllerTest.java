package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.ui.ConcurrentModel;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private Model model;

    @BeforeEach
    void setUp() {
        model = new ConcurrentModel();
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);

        assertEquals("CreateProduct", viewName);
        assertTrue(model.containsAttribute("product"));
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String result = productController.createProductPost(product, model);

        assertEquals("redirect:list", result);
        verify(productService, times(1)).create(product);
    }

    @Test
    void testProductListPage() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productService.findAll()).thenReturn(products);

        String viewName = productController.productListPage(model);

        assertEquals("ProductList", viewName);
        assertEquals(products, model.getAttribute("products"));
        verify(productService, times(1)).findAll();
    }

    @Test
    void testEditProductPage_Success() {
        Product product = new Product();
        product.setProductId("123");
        when(productService.findById("123")).thenReturn(product);

        String viewName = productController.editProductPage("123", model);

        assertEquals("EditProduct", viewName);
        assertEquals(product, model.getAttribute("product"));
        verify(productService, times(1)).findById("123");
    }

    @Test
    void testEditProductPage_NotFound() {
        when(productService.findById("999")).thenReturn(null);

        String viewName = productController.editProductPage("999", model);

        assertEquals("redirect:/product/list", viewName);
        verify(productService, times(1)).findById("999");
    }

    @Test
    void testEditProductPost() {
        String result = productController.editProductPost("123", "New Product", 20);

        assertEquals("redirect:/product/list", result);
        verify(productService, times(1)).update("123", "New Product", 20);
    }

    @Test
    void testDeleteProduct() {
        String result = productController.deleteProduct("123");

        assertEquals("redirect:/product/list", result);
        verify(productService, times(1)).delete("123");
    }
}
