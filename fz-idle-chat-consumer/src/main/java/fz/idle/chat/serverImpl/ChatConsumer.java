package fz.idle.chat.serverImpl;

import com.alibaba.fastjson.JSON;
import fz.idle.chat.entry.MessageDetail;
import fz.idle.chat.entry.MetaData;
import fz.idle.chat.enums.Types;
import fz.idle.chat.param.LogParam;
import fz.idle.chat.param.MsgParam;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.jvm.hotspot.oops.Metadata;

import java.io.PrintStream;
import java.util.UUID;

/**
 * Hello world!
 */

@Slf4j
@RestController
public class ChatConsumer {

    @Autowired
    private ProxyInstance proxyInstance;

    @Autowired
    private FlowChartGeneral flowChartGeneral;

    @GetMapping("login")
    public String login() {
        new Thread(() -> {
            LogParam param = new LogParam();
            param.setAccount("99999");
            param.setPassword("123456");

            MetaData metaData = new MetaData();
            metaData.setType(Types.login.name());
            metaData.setDate(param);
            proxyInstance.login(metaData);
        }).start();
        return "登录成功";
    }

    @GetMapping("send")
    public String send() {
        MessageDetail detail = new MessageDetail();
        detail.setClientId("123");
        detail.setDetail("来子客户端的消息");
        detail.setFriendId("456");

        MetaData metadata = new MetaData();
        metadata.setType(Types.chat.name());
        metadata.setDate(detail);
        flowChartGeneral.doChat(metadata);
        return "发送消息成功";
    }
}
