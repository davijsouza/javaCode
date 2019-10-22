package com.souche.salt_common_01.mapper;

import com.souche.salt_common_01.entity.SaltMche;
import com.souche.salt_common_01.entity.SaltMcheExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaltMcheMapper {
    int countByExample(SaltMcheExample example);

    int deleteByExample(SaltMcheExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SaltMche record);

    int insertSelective(SaltMche record);

    List<SaltMche> selectByExample(SaltMcheExample example);

    SaltMche selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SaltMche record, @Param("example") SaltMcheExample example);

    int updateByExample(@Param("record") SaltMche record, @Param("example") SaltMcheExample example);

    int updateByPrimaryKeySelective(SaltMche record);

    int updateByPrimaryKey(SaltMche record);
}