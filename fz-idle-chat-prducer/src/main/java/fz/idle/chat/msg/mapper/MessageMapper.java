package fz.idle.chat.msg.mapper;

import fz.idle.chat.msg.vo.FriendsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {
    List<FriendsVo> getFriends(String clientId);
}
