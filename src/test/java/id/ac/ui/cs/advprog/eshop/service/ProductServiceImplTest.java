package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = new Product();
        product1.setProductId("1111");
        product1.setProductName("Sampo A");
        product1.setProductQuantity(10);

        product2 = new Product();
        product2.setProductId("2222");
        product2.setProductName("Sampo B");
        product2.setProductQuantity(20);
    }

    @Test
    void testCreate() {
        when(productRepository.create(product1)).thenReturn(product1);

        Product createdProduct = productService.create(product1);

        assertNotNull(createdProduct);
        assertEquals("1111", createdProduct.getProductId());
        verify(productRepository, times(1)).create(product1);
    }

    @Test
    void testFindAll() {
        Iterator<Product> productIterator = Arrays.asList(product1, product2).iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> products = productService.findAll();

        assertEquals(2, products.size());
        assertEquals("Sampo A", products.get(0).getProductName());
        assertEquals("Sampo B", products.get(1).getProductName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdSuccess() {
        when(productRepository.findById("1111")).thenReturn(Optional.of(product1));

        Product foundProduct = productService.findById("1111");

        assertNotNull(foundProduct);
        assertEquals("1111", foundProduct.getProductId());
        verify(productRepository, times(1)).findById("1111");
    }

    @Test
    void testFindByIdNotFound() {
        when(productRepository.findById("9999")).thenReturn(Optional.empty());

        Product foundProduct = productService.findById("9999");

        assertNull(foundProduct);
        verify(productRepository, times(1)).findById("9999");
    }

    @Test
    void testUpdateSuccess() {
        when(productRepository.findById("1111")).thenReturn(Optional.of(product1));

        productService.update("1111", "Sampo A - Updated", 50);

        verify(productRepository, times(1)).save(argThat(updatedProduct ->
                updatedProduct.getProductId().equals("1111") &&
                        updatedProduct.getProductName().equals("Sampo A - Updated") &&
                        updatedProduct.getProductQuantity() == 50
        ));
    }

    @Test
    void testUpdateProductNotFound() {
        when(productRepository.findById("9999")).thenReturn(Optional.empty());

        productService.update("9999", "Invalid Product", 10);

        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void testDelete() {
        doNothing().when(productRepository).delete("1111");

        productService.delete("1111");

        verify(productRepository, times(1)).delete("1111");
    }
}
