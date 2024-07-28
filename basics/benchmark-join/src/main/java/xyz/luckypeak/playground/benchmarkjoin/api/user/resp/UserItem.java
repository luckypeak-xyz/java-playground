package xyz.luckypeak.playground.benchmarkjoin.api.user.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserItem {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("nick_name")
	private String nickName;

	@JsonProperty("username")
	private String username;

	@JsonProperty("birthday")
	private LocalDate birthday;

	@JsonProperty("roles")
	private List<RoleItem> roles;

}
