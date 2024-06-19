package in.aj7parihar.payment_service_class_04012024.services.payment_gateways;

import org.springframework.stereotype.Service;

@Service("razorpay")
public class RazorPayPaymentService implements PaymentGateway {
    public String createPaymentLink(String orderId, Long amount){
        return null;
    }
}
