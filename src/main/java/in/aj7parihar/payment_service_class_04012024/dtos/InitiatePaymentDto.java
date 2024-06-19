package in.aj7parihar.payment_service_class_04012024.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentDto {
    private String orderId;
    private long amount;
}
