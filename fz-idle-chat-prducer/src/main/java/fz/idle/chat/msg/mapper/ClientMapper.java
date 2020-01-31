package fz.idle.chat.msg.mapper;

import fz.idle.chat.param.AddFriendParam;
import fz.idle.chat.param.ClientAllParam;
import fz.idle.chat.param.LogParam;
import fz.idle.chat.msg.vo.ClientAllVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ClientMapper {
    ClientAllVo login(@Param("param") LogParam param);

    void saveClient(@Param("param") ClientAllParam param);

    void addFriend(@Param("param") AddFriendParam param);

    ClientAllVo findFriend(@Param("param") String account);

    void accept(@Param("param") String friendId);
}
