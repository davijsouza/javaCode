package com.souche.salt_common_01.mapper;

import com.souche.salt_common_01.entity.Config;
import com.souche.salt_common_01.entity.ConfigExample;
import com.souche.salt_common_01.entity.ConfigWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigMapper {
    int countByExample(ConfigExample example);

    int deleteByExample(ConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConfigWithBLOBs record);

    int insertSelective(ConfigWithBLOBs record);

    List<ConfigWithBLOBs> selectByExampleWithBLOBs(ConfigExample example);

    List<Config> selectByExample(ConfigExample example);

    ConfigWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConfigWithBLOBs record, @Param("example") ConfigExample example);

    int updateByExampleWithBLOBs(@Param("record") ConfigWithBLOBs record, @Param("example") ConfigExample example);

    int updateByExample(@Param("record") Config record, @Param("example") ConfigExample example);

    int updateByPrimaryKeySelective(ConfigWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ConfigWithBLOBs record);

    int updateByPrimaryKey(Config record);
    List<ConfigWithBLOBs> queryLike(String content);
}
