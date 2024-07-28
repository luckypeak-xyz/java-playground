package xyz.luckypeak.playground.benchmarkjoin.api.user.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleItem {
	/**
	 * 主键id
	 */
	@JsonProperty("id")
	private Long id;

	/**
	 * 角色名称
	 */
	@JsonProperty("name")
	private String name;

	/**
	 * 角色描述
	 */
	@JsonProperty("remark")
	private String remark;
}
