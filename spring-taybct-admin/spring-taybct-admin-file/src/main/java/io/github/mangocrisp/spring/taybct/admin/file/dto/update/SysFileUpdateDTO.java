package io.github.mangocrisp.spring.taybct.admin.file.dto.update;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.github.mangocrisp.spring.taybct.admin.file.domain.SysFile;
import io.github.mangocrisp.spring.taybct.tool.core.bean.ModelConvertible;
import io.github.mangocrisp.spring.taybct.tool.core.constant.DateConstants;
import io.github.mangocrisp.spring.taybct.tool.core.exception.def.BaseException;
import io.github.mangocrisp.spring.taybct.tool.core.util.BeanUtil;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <pre>
 * 文件管理 修改对象
 * TableName: sys_file 修改对象
 * </pre>
 *
 * @author 24154
 * @see SysFile
 * @since 2024-09-01 21:20:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "【文件管理】修改对象")
public class SysFileUpdateDTO implements Serializable, ModelConvertible<SysFile> {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @NotNull(message = "[主键]不能为空")
    @Schema(description = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 文件名（路径）
     */
    @NotBlank(message = "[文件名（路径）]不能为空")
    @Size(max = 1000, message = "编码长度不能超过1000")
    @Schema(description = "文件名（路径）")
    @Length(max = 1000, message = "编码长度不能超过1000")
    private String path;
    /**
     * 上传时间
     */
    @NotNull(message = "[上传时间]不能为空")
    @Schema(description = "上传时间")
    @DateTimeFormat(pattern = DateConstants.format.YYYY_MM_DD_HH_mm_ss)
    @JsonFormat(pattern = DateConstants.format.YYYY_MM_DD_HH_mm_ss)
    private LocalDateTime uploadTime;
    /**
     * 上传人
     */
    @Schema(description = "上传人")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long uploadUser;
    /**
     * 更新时间
     */
    @NotNull(message = "[更新时间]不能为空")
    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = DateConstants.format.YYYY_MM_DD_HH_mm_ss)
    @JsonFormat(pattern = DateConstants.format.YYYY_MM_DD_HH_mm_ss)
    private LocalDateTime updateTime;
    /**
     * 是否在使用中
     */
    @NotNull(message = "[是否在使用中]不能为空")
    @Schema(description = "是否在使用中")
    private Integer linked;
    /**
     * 关联的表
     */
    @Size(max = 100, message = "编码长度不能超过100")
    @Schema(description = "关联的表")
    @Length(max = 100, message = "编码长度不能超过100")
    private String linkedTable;
    /**
     * 关联的表的 id
     */
    @Schema(description = "关联的表的 id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long linkedTableId;
    /**
     * 是否已删除
     */
    @NotNull(message = "[是否已删除]不能为空")
    @Schema(description = "是否已删除")
    private Integer isDeleted;
    /**
     * 文件管理服务器类型（local,oss,fdfs,minio）
     */
    @NotBlank(message = "[文件管理服务器类型（local,oss,fdfs,minio）]不能为空")
    @Size(max = 50, message = "编码长度不能超过50")
    @Schema(description = "文件管理服务器类型（local,oss,fdfs,minio）")
    @Length(max = 50, message = "编码长度不能超过50")
    private String manageType;
    /**
     * 文件类型
     */
    @Size(max = 255, message = "编码长度不能超过255")
    @Schema(description = "文件类型")
    @Length(max = 255, message = "编码长度不能超过255")
    private String fileType;
    /**
     * 更新人
     */
    @Schema(description = "更新人")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateUser;

    @Hidden
    SysFile convertedBean;

    @Override
    @Hidden
    public void setConvertedBean(SysFile convertedBean) {
        throw new BaseException("not support!");
    }

    @Override
    public SysFile bean(String... ignoreProperties) {
        SysFile bean;
        if ((bean = getConvertedBean()) != null) {
            return bean;
        }
        this.convertedBean = (bean = BeanUtil.copyProperties(this, beanClass(), ignoreProperties));
        return bean;
    }

}
