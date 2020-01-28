package fz.idle.chat.serverImpl;

import fz.idle.chat.entry.MetaData;
import fz.idle.chat.enums.Types;
import fz.idle.chat.param.LogParam;
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
        LogParam param = new LogParam();
        param.setAccount("99999");
        param.setPassword("123456");

        MetaData metaData = new MetaData();
        metaData.setType(Types.login.name());
        metaData.setDate(param);
        System.out.println(proxyInstance.login(metaData));
        return null;
    }
}
