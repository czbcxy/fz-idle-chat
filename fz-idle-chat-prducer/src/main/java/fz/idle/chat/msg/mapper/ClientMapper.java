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

    Integer addFriend(@Param("param") AddFriendParam param);

    ClientAllVo clientByCid(@Param("param") String clientId);

    ClientAllVo findFriend(@Param("param") String account);

    void accept(@Param("param") String friendId);

    void delete(@Param("param") AddFriendParam param);

    void delete1(@Param("param") AddFriendParam param);

    void deleteMsg(@Param("param") AddFriendParam param);

    void deleteMsg1(@Param("param") AddFriendParam param);
}
