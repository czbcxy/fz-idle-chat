package fz.idle.chat.msg.mapper;

import fz.idle.chat.param.FindMsgParam;
import fz.idle.chat.param.MsgParam;
import fz.idle.chat.msg.vo.FriendsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
@Mapper
@Repository
public interface MessageMapper {
    List<FriendsVo> getFriend(@Param("param") String clientId);

    void send(@Param("param") MsgParam param);

    void sendMsg(@Param("param") MsgParam param);

    List<HashMap<String, String>> findMsg(@Param("param") FindMsgParam param);
}
