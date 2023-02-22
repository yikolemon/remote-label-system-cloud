package cn.zko0.remotelabel.service.impl;

import cn.zko0.remotelabel.mail.MailRequest;
import cn.zko0.remotelabel.service.SendMailService;
import cn.zko0.remotelabel.util.AuthUtil;
import cn.zko0.remotelabel.util.CodeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author duanfuqiang
 * @date 2023/2/22 21:35
 * @description
 */
@RunWith(SpringRunner.class)//必须
@SpringBootTest
public class SendMailServiceImplTest {

    @Autowired
    private SendMailService sendMailService;

    @Test
    public void sendSimpleMail() {
        MailRequest mailRequest = new MailRequest();
        mailRequest.setSendTo("1142496307@qq.com");
        mailRequest.setSubject("验证码");
        mailRequest.setText(CodeUtil.getCode4());
        sendMailService.sendSimpleMail(mailRequest);
    }
}