package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    Map<String, String> paymentData;
    Order order;

    @Setter
    String status;

    public Payment(String id, String method, String status, Map<String, String> paymentData, Order order) {
    }
}