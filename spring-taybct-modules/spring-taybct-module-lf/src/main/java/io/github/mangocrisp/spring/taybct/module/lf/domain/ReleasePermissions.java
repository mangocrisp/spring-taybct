package io.github.mangocrisp.spring.taybct.module.lf.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.mangocrisp.spring.taybct.tool.core.annotation.TableFieldDefault;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 流程发布权限表，用于关联指定流程，可以被哪些角色或者用户看到
 *
 * <br>TableName lf_release_permissions
 */
@TableName(value = "lf_release_permissions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "流程发布权限表，用于关联指定流程，可以被哪些角色或者用户看到")
public class ReleasePermissions implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 8908099351198345480L;

    /**
     * 主键
     */
    @NotNull(message = "[主键]不能为空")
    @Schema(description = "主键")
    @TableFieldDefault(expression = "T(com.baomidou.mybatisplus.core.toolkit.IdWorker).getId()")
    @TableId
    private Long id;
    /**
     * 流程发布 id
     */
    @NotNull(message = "[流程发布 id]不能为空")
    @Schema(description = "流程发布 id")
    private Long releaseId;
    /**
     * 部门id
     */
    @Schema(description = "部门 id")
    private Long deptId;
    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private Long userId;

}
