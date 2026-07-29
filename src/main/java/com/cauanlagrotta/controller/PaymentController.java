package com.cauanlagrotta.controller;

import com.cauanlagrotta.domain.PaymentMethod;
import com.cauanlagrotta.dto.BookingDTO;
import com.cauanlagrotta.dto.UserDTO;
import com.cauanlagrotta.model.PaymentOrder;
import com.cauanlagrotta.payload.response.PaymentLinkResponse;
import com.cauanlagrotta.service.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

  private final PaymentService paymentService;

  @PostMapping("/create")
  public ResponseEntity<PaymentLinkResponse> createPaymentLink(@RequestBody BookingDTO booking, @RequestParam PaymentMethod paymentMethod) throws StripeException, RazorpayException {
    UserDTO user = new UserDTO();
    user.setFullName("John Doe");
    user.setEmail("johndoe@email.com");
    user.setId(1L);

    PaymentLinkResponse res = paymentService.createOrder(user, booking, paymentMethod);
    return ResponseEntity.ok(res);
  }

  @PostMapping("/{paymentOrderId}")
  public ResponseEntity<PaymentOrder> getPaymentOrderById(@PathVariable Long paymentOrderId) {

    PaymentOrder res = paymentService.getPaymentOrderById(paymentOrderId);
    return ResponseEntity.ok(res);
  }

  @PostMapping("/proceed")
  public ResponseEntity<Boolean> proceedPayment(@RequestParam String paymentId, @RequestParam String paymentLinkId) throws RazorpayException {

    PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId(paymentLinkId);

    Boolean res = paymentService.proceedPayment(paymentOrder, paymentId, paymentLinkId);
    return ResponseEntity.ok(res);
  }
}
