package org.eforum.mail;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;

@Component
public class Email {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Value("${spring.mail.username}")
    private String fromAccount;
    @Value("${spring.mail.name}")
    private String fromAccountName;

    public void sendValidEmail(MailEntity entity) throws MessagingException,UnsupportedEncodingException{
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(fromAccount,fromAccountName);
        helper.setTo((String[])entity.getTos().toArray());
        helper.setCc((String[])entity.getCcs().toArray());
        helper.setBcc((String[])entity.getBccs().toArray());
        helper.setSubject(entity.getSubject());
        String text = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine, "validTemplate.vm", "UTF-8", entity.getValues());
        helper.setText(text, true);
        for(String file:entity.getFiles()){
            File f = new File(file);
            helper.addAttachment(f.getName(), f);
        }
        mailSender.send(mimeMessage);
    }
}
