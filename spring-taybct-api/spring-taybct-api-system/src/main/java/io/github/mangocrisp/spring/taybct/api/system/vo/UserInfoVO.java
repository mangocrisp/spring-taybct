package io.github.mangocrisp.spring.taybct.api.system.vo;

import io.github.mangocrisp.spring.taybct.api.system.domain.SysUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 当前登录用户的信息
 *
 * @author xijieyin <br> 2022/8/5 10:19
 * @since 1.0.0
 */
@Schema(description = "当前登录用户的信息")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserInfoVO extends SysUser {

    private static final long serialVersionUID = 1645215662147498564L;
    /**
     * 用户角色编码集合 ["ADMIN","NORMAL","LEADER"...]
     */
    @Schema(description = "拥有的角色列表")
    private List<String> roles;

    /**
     * 要求修改密码的时间间隔(单位：月)
     */
    @Schema(description = "要求修改密码的时间间隔(单位：月)")
    private Long passwdRequire;
}
