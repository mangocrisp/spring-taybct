package io.github.mangocrisp.spring.taybct.module.lf.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import io.github.mangocrisp.spring.taybct.module.lf.domain.Release;
import io.github.mangocrisp.spring.taybct.module.lf.dto.ReleasePublishDTO;
import io.github.mangocrisp.spring.taybct.tool.core.annotation.DataScope;
import io.github.mangocrisp.spring.taybct.tool.core.enums.DataScopeGetNotDealType;
import io.github.mangocrisp.spring.taybct.tool.core.enums.DataScopeType;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * @author admin
 * <br>description 针对表【lf_release(流程发布表)】的数据库操作Mapper
 * @see Release
 * @since 2023-07-03 11:31:36
 */
public interface ReleaseMapper extends BaseMapper<Release> {

    /**
     * 发布流程图
     *
     * @param dto 数据
     * @return 影响行数
     */
    int publish(@Param("dto") Collection<ReleasePublishDTO> dto);

    @DataScope(alias = "lf_release"
            , field = "id"
            , type = DataScopeType.MULTI
            , multiTable = "lf_release_permissions"
            , multiFiled = "release_id"
            , multiDeptId = "dept_id"
            , notExistDealType = DataScopeGetNotDealType.ALLOW
    )
    @Override
    <P extends IPage<Release>> P selectPage(P page, @Param(Constants.WRAPPER) Wrapper<Release> queryWrapper);

    @DataScope(alias = "lf_release"
            , field = "id"
            , type = DataScopeType.MULTI
            , multiTable = "lf_release_permissions"
            , multiFiled = "release_id"
            , multiDeptId = "dept_id"
            , notExistDealType = DataScopeGetNotDealType.ALLOW
    )
    Release selectByPrimaryKey(Long id);

}




