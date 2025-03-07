package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;

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

        if (!PaymentMethod.contains(method)) {
            throw new IllegalArgumentException();
        } else {
            this.method = method;
        }

        if (method.equals(PaymentMethod.VOUCHER.getValue())) {
            validateVoucherPayment();
        } else if (method.equals(PaymentMethod.CASH_ON_DELIVERY.getValue())) {
            validateCashOnDeliveryPayment();
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

    private void validateVoucherPayment() {
        String voucher = paymentData.get("voucherCode");
        if ((voucher != null && voucher.length() == 16) && voucher.startsWith("ESHOP") && countDigit(voucher) == 8) {
            this.status = PaymentStatus.SUCCESS.getValue();
        } else {
            this.status = PaymentStatus.REJECTED.getValue();
        }
    }

    private void validateCashOnDeliveryPayment() {
        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");
        if (address != null && !address.isEmpty() && deliveryFee != null && !deliveryFee.isEmpty()) {
            this.status = PaymentStatus.SUCCESS.getValue();
        } else {
            this.status = PaymentStatus.REJECTED.getValue();
        }
    }

    private int countDigit(String code){
        int count = 0;
        for (int i = 0; i < code.length(); i++) {
            if (Character.isDigit(code.charAt(i))) {
                count++;
            }
        }
        return count;
    }
}