package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    private Map<String, String> paymentData;
    private Order order;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "2306241695");

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira Sudrajat");
    }

    @Test
    void testCreatePaymentSuccess() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), paymentData, order);

        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(order, payment.getOrder());
    }

    @Test
    void testCreatePaymentWithInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), "GAWK", paymentData, order);
        });
    }

    @Test
    void testCreatePaymentWithNullOrder() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), paymentData, null);
        });
    }

    @Test
    void testCreatePaymentWithInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "HALO", PaymentStatus.PENDING.getValue(), paymentData, order);
        });
    }

    @Test
    void testCreatePaymentWithEmptyPaymentData() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), new HashMap<>(), order);
        assertTrue(payment.getPaymentData().isEmpty());
    }

    @Test
    void testSetValidStatus() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), paymentData, order);

        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());

        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetInvalidStatus() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), "PENDING", paymentData, order);

        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("GAWK");
        });
    }
}