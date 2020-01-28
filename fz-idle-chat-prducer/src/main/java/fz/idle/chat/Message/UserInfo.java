package fz.idle.chat.Message;

import fz.idle.chat.Do.UserInfoDo;

public interface UserInfo {

    UserInfoDo findUserInfo(String clientId);
}
