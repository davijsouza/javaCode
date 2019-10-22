package com.souche.salt_common_01.mapper;

import com.souche.salt_common_01.entity.RunJob;
import com.souche.salt_common_01.entity.RunJobExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RunJobMapper {
    int countByExample(RunJobExample example);

    int deleteByExample(RunJobExample example);

    int deleteByPrimaryKey(String id);

    int insert(RunJob record);

    int insertSelective(RunJob record);

    List<RunJob> selectByExample(RunJobExample example);

    RunJob selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RunJob record, @Param("example") RunJobExample example);

    int updateByExample(@Param("record") RunJob record, @Param("example") RunJobExample example);

    int updateByPrimaryKeySelective(RunJob record);

    int updateByPrimaryKey(RunJob record);
}