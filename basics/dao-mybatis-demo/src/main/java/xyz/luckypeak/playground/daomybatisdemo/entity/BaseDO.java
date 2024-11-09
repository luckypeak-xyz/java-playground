package xyz.luckypeak.playground.daomybatisdemo.entity;

import lombok.Data;

@Data
public abstract class BaseDO {

	protected Long id;

	protected String creator;

	protected String updater;

	protected Long updatedAt;

	protected Long createdAt;

}
