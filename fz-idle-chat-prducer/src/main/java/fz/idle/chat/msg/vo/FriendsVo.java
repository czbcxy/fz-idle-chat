package fz.idle.chat.msg.vo;

import lombok.Data;

import java.util.List;

@Data
public class FriendsVo {
    private String clientId;
    private List<ClientVo> friends;
}
