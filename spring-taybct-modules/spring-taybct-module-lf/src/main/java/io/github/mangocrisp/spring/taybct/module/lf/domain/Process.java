package io.github.mangocrisp.spring.taybct.module.lf.domain;

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
 * 流程管理
 * <p>
 * lf_process
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "lf_process")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "流程管理")
public class Process extends DeleteLogicEntity<Long, Long> {

    @TableField(exist = false)
    private static final long serialVersionUID = -6040784392969489687L;

    /**
     * 流程图 id（可以知道当前流程是基于什么原始设计运行的）
     */
    @NotNull(message = "[流程图 id（可以知道当前流程是基于什么原始设计运行的）]不能为空")
    @Schema(description = "流程图 id（可以知道当前流程是基于什么原始设计运行的）")
    private Long designId;
    /**
     * 流程标题
     */
    @NotBlank(message = "[流程标题]不能为空")
    @Size(max = 100, message = "编码长度不能超过100")
    @Schema(description = "流程标题")
    @Length(max = 100, message = "编码长度不能超过100")
    private String title;
    /**
     * 流程发起人 id
     */
    @NotNull(message = "[流程发起人 id]不能为空")
    @Schema(description = "流程发起人 id")
    private Long userId;
    /**
     * 发起部门
     */
    @Schema(description = "发起部门")
    private Long deptId;
    /**
     * 岗位
     */
    @Schema(description = "岗位")
    private Long postId;
    /**
     * 流程实时数据(方便实时查看流程走向)
     */
    @Schema(description = "流程实时数据(方便实时查看流程走向)")
    @TableFieldJSON
    private Object data;
    /**
     * 流程发布 id（可以知道当前流程是基于什么版本的设计在运行的）
     */
    @NotNull(message = "[流程发布 id（可以知道当前流程是基于什么版本的设计在运行的）]不能为空")
    @Schema(description = "流程发布 id（可以知道当前流程是基于什么版本的设计在运行的）")
    private Long releaseId;
    /**
     * 流程运行过程中的所有表单数据
     */
    @Schema(description = "流程运行过程中的所有表单数据")
    @TableFieldJSON
    private Object formData;

    /**
     * 状态（1、流程进行中 0、流程已经完成 -1、流程中止）
     */
    @Schema(description = "状态（1、流程进行中 0、流程已经完成 -1、流程中止）")
    @TableFieldDefault("1")
    private Byte status;
    /**
     * 备注
     */
    @Size(max = 500, message = "编码长度不能超过100")
    @Schema(description = "备注")
    @Length(max = 500, message = "编码长度不能超过100")
    private String remark;
    /**
     * 流程中止等原因
     */
    @Size(max = 1000, message = "编码长度不能超过100")
    @Schema(description = "流程中止等原因")
    @Length(max = 1000, message = "编码长度不能超过100")
    private String cause;
    /**
     * 流程类型（字典项 lf_process_type）
     */
    @Size(max = 100, message = "编码长度不能超过100")
    @NotBlank(message = "[流程类型（字典项 lf_process_type）]不能为空")
    @Schema(description = "流程类型（字典项 lf_process_type）")
    @Length(max = 100, message = "编码长度不能超过100")
    private String type;

}
