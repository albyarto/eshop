package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    OrderService orderService;

    List<Payment> payments;
    Order order;
    Map<String, String> paymentData1;
    Map<String, String> paymentData2;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");

        paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "1234567890123456");

        paymentData2 = new HashMap<>();
        paymentData2.put("address", "Margonda");
        paymentData2.put("deliveryFee", "10");

        payments = new ArrayList<>();
        Payment payment1 = new Payment("payment-123", "VOUCHER", "PENDING", paymentData1, order);
        payment1.setStatus(PaymentStatus.SUCCESS.getValue());
        payments.add(payment1);

        Payment payment2 = new Payment("payment-456", "CASH_ON_DELIVERY", "PENDING", paymentData2, order);
        payment2.setStatus(PaymentStatus.REJECTED.getValue());
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        String paymentMethod = "VOUCHER";

        Payment expectedPayment = new Payment("new-payment-id", paymentMethod, PaymentStatus.SUCCESS.getValue(), paymentData1, order);
        doReturn(expectedPayment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, paymentMethod, paymentData1);

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(expectedPayment.getId(), result.getId());
        assertEquals(order.getId(), result.getOrder().getId());
        assertEquals(paymentMethod, result.getMethod());
        assertEquals(paymentData1, result.getPaymentData());
    }

    @Test
    void testSetStatusSuccess() {
        Payment payment = payments.get(0);

        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));
        payment = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), payment.getOrder().getStatus());
    }

    @Test
    void testSetInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("a5e93216-127c-43df-b7f1-89b720e496bb","VOUCHER","PENDING", paymentData1, order);
            paymentService.setStatus(payment, "HALO");
        });
    }

    @Test
    void testGetPaymentIfIdFound() {
        Payment payment = payments.get(0);

        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());

        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentIfIdNotFound() {
        doReturn(null).when(paymentRepository).findById("nonexistent-id");

        Payment result = paymentService.getPayment("nonexistent-id");

        assertNull(result);
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> result = paymentService.getAllPayments();

        assertEquals(payments.size(), result.size());
        assertEquals(payments.get(0).getId(), result.get(0).getId());
        assertEquals(payments.get(1).getId(), result.get(1).getId());
    }
}