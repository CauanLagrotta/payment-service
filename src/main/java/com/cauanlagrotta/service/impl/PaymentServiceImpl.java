package com.cauanlagrotta.service.impl;

import com.cauanlagrotta.domain.PaymentMethod;
import com.cauanlagrotta.dto.BookingDTO;
import com.cauanlagrotta.dto.UserDTO;
import com.cauanlagrotta.model.PaymentOrder;
import com.cauanlagrotta.payload.response.PaymentLinkResponse;
import com.cauanlagrotta.repository.PaymentOrderRepository;
import com.cauanlagrotta.service.PaymentService;
import com.razorpay.PaymentLink;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final PaymentOrderRepository paymentOrderRepository;

  @Value("{stripe.api.key}")
  private String stripeSecretKey;

  @Value("{razorpay.api.key}")
  private String razorpayApiKey;

  @Value("{razorpay.api.secret}")
  private String razorpayApiSecret;

  @Override
  public PaymentLinkResponse createOrder(UserDTO user, BookingDTO booking, PaymentMethod paymentMethod) {

    Long amount =(long) booking.getTotalPrice();

    PaymentOrder order = new PaymentOrder();
    order.setAmount(amount);
    order.setPaymentMethod(paymentMethod);
    order.setBookingId(booking.getId());
    order.setSaloonId(booking.getSaloonId());

    PaymentOrder savedOrder = paymentOrderRepository.save(order);

    PaymentLinkResponse paymentLinkResponse = new PaymentLinkResponse();

    if(paymentMethod == PaymentMethod.RAZORPAY){
      PaymentLink payment = createRazorpayPaymentLink(user, savedOrder.getAmount(), savedOrder.getId());

      String paymentUrl = payment.get("short_url");
      String paymentUrlId = payment.get("id");

      paymentLinkResponse.setPayment_link_url(paymentUrl);
      paymentLinkResponse.setPayment_link_id(paymentUrlId);

      savedOrder.setPaymentLinkId(paymentUrlId);

      paymentOrderRepository.save(savedOrder);

    }else {
      String paymentUrl = createStripePaymentLink(user, savedOrder.getAmount(), savedOrder.getId());

      paymentLinkResponse.setPayment_link_url(paymentUrl);

    }

    return paymentLinkResponse;
  }

  @Override
  public PaymentOrder getPaymentOrderById(Long id) {
    return null;
  }

  @Override
  public PaymentOrder getPaymentOrderByPaymentId(String paymentId) {
    return null;
  }

  @Override
  public PaymentLink createRazorpayPaymentLink(UserDTO user, Long amount, Long orderId) {
    return null;
  }

  @Override
  public String createStripePaymentLink(UserDTO user, Long amount, Long orderId) {
    return "";
  }
}
