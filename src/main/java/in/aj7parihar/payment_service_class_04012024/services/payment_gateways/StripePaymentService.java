package in.aj7parihar.payment_service_class_04012024.services.payment_gateways;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("stripe")
public class StripePaymentService implements PaymentGateway {

    // Fetching the API secret key from application.properties
    @Value("${stripe.api.secret.key}")
    private String stripeAPISecretKey;

    public String createPaymentLink(String orderId, Long amount) throws StripeException {
        Stripe.apiKey = stripeAPISecretKey;

        PriceCreateParams priceCreateParams =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(amount)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Order Id: " + orderId).build()
                        )
                        .build();

        Price price = Price.create(priceCreateParams);

        String callbackUrl = "https://aj7parihar.github.io/order-confirmation.github.io/";
        PaymentLinkCreateParams paymentLinkCreateParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId()) //why "price.getId()" here
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl(callbackUrl)
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(paymentLinkCreateParams);
        System.out.println(paymentLink.getUrl());
        return paymentLink.getUrl();
    }
}
