package xyz.luckypeak.mybatisplusdemo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import xyz.luckypeak.mybatisplusdemo.enums.UserStatusEnum;
import xyz.luckypeak.mybatisplusdemo.po.UserInfo;

import java.util.List;

@Data
@Schema(description = "用户VO实体")
public class UserVO {

    @Schema(description = "用户id")
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "详细信息")
    private UserInfo info;

    @Schema(description = "使用状态（1正常 2冻结）")
    private UserStatusEnum status;

    @Schema(description = "账户余额")
    private Integer balance;

    @Schema(description = "地址列表")
    List<AddressVO> addresses;
}