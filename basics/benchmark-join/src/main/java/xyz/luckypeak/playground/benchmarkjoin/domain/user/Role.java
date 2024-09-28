package xyz.luckypeak.playground.benchmarkjoin.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
	/**
	 * 主键id
	 */
	private Long id;

	/**
	 * 角色名称
	 */
	private String name;

	/**
	 * 角色描述
	 */
	private String remark;
}
