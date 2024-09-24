package io.github.mangocrisp.spring.taybct.module.system.controller;

import io.github.mangocrisp.spring.taybct.api.system.domain.SysPermissionGroup;
import io.github.mangocrisp.spring.taybct.common.constants.ServeConstants;
import io.github.mangocrisp.spring.taybct.module.system.service.ISysPermissionGroupService;
import io.github.mangocrisp.spring.taybct.tool.core.annotation.ApiVersion;
import io.github.mangocrisp.spring.taybct.tool.core.annotation.RestControllerRegister;
import io.github.mangocrisp.spring.taybct.tool.core.bean.controller.BaseController;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 权限分组接口
 *
 * @author xijieyin <br> 2022/9/23 9:35
 * @see SysPermissionGroup
 * @see ISysPermissionGroupService
 * @since 1.0.4
 */
@Tag(name = "权限分组接口")
@RestControllerRegister(ServeConstants.CONTEXT_PATH_SYSTEM + "{version}/permissionGroup")
@ApiVersion
public interface ISysPermissionGroupController extends BaseController<SysPermissionGroup, ISysPermissionGroupService> {
}
