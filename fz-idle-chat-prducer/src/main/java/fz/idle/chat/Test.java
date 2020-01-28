package fz.idle.chat;

import fz.idle.chat.Do.UserInfoDo;
import fz.idle.chat.Message.Impl.UserInfoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @Autowired
    private UserInfoImpl userInfo;

    @GetMapping("get")
    public String gets(){
        UserInfoDo userInfo = this.userInfo.findUserInfo("123");
        return userInfo.toString();
    }
}
