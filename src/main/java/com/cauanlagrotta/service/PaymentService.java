package com.cauanlagrotta.service;

import com.cauanlagrotta.domain.PaymentMethod;
import com.cauanlagrotta.dto.BookingDTO;
import com.cauanlagrotta.dto.UserDTO;
import com.cauanlagrotta.model.PaymentOrder;
import com.cauanlagrotta.payload.response.PaymentLinkResponse;
import com.razorpay.PaymentLink;

public interface PaymentService {

  PaymentLinkResponse createOrder(UserDTO user, BookingDTO booking, PaymentMethod paymentMethod);

  PaymentOrder getPaymentOrderById(Long id);

  PaymentOrder getPaymentOrderByPaymentId(String paymentId);

  PaymentLink createRazorpayPaymentLink(UserDTO user, Long amount, Long orderId);

  String createStripePaymentLink(UserDTO user, Long amount, Long orderId);
}
