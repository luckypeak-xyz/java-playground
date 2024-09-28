package xyz.luckypeak.playground.benchmarkjoin.api.user.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListUsersResp {

	@JsonProperty("users")
	private List<UserItem> users;

	@JsonProperty("total")
	private Integer total;

	@JsonProperty("page_num")
	private Integer pageNum;

	@JsonProperty("page_size")
	private Integer pageSize;

}
