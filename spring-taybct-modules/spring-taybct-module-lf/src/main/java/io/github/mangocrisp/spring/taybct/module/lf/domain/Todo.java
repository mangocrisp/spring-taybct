package io.github.mangocrisp.spring.taybct.module.lf.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.mangocrisp.spring.taybct.tool.core.annotation.TableFieldDefault;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 待办、已办
 *
 * <br>TableName lf_todo
 */
@TableName(value = "lf_todo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "待办、已办")
public class Todo implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 4746489237670609235L;

    /**
     * 主键
     */
    @NotNull(message = "[主键]不能为空")
    @Schema(description = "主键")
    @TableId
    @TableFieldDefault(expression = "T(com.baomidou.mybatisplus.core.toolkit.IdWorker).getId()")
    private Long id;
    /**
     * 节点 id
     */
    @NotBlank(message = "[节点 id]不能为空")
    @Size(max = 36, message = "编码长度不能超过36")
    @Schema(description = "节点 id")
    @Length(max = 36, message = "编码长度不能超过36")
    private String nodeId;
    /**
     * 角色 id
     */
    @Schema(description = "角色 id")
    private Long roleId;
    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private Long userId;
    /**
     * 部门id
     */
    @Schema(description = "部门id")
    private Long deptId;
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @TableFieldDefault(isTimeNow = true)
    private LocalDateTime createTime;
    /**
     * 流程 id
     */
    @NotNull(message = "[流程 id]不能为空")
    @Schema(description = "流程 id")
    private Long processId;
    /**
     * 状态（1、待办、0、已办）
     */
    @NotNull(message = "[状态（1、待办、0、已办）]不能为空")
    @Schema(description = "状态（1、待办、0、已办）")
    private Byte status;
    /**
     * 流程类型（字典项 lf_process_type）
     */
    @Size(max = 100, message = "编码长度不能超过100")
    @Schema(description = "流程类型（字典项 lf_process_type）")
    @Length(max = 100, message = "编码长度不能超过100")
    private String type;
    /**
     * 待办状态（1、待处理 2、待阅 3、被退回  4、未读 5、反馈）
     */
    @Schema(description = "待办状态（1、待处理 2、待阅 3、被退回 4、未读 5、反馈）")
    private Byte todoStatus;
    /**
     * 已办状态（1、未归档 2、已归档 3、待回复 4、未读 5、反馈）
     */
    @Schema(description = "已办状态（1、未归档 2、已归档 3、待回复 4、未读 5、反馈）")
    private Byte doneStatus;
    /**
     * 流程图 id（这里主要是用来查询分类）
     */
    @NotNull(message = "[流程图 id（这里主要是用来查询分类）]不能为空")
    @Schema(description = "流程图 id（这里主要是用来查询分类）")
    private Long designId;
}
