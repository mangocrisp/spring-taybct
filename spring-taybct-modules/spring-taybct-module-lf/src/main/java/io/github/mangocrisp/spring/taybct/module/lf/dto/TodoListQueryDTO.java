package io.github.mangocrisp.spring.taybct.module.lf.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 流程查询 DTO
 *
 * @author XiJieYin <br> 2023/7/14 16:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "流程查询 DTO")
public class TodoListQueryDTO implements Serializable {

    private static final long serialVersionUID = 6231624597804786448L;

    /**
     * 流程图 id（可以知道当前流程是基于什么原始设计运行的）
     */
    @Schema(description = "流程图 id（可以知道当前流程是基于什么原始设计运行的）")
    private Long designId;
    /**
     * 流程标题
     */
    @Size(max = 100, message = "编码长度不能超过100")
    @Schema(description = "流程标题")
    @Length(max = 100, message = "编码长度不能超过100")
    private String title;
    // TODO 这里要考虑给不给查询，
//    /**
//     * 待办用户 id
//     */
//    @Schema(description = "待办用户 id")
//    private Long userId;
//    /**
//     * 待办用户部门
//     */
//    @Schema(description = "待办用户部门")
//    private Long deptId;
//    /**
//     * 待办用户角色 id
//     */
//    @Schema(description = "待办用户角色 id")
//    private Long roleId;
    /**
     * 流程发布 id（可以知道当前流程是基于什么版本的设计在运行的）
     */
    @Schema(description = "流程发布 id（可以知道当前流程是基于什么版本的设计在运行的）")
    private Long releaseId;
    /**
     * 运行流程 id
     */
    @Schema(description = "运行流程 id")
    private Long processId;
    /**
     * 状态（1、待办、0、已办）
     */
    @Schema(description = "状态（1、待办、0、已办）")
    private Byte status;
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
     * 流程类型（字典项 lf_process_type）
     */
    @Size(max = 100, message = "编码长度不能超过100")
    @Schema(description = "流程类型（字典项 lf_process_type）")
    @Length(max = 100, message = "编码长度不能超过100")
    private String type;
}
