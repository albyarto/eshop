package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    PaymentRepository paymentRepository;
    Payment payment1;
    Payment payment2;
    Order order1;
    Order order2;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);

        List<Product> products = new ArrayList<>();
        products.add(product1);

        order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira Sudrajat");
        order2 = new Order("12345678-013e-4z06-b543-adn3j14d32dd", products, 1708560000L, "Bambang");

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        payment1 = new Payment("fa254f7n-3e45-667u-9988-98jh77yui900", "VOUCHER", "PENDING", paymentData1, order1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("address", "Margonda");
        paymentData2.put("deliveryFee", "10");
        payment2 = new Payment("a2c62328-4a37-4664-83c7-f32db8620155", "CASH_ON_DELIVERY", "SUCCESS", paymentData2, order2);
    }

    @Test
    void testSaveCreate() {
        Payment result = paymentRepository.save(payment1);

        Payment findResult = paymentRepository.findById(payment1.getId());
        assertEquals(payment1.getId(), result.getId());
        assertPaymentEquals(payment1, findResult);
    }

    @Test
    void testSaveUpdate() {
        paymentRepository.save(payment1);

        Map<String, String> updatedData = new HashMap<>();
        updatedData.put("voucherCode", "ESHOP12321321");
        Payment updatedPayment = new Payment(payment1.getId(), "CASH_ON_DELIVERY", "SUCCESS", updatedData, order1);

        Payment result = paymentRepository.save(updatedPayment);

        Payment findResult = paymentRepository.findById(payment1.getId());
        assertEquals(updatedPayment.getId(), result.getId());
        assertPaymentEquals(updatedPayment, findResult);

        assertEquals(1, paymentRepository.findAll().size());
    }

    @Test
    void testFindByIdIfIdFound() {
        paymentRepository.save(payment1);

        Payment foundPayment = paymentRepository.findById(payment1.getId());

        assertNotNull(foundPayment);
        assertEquals(payment1.getId(), foundPayment.getId());
        assertEquals(payment1.getMethod(), foundPayment.getMethod());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        Payment foundPayment = paymentRepository.findById("idbodong");

        assertNull(foundPayment);
    }

    @Test
    void testFindAll() {
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        List<Payment> allPayments = paymentRepository.findAll();

        assertEquals(2, allPayments.size());
        assertTrue(allPayments.contains(payment1));
        assertTrue(allPayments.contains(payment2));
    }

    @Test
    void testFindAllEmptyRepository() {
        List<Payment> allPayments = paymentRepository.findAll();

        assertTrue(allPayments.isEmpty());
    }

    @Test
    void testClearRepository() {
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        paymentRepository.clear();

        List<Payment> allPayments = paymentRepository.findAll();
        assertTrue(allPayments.isEmpty());
    }

    // Extract Method: Moved assertion logic to a reusable method
    private void assertPaymentEquals(Payment expected, Payment actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getMethod(), actual.getMethod());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getPaymentData(), actual.getPaymentData());
    }
}