package io.github.mangocrisp.spring.taybct.module.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.mangocrisp.spring.taybct.api.system.domain.SysNotice;
import io.github.mangocrisp.spring.taybct.api.system.dto.SysNoticeDTO;
import io.github.mangocrisp.spring.taybct.api.system.vo.SysNoticeVO;
import io.github.mangocrisp.spring.taybct.common.constants.ServeConstants;
import io.github.mangocrisp.spring.taybct.module.system.service.ISysNoticeService;
import io.github.mangocrisp.spring.taybct.tool.core.annotation.ApiLog;
import io.github.mangocrisp.spring.taybct.tool.core.annotation.ApiVersion;
import io.github.mangocrisp.spring.taybct.tool.core.annotation.RestControllerRegister;
import io.github.mangocrisp.spring.taybct.tool.core.annotation.WebLog;
import io.github.mangocrisp.spring.taybct.tool.core.bean.controller.BaseController;
import io.github.mangocrisp.spring.taybct.tool.core.constant.OperateType;
import io.github.mangocrisp.spring.taybct.tool.core.result.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

/**
 * 消息通知相关接口
 *
 * @author xijieyin <br> 2022/10/10 15:32
 * @since 1.0.5
 */
@Tag(name = "消息通知相关接口")
@RestControllerRegister(ServeConstants.CONTEXT_PATH_SYSTEM + "{version}/notice")
@ApiVersion
public interface ISysNoticeController extends BaseController<SysNotice, ISysNoticeService> {

    /**
     * 用户通知消息分页
     *
     * @param sqlQueryParams {@literal sql 查询参数}
     * @return {@code R<IPage<? extends T>>} 分页信息
     * @author xijieyin <br> 2022/10/10 16:50
     * @since 1.0.5
     */
    @Operation(summary = "用户通知消息分页")
    @GetMapping("userNoticesPage")
    @WebLog
    default R<IPage<SysNoticeVO>> userNoticesPage(@RequestParam(required = false) Map<String, Object> sqlQueryParams) {
        return R.data(getBaseService().userNoticesPage(sqlQueryParams));
    }

    /**
     * 新增消息通知
     *
     * @param dto 数据传输对象
     * @return {@code R<SysNotice>} 把新增成功的消息通知返回，包含自动生成的 id
     * @author xijieyin <br> 2022/10/11 14:07
     * @see SysNoticeDTO
     * @since 1.0.5
     */
    @Operation(summary = "新增消息通知")
    @PostMapping("relatedNotices")
    @WebLog
    @ApiLog(title = "新增消息通知", description = "新增一条记录，并且在新增成功后返回这个新增的对象，这个对象会带着生成的 id 一起返回", type = OperateType.INSERT)
    default R<SysNotice> addRelatedNotices(@Valid @NotNull @RequestBody SysNoticeDTO dto) {
        return getBaseService().addRelatedNotices(dto.getSysNotice(), dto.getNoticeUsers()) ? R.data(dto.getSysNotice()) : R.fail(String.format("新增%s失败！", getResource()));
    }

    /**
     * 批量修改用户消息通知状态
     *
     * @param status    状态
     * @param noticeIds 消息 id
     * @return R 操作返回结果
     * @author xijieyin <br> 2022/10/11 13:43
     * @since 1.0.5
     */
    @Operation(summary = "批量修改用户消息通知状态")
    @PatchMapping("userNotices/{status}")
    @WebLog
    @ApiLog(title = "批量修改用户消息通知状态", description = "批量修改用户消息通知状态", type = OperateType.UPDATE, isSaveRequestData = false, isSaveResultData = false)
    @Parameters({
            @Parameter(name = "status", description = "[状态(0不可见 1 已读 2待办)]不能为空", required = true, in = ParameterIn.PATH),
            @Parameter(name = "noticeIds", description = "通知消息id集合", required = true, in = ParameterIn.DEFAULT)
    })
    default R<?> updateUserNotices(@Valid @NotNull @PathVariable Integer status, @Valid @NotNull @RequestBody Collection<Long> noticeIds) {
        return getBaseService().updateUserNotices(status, noticeIds) ? R.ok(String.format("操作%s成功！", getResource())) : R.fail(String.format("操作%s失败！", getResource()));
    }

}
