package xyz.luckypeak.playground.daomybatisdemo.dao;

import xyz.luckypeak.playground.daomybatisdemo.entity.RecordDO;

public interface RecordMapper {

	int insertOne(RecordDO record);

	RecordDO selectOneById(Long id);

}
