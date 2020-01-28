package fz.idle.chat.serverImpl;

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

    @GetMapping("login")
    public String login() {


        System.out.println(proxyInstance.login(metaData));
        return null;
    }
}
