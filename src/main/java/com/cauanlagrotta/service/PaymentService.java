package com.cauanlagrotta.service;

import com.cauanlagrotta.domain.PaymentMethod;
import com.cauanlagrotta.dto.BookingDTO;
import com.cauanlagrotta.dto.UserDTO;
import com.cauanlagrotta.model.PaymentOrder;
import com.cauanlagrotta.payload.response.PaymentLinkResponse;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {

  PaymentLinkResponse createOrder(UserDTO user, BookingDTO booking, PaymentMethod paymentMethod) throws RazorpayException, StripeException;

  PaymentOrder getPaymentOrderById(Long paymentOrderId);

  PaymentOrder getPaymentOrderByPaymentId(String paymentId);

  PaymentLink createRazorpayPaymentLink(UserDTO user, Long amount, Long orderId) throws RazorpayException;

  String createStripePaymentLink(UserDTO user, Long amount, Long orderId) throws StripeException;
}
