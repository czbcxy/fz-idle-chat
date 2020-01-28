package fz.idle.chat.Message.Impl;

import fz.idle.chat.Do.UserInfoDo;
import fz.idle.chat.Message.UserInfo;
import fz.idle.chat.dao.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInfoImpl implements UserInfo {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public UserInfoDo findUserInfo(String clientId) {
        UserInfoDo message = messageMapper.findMessage(clientId);
        return message;
    }
}
