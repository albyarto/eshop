package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    private ProductRepository productRepository;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();

        product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);
    }

    @Test
    void testCreateAndFind() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        assertEquals(product1.getProductName(), savedProduct.getProductName());
        assertEquals(product1.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        productRepository = new ProductRepository(); // Gunakan instance baru agar kosong
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProductSuccess() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId(product1.getProductId());
        updatedProduct.setProductName("Sampo Cap Bambang Edisi Baru");
        updatedProduct.setProductQuantity(150);
        productRepository.save(updatedProduct);

        Optional<Product> foundProduct = productRepository.findById(product1.getProductId());
        assertTrue(foundProduct.isPresent());
        assertEquals("Sampo Cap Bambang Edisi Baru", foundProduct.get().getProductName());
        assertEquals(150, foundProduct.get().getProductQuantity());
    }

    @Test
    void testEditProductNotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("invalid-id");
        updatedProduct.setProductName("Produk Tidak Ada");
        updatedProduct.setProductQuantity(10);
        productRepository.save(updatedProduct);

        Optional<Product> foundProduct = productRepository.findById("invalid-id");
        assertFalse(foundProduct.isPresent());
    }

    @Test
    void testDeleteProductSuccess() {
        productRepository.delete(product1.getProductId());

        Optional<Product> foundProduct = productRepository.findById(product1.getProductId());
        assertFalse(foundProduct.isPresent());
    }

    @Test
    void testDeleteProductNotFound() {
        productRepository.delete("invalid-id");

        Optional<Product> foundProduct = productRepository.findById(product1.getProductId());
        assertTrue(foundProduct.isPresent());
    }

    @Test
    void testFindByIdSuccess() {
        Optional<Product> foundProduct = productRepository.findById(product1.getProductId());
        assertTrue(foundProduct.isPresent());
        assertEquals(product1.getProductId(), foundProduct.get().getProductId());
        assertEquals(product1.getProductName(), foundProduct.get().getProductName());
        assertEquals(product1.getProductQuantity(), foundProduct.get().getProductQuantity());
    }

    @Test
    void testFindByIdNotFound() {
        Optional<Product> foundProduct = productRepository.findById("invalid-id");
        assertFalse(foundProduct.isPresent());
    }

    @Test
    void testEditProductInMiddle() {
        Product product3 = new Product();
        product3.setProductId("3333");
        product3.setProductName("Sampo C");
        product3.setProductQuantity(30);
        productRepository.create(product3);

        Product updatedProduct2 = new Product();
        updatedProduct2.setProductId(product2.getProductId());
        updatedProduct2.setProductName("Sampo B - Update");
        updatedProduct2.setProductQuantity(50);
        productRepository.save(updatedProduct2);

        Optional<Product> foundProduct = productRepository.findById(product2.getProductId());
        assertTrue(foundProduct.isPresent());
        assertEquals("Sampo B - Update", foundProduct.get().getProductName());
        assertEquals(50, foundProduct.get().getProductQuantity());
    }

    @Test
    void testEditProductAtEnd() {
        Product product3 = new Product();
        product3.setProductId("3333");
        product3.setProductName("Sampo C");
        product3.setProductQuantity(30);
        productRepository.create(product3);

        Product updatedProduct3 = new Product();
        updatedProduct3.setProductId("3333");
        updatedProduct3.setProductName("Sampo C - Update");
        updatedProduct3.setProductQuantity(60);
        productRepository.save(updatedProduct3);

        Optional<Product> foundProduct = productRepository.findById("3333");
        assertTrue(foundProduct.isPresent());
        assertEquals("Sampo C - Update", foundProduct.get().getProductName());
        assertEquals(60, foundProduct.get().getProductQuantity());
    }
}