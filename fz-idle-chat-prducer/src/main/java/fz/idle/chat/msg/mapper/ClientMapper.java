package fz.idle.chat.msg.mapper;

import fz.idle.chat.msg.param.ClientAllParam;
import fz.idle.chat.msg.param.LogParam;
import fz.idle.chat.msg.vo.ClientAllVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ClientMapper {
    ClientAllVo login(@Param("param") LogParam param);

    void saveClient(@Param("param") ClientAllParam param);
}
