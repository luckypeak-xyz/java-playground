package xyz.luckypeak.playground.mybatislearning.dao;

import xyz.luckypeak.playground.mybatislearning.entity.RecordDO;

public interface RecordMapper {

	int insertOne(RecordDO record);

	RecordDO selectOneById(Long id);

}
