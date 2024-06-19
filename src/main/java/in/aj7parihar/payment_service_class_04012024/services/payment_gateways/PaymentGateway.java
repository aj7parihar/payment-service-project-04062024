package in.aj7parihar.payment_service_class_04012024.services.payment_gateways;

import com.stripe.exception.StripeException;

public interface PaymentGateway {
    public String createPaymentLink(String orderId, Long amount) throws StripeException;
}
