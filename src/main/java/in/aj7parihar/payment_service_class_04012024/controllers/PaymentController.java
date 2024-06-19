package in.aj7parihar.payment_service_class_04012024.controllers;

import com.stripe.exception.StripeException;
import in.aj7parihar.payment_service_class_04012024.dtos.InitiatePaymentDto;
import in.aj7parihar.payment_service_class_04012024.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    // Constructor DI, also learn Setter DI & Autowire

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payments")
    public ResponseEntity<String> getPaymentLink(
            @RequestBody InitiatePaymentDto requestDto) throws StripeException {
        String paymentLink = paymentService.generatePaymentLink(requestDto.getOrderId(), requestDto.getAmount());
        return ResponseEntity.ok(paymentLink);
    }


}
