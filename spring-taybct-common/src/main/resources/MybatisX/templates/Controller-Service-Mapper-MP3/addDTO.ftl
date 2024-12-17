package ${baseInfo.packageName};

import ${tableClass.fullClassName};
import io.github.mangocrisp.spring.taybct.tool.core.exception.def.BaseException;
import io.github.mangocrisp.spring.taybct.tool.core.util.BeanUtil;
import io.github.mangocrisp.spring.taybct.tool.core.constant.DateConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.mangocrisp.spring.taybct.tool.core.bean.ModelConvertible;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <pre>
 * ${tableClass.remark!} 新增对象
 * TableName: ${tableClass.tableName} 新增对象
 * </pre>
 *
 * @author ${author!}
 * @since ${.now?string('yyyy-MM-dd HH:mm:ss')}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "【${tableClass.remark!}】新增对象")
public class ${tableClass.shortClassName}AddDTO implements Serializable, ModelConvertible<${tableClass.shortClassName}> {

    @Serial
    private static final long serialVersionUID = 1L;

<#list tableClass.baseBlobFields as field>
    /**
    * ${field.remark!}
    */<#if !field.nullable><#if field.jdbcType=="VARCHAR">${"\n    "}@NotBlank(message="[${field.remark!}]不能为空")<#else>${"\n    "}@NotNull(message="[${field.remark!}]不能为空")</#if></#if><#if field.jdbcType=="VARCHAR"&&field.columnLength gt 0><#if !field.nullable>${"\n    "}</#if>@Size(max= ${field.columnLength?c},message="${field.remark!}长度不能超过${field.columnLength?c}")</#if>
    @Schema(description = "${field.remark!}")<#if field.jdbcType=="VARCHAR"&&field.columnLength gt 0>${"\n    "}@Length(max= ${field.columnLength?c},message="${field.remark!}长度不能超过${field.columnLength?c}")</#if><#if field.jdbcType=="DATE">${"\n    "}@DateTimeFormat(pattern = DateConstants.format.YYYY_MM_DD)${"\n    "}@JsonFormat(pattern = DateConstants.format.YYYY_MM_DD)</#if><#if field.jdbcType=="TIMESTAMP">${"\n    "}@DateTimeFormat(pattern = DateConstants.format.YYYY_MM_DD_HH_mm_ss)${"\n    "}@JsonFormat(pattern = DateConstants.format.YYYY_MM_DD_HH_mm_ss)</#if><#if field.jdbcType=="BIGINT">${"\n    "}@JsonSerialize(using = ToStringSerializer.class)</#if>
    private ${field.shortTypeName} ${field.fieldName};
</#list>

    @Hidden
    ${tableClass.shortClassName} convertedBean;

    @Override
    @Hidden
    public void setConvertedBean(${tableClass.shortClassName} convertedBean) {
        throw new BaseException("not support!");
    }

    @Override
    public ${tableClass.shortClassName} bean(String... ignoreProperties) {
        ${tableClass.shortClassName} bean;
        if ((bean = getConvertedBean()) != null) {
            return bean;
        }
        this.convertedBean = (bean = BeanUtil.copyProperties(this, beanClass(), ignoreProperties));
        return bean;
    }
}