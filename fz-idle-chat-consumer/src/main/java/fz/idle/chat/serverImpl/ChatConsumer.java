package fz.idle.chat.serverImpl;

import fz.idle.chat.entry.MessageDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 */

@Slf4j
@RestController
public class ChatConsumer {

    @Autowired
    private ProxyInstance proxyInstance;

    @GetMapping("send")
    public String send() {
        MessageDetail metaData = new MessageDetail();
        metaData.setClientId("123");
        metaData.setDetail("message");
        metaData.setFriendId("456");
        System.out.println(proxyInstance.login(metaData));
        return null;
    }
}
