package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;
import java.util.Arrays;

@Getter
public class Payment {
    String id;
    String method;
    Map<String, String> paymentData;
    Order order;
    String status;

    public Payment(String id, String method, String status, Map<String, String> paymentData, Order order) {
        this.id = id;
        this.paymentData = paymentData;

        if(method == null){
            throw new IllegalArgumentException();
        } else {
            this.method = method;
        }

        if(order == null){
            throw new IllegalArgumentException();
        } else {
            this.order = order;
        }
        this.setStatus(status);
    }

    public void setStatus(String status){
        if(PaymentStatus.contains(status)){
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}