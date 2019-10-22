package com.souche.salt_common_01.mapper;

import com.souche.salt_common_01.entity.Current;
import com.souche.salt_common_01.entity.CurrentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CurrentMapper {
    int countByExample(CurrentExample example);

    int deleteByExample(CurrentExample example);

    int deleteByPrimaryKey(String id);

    int insert(Current record);

    int insertSelective(Current record);

    List<Current> selectByExampleWithBLOBs(CurrentExample example);

    List<Current> selectByExample(CurrentExample example);

    Current selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Current record, @Param("example") CurrentExample example);

    int updateByExampleWithBLOBs(@Param("record") Current record, @Param("example") CurrentExample example);

    int updateByExample(@Param("record") Current record, @Param("example") CurrentExample example);

    int updateByPrimaryKeySelective(Current record);

    int updateByPrimaryKeyWithBLOBs(Current record);

    int updateByPrimaryKey(Current record);
}