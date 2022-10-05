package com.elliott.software.service;

import com.elliott.software.repositories.UserRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Subscription;
import com.stripe.param.SubscriptionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Service
public class SubscriptionService {

    private UserRepository userRepository;

    @Autowired
    public SubscriptionService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public Boolean checkIfCustomerIdExists(String customerId){
        Boolean userFound = this.userRepository.existsByCustomerId(customerId);

        return userFound;
    }
    public String createSubscription(String customerId) throws StripeException {

        String priceId = "price_1Lf524IUupCxt3YbbA3L0Meu";
        // Create the subscription
        SubscriptionCreateParams subCreateParams = SubscriptionCreateParams
                .builder()
                .setCustomer(customerId)
                .addItem(
                        SubscriptionCreateParams
                                .Item.builder()
                                .setPrice(priceId)
                                .build()
                )
                .setPaymentBehavior(SubscriptionCreateParams.PaymentBehavior.DEFAULT_INCOMPLETE)
                .addAllExpand(Arrays.asList("latest_invoice.payment_intent"))
                .build();

        Subscription subscription = Subscription.create(subCreateParams);
        return subscription.getLatestInvoiceObject().getPaymentIntentObject().getClientSecret();

    }

}
