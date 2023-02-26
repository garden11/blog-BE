package com.name.blog.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/* 참조: https://jaimemin.tistory.com/2088 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EmailSender {
    private final JavaMailSender javaMailSender;

    public void send(String to, String subject, String message) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            // 첨부 파일(Multipartfile) 보내는 경우 true
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);

            // html 템플릿으로 보내는 경우 true, plaintext로 보내는 경우 false
            mimeMessageHelper.setText(message, false);

            javaMailSender.send(mimeMessage);

            log.info("sent email:", message);
        } catch (MessagingException error) {
            error.printStackTrace();

            throw error;
        }
    }
}
