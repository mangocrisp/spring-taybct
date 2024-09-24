package io.github.mangocrisp.spring.taybct.module.lf.domain;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.mangocrisp.spring.taybct.tool.core.annotation.TableFieldDefault;
import io.github.mangocrisp.spring.taybct.tool.core.annotation.TableFieldJSON;
import io.github.mangocrisp.spring.taybct.tool.core.bean.DeleteLogicEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

/**
 * 流程发布表
 * <p>
 * lf_release
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "lf_release")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "流程发布表")
public class Release extends DeleteLogicEntity<Long, Long> {

    @TableField(exist = false)
    private static final long serialVersionUID = -9220849992133901103L;

    /**
     * 流程图 id
     */
    @NotNull(message = "[流程图 id]不能为空")
    @Schema(description = "流程图 id")
    private Long designId;
    /**
     * 发布名称
     */
    @NotBlank(message = "[发布名称]不能为空")
    @Size(max = 64, message = "编码长度不能超过64")
    @Schema(description = "发布名称")
    @Length(max = 64, message = "编码长度不能超过64")
    @TableField(condition = SqlCondition.LIKE)
    private String name;
    /**
     * 状态(0 关闭 1 打开)
     */
    @Schema(description = "状态(0 关闭 1 打开)")
    @TableFieldDefault("1")
    private Byte status;
    /**
     * 备注说明
     */
    @Size(max = 255, message = "编码长度不能超过255")
    @Schema(description = "备注说明")
    @Length(max = 255, message = "编码长度不能超过255")
    private String description;
    /**
     * 数据（每个版本的数据）
     */
    @NotNull(message = "[数据（每个版本的数据）]不能为空")
    @Schema(description = "数据（每个版本的数据）")
    @TableFieldJSON
    private Object data;
    /**
     * 版本号
     */
    @Schema(description = "版本号")
    private Long version;
    /**
     * 流程类型（字典项 lf_process_type）
     */
    @Size(max = 100, message = "编码长度不能超过100")
    @NotBlank(message = "[流程类型（字典项 lf_process_type）]不能为空")
    @Schema(description = "流程类型（字典项 lf_process_type）")
    @Length(max = 100, message = "编码长度不能超过100")
    private String type;

}
