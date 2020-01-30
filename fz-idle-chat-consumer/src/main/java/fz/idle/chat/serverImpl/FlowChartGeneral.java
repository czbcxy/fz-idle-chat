package fz.idle.chat.serverImpl;

import fz.idle.chat.ConsumerApp;
import fz.idle.chat.entry.MessageDetail;
import fz.idle.chat.entry.MetaData;
import fz.idle.chat.enums.Types;
import fz.idle.chat.param.LogParam;

import java.util.Objects;
import java.util.Scanner;

public class FlowChartGeneral {

    public static volatile String UserInfo = null;

    public static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户账户：");
        String account = scanner.next();
        System.out.println("请输入用户密码：");
        String passage = scanner.next();
        new Thread(() -> {
            LogParam param = new LogParam();
            param.setAccount(account);
            param.setPassword(passage);
            MetaData metaData = new MetaData();
            metaData.setType(Types.login.name());
            metaData.setDate(param);
            new ProxyInstance().login(metaData);
        }).start();
    }

    public static void send() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (Objects.isNull(UserInfo)) {
                continue;
            }

            System.out.println("请输入要发给谁。。。");
            String to = scanner.next();

            System.out.println("请输入要发送的信息。。。");
            String mssage = scanner.next();

            MessageDetail detail = new MessageDetail();
            detail.setClientId(UserInfo);
            detail.setDetail(mssage);
            detail.setFriendId(to);

            MetaData metadata = new MetaData();
            metadata.setType(Types.chat.name());
            metadata.setDate(detail);
            ConsumerApp.ctx.writeAndFlush(metadata);
        }
    }
}
