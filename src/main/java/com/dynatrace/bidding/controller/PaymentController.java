package com.dynatrace.bidding.controller;

import com.dynatrace.bidding.model.Bid;
import com.dynatrace.bidding.model.Payment;
import com.dynatrace.bidding.services.BiddingService;
import com.dynatrace.bidding.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private BiddingService biddingService;

    @PostMapping
    public ResponseEntity<Payment> processPayment(@RequestBody Payment payment) {
        //Payment newPayment = paymentService.find;
        System.out.println("Payment Redirection...");

        logger.info("BIZ_METRIC: Redirect to Payment. | type={} | userId={} | biddingNumber={} | bidAmount={} | series={} | paymentId={}",
                payment.getType(),
                payment.getUserId(),
                payment.getBiddingNumber(),
                payment.getAmount(),
                payment.getStatus(), // Example: "FF" from "FF101"
                payment.getId());

        return ResponseEntity.ok(payment);
    }


    @PostMapping("/confirm")
    public ResponseEntity<Payment> confirmPayment(@RequestBody Payment payment) {
        // Step 1: Process the payment. This should handle the transaction logic.
        Payment newPayment = paymentService.processPayment(payment);
        System.out.println(newPayment.toString());

        // Step 2: Check if payment was successful and update the bid status.
        // Assuming your processPayment method returns a successful payment object.
        if (newPayment != null && "COMPLETED".equals(newPayment.getStatus())) {
            // Update the bid status to "COMPLETED" or similar.
            Bid bid = biddingService.updateBidStatus(payment.getBidId(), "ACTIVE");
            // Step 4: Log the successful payment and bid activation.
            if (bid != null) {
                logger.info("BIZ_METRIC: Payment successful & bid activated. | type={} | userId={} | biddingNumber={} | bidAmount={} | bidStatus={} | paymentId={}",
                        payment.getType(),
                        payment.getUserId(),
                        payment.getBiddingNumber(),
                        payment.getAmount(),
                        "ACTIVE", // Log the final status
                        newPayment.getId());

                return ResponseEntity.ok(newPayment);
            }else {
                // Log failed payment and potentially update bid status to FAILED or LOST
                logger.error("BIZ_METRIC: Payment Successful, Bid Status NOT_ACTIVE. | userId={} | biddingNumber={} | bidAmount={}, status={}",
                        payment.getUserId(),
                        payment.getBiddingNumber(),
                        payment.getAmount(),
                        "NOT_ACTIVE");
                // You may want to handle the failed bid status here.
                // For example: bidService.updateBidStatus(payment.getBidId(), "FAILED");

                return ResponseEntity.status(400).body(newPayment);
            }
        } else {
            // Log failed payment and potentially update bid status to FAILED or LOST
            logger.error("BIZ_METRIC: Payment failed. | userId={} | biddingNumber={} | bidAmount={}",
                    payment.getUserId(),
                    payment.getBiddingNumber(),
                    payment.getAmount());

            // You may want to handle the failed bid status here.
            // For example: bidService.updateBidStatus(payment.getBidId(), "FAILED");

            return ResponseEntity.status(400).body(newPayment);
        }
    }
}
