package in.aj7parihar.payment_service_class_04012024.services;

import com.stripe.exception.StripeException;
import in.aj7parihar.payment_service_class_04012024.services.payment_gateways.PaymentGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    // Unlike in Product Service where we created Product Service Interface and then various kinds of
    // services (as class), here we will create Payment Service as class and inside the services package we
    // will create another folder representing multiple payment gateways.

    // See in Product Service we had its interface and then various classes because the service might change
    // based on the usage, similarly Payment Service will not change rather payment gateways might change
    // in the future hence we will have Payment Gateway interface.

    private PaymentGateway paymentGateway;

    public PaymentService(@Qualifier ("stripe") PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String generatePaymentLink(String orderId, Long amount) throws StripeException {

        return paymentGateway.createPaymentLink(orderId, amount);
    }

}
