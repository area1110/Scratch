package com.training.JWEBPraticeT02.controller.customer;


import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.training.JWEBPraticeT02.Service.PaypalService;
import com.training.JWEBPraticeT02.Utils.Utils;
import com.training.JWEBPraticeT02.conf.PaypalPaymentIntent;
import com.training.JWEBPraticeT02.conf.PaypalPaymentMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
@Controller
public class PaymentController {
    public static final String URL_PAYPAL_SUCCESS = "pay/success";
    public static final String URL_PAYPAL_CANCEL = "pay/cancel";
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private PaypalService paypalService;
    @GetMapping("/")
    public String index(){
        return "html/CustomerView/index";
    }
//    @PostMapping("/pay")
//    public String pay(HttpServletRequest request, @RequestParam("price") double price ){
//        String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
//        String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
//        try {
//            Payment payment = paypalService.createPayment(
//                    price,
//                    "USD",
//                    PaypalPaymentMethod.paypal,
//                    PaypalPaymentIntent.sale,
//                    "payment description",
//                    cancelUrl,
//                    successUrl);
//            for(Links links : payment.getLinks()){
//                if(links.getRel().equals("approval_url")){
//                    return "redirect:" + links.getHref();
//                }
//            }
//        } catch (PayPalRESTException e) {
//            log.error(e.getMessage());
//        }
//        return "redirect:/";
//    }
    @GetMapping(URL_PAYPAL_CANCEL)
    public String cancelPay(){
        return "cancel";
    }
    @GetMapping(URL_PAYPAL_SUCCESS)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, RedirectAttributes redirectAttributes){
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                redirectAttributes.addFlashAttribute("message", "Cảm ơn bạn đã đặt hàng ở chúng tôi");
                return "redirect:/home";
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/home";
    }
}
