package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

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
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        // Coba mengedit produk dengan id sama
        Product updatedProduct = new Product();
        updatedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        updatedProduct.setProductName("Sampo Cap Bambang Edisi Baru");
        updatedProduct.setProductQuantity(150);
        productRepository.save(updatedProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();

        // Cek apakah nama atau quantity produk telah teredit
        assertEquals(updatedProduct.getProductId(), savedProduct.getProductId());
        assertEquals(updatedProduct.getProductName(), savedProduct.getProductName());
        assertEquals(updatedProduct.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testEditProductNotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("invalid-id");
        updatedProduct.setProductName("Produk Tidak Ada");
        updatedProduct.setProductQuantity(10);

        // Coba mengedit produk yang tidak ada
        productRepository.save(updatedProduct);

        // Repository tetap kosong karena produk tidak ditemukan
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteProductSuccess() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteProductNotFound() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        // Coba menghapus produk yang tidak ada
        productRepository.delete("invalid-id");

        // Pastikan produk yang ada tetap tidak terhapus
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", savedProduct.getProductId());
    }

    @Test
    void testFindByIdSuccess() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Optional<Product> foundProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertTrue(foundProduct.isPresent());
        assertEquals(product.getProductId(), foundProduct.get().getProductId());
        assertEquals(product.getProductName(), foundProduct.get().getProductName());
        assertEquals(product.getProductQuantity(), foundProduct.get().getProductQuantity());
    }

    @Test
    void testFindByIdNotFound() {
        Optional<Product> foundProduct = productRepository.findById("invalid-id");

        assertFalse(foundProduct.isPresent());
    }

    @Test
    void testEditProductInMiddle() {
        // Produk pertama
        Product product1 = new Product();
        product1.setProductId("1111");
        product1.setProductName("Sampo A");
        product1.setProductQuantity(10);
        productRepository.create(product1);

        // Produk kedua (target update)
        Product product2 = new Product();
        product2.setProductId("2222");
        product2.setProductName("Sampo B");
        product2.setProductQuantity(20);
        productRepository.create(product2);

        // Produk ketiga
        Product product3 = new Product();
        product3.setProductId("3333");
        product3.setProductName("Sampo C");
        product3.setProductQuantity(30);
        productRepository.create(product3);

        // Produk yang akan menggantikan produk2
        Product updatedProduct2 = new Product();
        updatedProduct2.setProductId("2222"); // ID sama dengan product2
        updatedProduct2.setProductName("Sampo B - Update");
        updatedProduct2.setProductQuantity(50);

        // Simpan perubahan
        productRepository.save(updatedProduct2);

        // Ambil produk yang diupdate
        Optional<Product> foundProduct = productRepository.findById("2222");
        assertTrue(foundProduct.isPresent());
        assertEquals("Sampo B - Update", foundProduct.get().getProductName());
        assertEquals(50, foundProduct.get().getProductQuantity());
    }

    @Test
    void testEditProductAtEnd() {
        // Produk pertama
        Product product1 = new Product();
        product1.setProductId("1111");
        product1.setProductName("Sampo A");
        product1.setProductQuantity(10);
        productRepository.create(product1);

        // Produk kedua
        Product product2 = new Product();
        product2.setProductId("2222");
        product2.setProductName("Sampo B");
        product2.setProductQuantity(20);
        productRepository.create(product2);

        // Produk ketiga (akan diperbarui)
        Product product3 = new Product();
        product3.setProductId("3333");
        product3.setProductName("Sampo C");
        product3.setProductQuantity(30);
        productRepository.create(product3);

        // Produk yang akan menggantikan produk 3
        Product updatedProduct3 = new Product();
        updatedProduct3.setProductId("3333");
        updatedProduct3.setProductName("Sampo C - Update");
        updatedProduct3.setProductQuantity(60);

        // Simpan perubahan
        productRepository.save(updatedProduct3);

        // Ambil produk yang diupdate
        Optional<Product> foundProduct = productRepository.findById("3333");
        assertTrue(foundProduct.isPresent());
        assertEquals("Sampo C - Update", foundProduct.get().getProductName());
        assertEquals(60, foundProduct.get().getProductQuantity());
    }
}