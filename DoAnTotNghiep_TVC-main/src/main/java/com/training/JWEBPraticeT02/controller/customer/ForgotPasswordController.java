package com.training.JWEBPraticeT02.controller.customer;
import com.training.JWEBPraticeT02.entity.User;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class ForgotPasswordController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private com.training.JWEBPraticeT02.Service.UserService userService;

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
        return "html/CustomerView/forgot_password_form";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) throws MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String token = RandomString.make(30);

        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "Email đã được gửi thành công!. Hãy kiểm tra email của bạn.");

        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }

        return "html/CustomerView/forgot_password_form";
    }

    public void sendEmail(String recipientEmail, String link)throws MessagingException, UnsupportedEncodingException{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("34tranvietcuong@gmail.com", "Wuming Shop");
        helper.setTo(recipientEmail);

        String subject = "Đặt lại mật khẩu";

        String content = "<h2>Chào bạn,</h2>"
                + "<p>Bạn đã yêu cầu đặt lại mật khẩu cho tài khoản Gmail của mình.</p>"
                + " <p>Vui lòng nhấp vào liên kết dưới đây để đặt lại mật khẩu:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + " <p>Nếu bạn không yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này.</p>"
                + " <p>Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi.</p>"
                +" <p>Trân trọng,</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }


    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        User user = userService.getByResetPasswordToken(token);
        model.addAttribute("token", token);

        if (user == null) {
            model.addAttribute("message", "Mã không hợp lệ");
            return "message";
        }

        return "html/CustomerView/reset_password_form";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.getByResetPasswordToken(token);
        model.addAttribute("title", "Đặt lại mật khẩu của bạn");

        if (user == null) {

            redirectAttributes.addFlashAttribute("message", "Mã không hợp lệ");
            return "redirect:/login";
        } else {
            userService.updatePassword(user, password);
            redirectAttributes.addFlashAttribute("message", "Bạn đã đổi mật khẩu thành công.");
        }

        return "redirect:/login";
    }

    public class Utility {
        public static String getSiteURL(HttpServletRequest request) {
            String siteURL = request.getRequestURL().toString();
            return siteURL.replace(request.getServletPath(), "");
        }
    }
}
