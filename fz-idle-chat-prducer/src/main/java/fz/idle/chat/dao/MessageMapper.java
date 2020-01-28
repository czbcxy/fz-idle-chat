package fz.idle.chat.dao;

import fz.idle.chat.Do.UserInfoDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MessageMapper {
    UserInfoDo findMessage(@Param("clientId") String clientId);
}
