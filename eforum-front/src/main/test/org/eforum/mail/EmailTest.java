package org.eforum.mail;

import org.eforum.FrontApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FrontApplication.class)
public class EmailTest {

    @Autowired
    private Email email;
    @Test
    public void sendValidEmail() {
        MailEntity entity = new MailEntity("1679600752@qq.com","验证码");
        entity.addFile("C:\\Users\\14183\\Desktop\\无标题y0.skp");
        entity.addValue("username","javalitterboy");
        entity.addValue("validcode","117254");
        try {
            email.sendValidEmail(entity);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}