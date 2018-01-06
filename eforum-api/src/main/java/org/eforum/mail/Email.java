package org.eforum.mail;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
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
        helper.setTo(entity.getTos().toArray(new String[entity.getTos().size()]));
        helper.setCc(entity.getCcs().toArray(new String[entity.getCcs().size()]));
        helper.setBcc(entity.getBccs().toArray(new String[entity.getBccs().size()]));
        helper.setSubject(entity.getSubject());
        System.out.println(velocityEngine.resourceExists("log4j2"));
        String text = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine, "validTemplate.vm", "UTF-8", entity.getValues());
        helper.setText(text, true);
        Resource eforum = new ClassPathResource("public/img/eforum.png");
        helper.addInline("eforum",eforum);
        for(String file:entity.getFiles()){
            File f = new File(file);
            helper.addAttachment(f.getName(), f);
        }
        mailSender.send(mimeMessage);
    }
}
