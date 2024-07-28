package xyz.luckypeak.playground.benchmarkjoin.domain.user;

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
public class User {

	private Long id;

	private String nickName;

	private String username;

	private LocalDate birthday;

	private List<Role> roles;

}
