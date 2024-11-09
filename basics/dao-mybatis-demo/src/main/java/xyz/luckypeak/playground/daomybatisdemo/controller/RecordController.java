package xyz.luckypeak.playground.daomybatisdemo.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.luckypeak.playground.daomybatisdemo.dao.RecordMapper;
import xyz.luckypeak.playground.daomybatisdemo.entity.RecordDO;

@Slf4j
@RestController
@RequestMapping("/records")
public class RecordController {

	@Resource
	private RecordMapper recordMapper;

	@PostMapping("")
	public RecordDO create(
			@RequestBody RecordDO recordDO
	) {
		log.debug("req record={}", recordDO);
		recordMapper.insertOne(recordDO);
		RecordDO recordDODb = recordMapper.selectOneById(recordDO.getId());
		return recordDODb;
	}

}
