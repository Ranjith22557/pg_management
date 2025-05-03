package com.example.pgmanagement.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Transactional(rollbackFor = Exception.class)
    public void sendCustomerEmail(String to, String customerName, Long customerId,
                                  String roomNumber, LocalDate joinDate, BigDecimal totalAmount,
                                  int paidAmount, BigDecimal pendingAmount) throws Exception{

        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject("Welcome to Pg");

            //Process Thymeleaf template
            Context context = new Context();
            context.setVariable("customerName", customerName);
            context.setVariable("customerId", customerId);
            context.setVariable("joinDate", joinDate);
            context.setVariable("roomNumber", roomNumber);
            context.setVariable("totalAmount", totalAmount);
            context.setVariable("paidAmount", paidAmount);
            context.setVariable("pendingAmount", pendingAmount);
            String htmlContent = templateEngine.process("emailTemplate", context);

            helper.setText(htmlContent, true);
            javaMailSender.send(message);
        }catch (Exception e){
            throw new RuntimeException("Transaction failed, rolling back changes",e);
        }

    }

}
