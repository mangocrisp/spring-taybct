<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperInterface.packageName}.${baseInfo.fileName}">

    <resultMap id="BaseResultMap" type="${tableClass.fullClassName}">
        <#list tableClass.pkFields as field>
            <id property="${field.fieldName}" column="${tableClass.tableName}_${field.columnName}"
                jdbcType="${field.jdbcType}"/>
        </#list>
        <#list tableClass.baseFields as field>
            <result property="${field.fieldName}" column="${tableClass.tableName}_${field.columnName}"
                    jdbcType="${field.jdbcType}"/>
        </#list>
    </resultMap>

    <!--分页返回结果-->
    <resultMap id="BasePageResultMap" type="${tableClass.fullClassName}" extends="BaseResultMap">
        <!--这里返回的是可扩展的返回结果 vo 之类的-->
        <!--<collection property="实体类里面的属性" javaType="返回的集合是什么集合的类型如：java.util.List" resultMap="指定映射集合类元素的类型的 resultMap"/>-->
        <!--<association property="实体类里面的属性" column="关联的另一张表的主键" javaType="另一张表对应的实体类" resultMap="指定映射属性对应对象的 resultMap"/>-->
    </resultMap>

    <!--表名-->
    <sql id="Base_Table_Name">${tableClass.tableName}</sql>

    <!--列名-->
    <sql id="Base_Column_List">
        <#list tableClass.allFields as field>${tableClass.tableName}.${field.columnName} as ${tableClass.tableName}_${field.columnName}<#sep>,<#if field_index%3==2>${"\n        "}</#if></#list>
    </sql>

    <update id="updateBatchByCondition">
        update
        <include refid="Base_Table_Name"/>
        <set>
            <#list tableClass.baseBlobFields as field>
                <if test="bean.${field.fieldName} != null">
                    ,${field.columnName} = ${'#'}{bean.${field.fieldName},jdbcType=${field.jdbcType}}
                </if>
            </#list>
        </set>
        <where>
            <#list tableClass.pkFields as field>
                <!--${field.remark!}-->
                <if test="params.${field.fieldName} != null<#if field.jdbcType=="VARCHAR"> and params.${field.fieldName} != ''</#if>">
                    and ${tableClass.tableName}.${field.columnName} = ${r'#'}{params.${field.fieldName}}
                </if>
            </#list>
            <!--当前表的查询条件-->
            <if test="params.${tableClass.shortClassName?uncap_first}QueryDTO != null">
                <include refid="DTO_Condition"/>
            </if>
            <!--TODO 继续添加查询条件-->
        </where>
    </update>

    <!--查询总数-->
    <select id="total" resultType="java.lang.Long">
        select count(1) from (
        <include refid="Base_Query"/>
        ) temp
    </select>

    <!--查询分页-->
    <select id="page" resultMap="BasePageResultMap">
        select t.*
        from (
        <include refid="Base_Page_Query"/>
        ) t
        <if test="page != null and page.sort != null">
            order by
            <foreach collection="page.sort" item="item" separator=","> t.${tableClass.tableName}_${r'$'}{item}</foreach>
        </if>
    </select>

    <!--查询详情-->
    <select id="detail" resultMap="BasePageResultMap">
        select t.*
        from (
        <include refid="Base_Query"/>
        ) t
    </select>

    <!--基础查询-->
    <sql id="Base_Query">
        select
        <include refid="Base_Column_List"/>
        from
        <include refid="Base_Table_Name"/>
        <include refid="Base_Condition"/>
    </sql>

    <!--对象参数查询条件-->
    <sql id="DTO_Condition">
        <#list tableClass.pkFields as field>
            <!--${field.remark!}-->
            <if test="params.${tableClass.shortClassName?uncap_first}QueryDTO.${field.fieldName} != null<#if field.jdbcType=="VARCHAR"> and params.${tableClass.shortClassName?uncap_first}QueryDTO.${field.fieldName} != ''</#if>">
                and ${tableClass.tableName}.${field.columnName} = ${r'#'}
                {params.${tableClass.shortClassName?uncap_first}QueryDTO.${field.fieldName}}
            </if>
        </#list>
        <#list tableClass.baseBlobFields as field>
            <!--${field.remark!}-->
            <#if field.jdbcType=="TIMESTAMP">
                <if test="params.${tableClass.shortClassName?uncap_first}QueryDTO.${field.fieldName}_ge != null">
                    and ${tableClass.tableName}.${field.columnName} ${r'&gt;'}= ${r'#'}
                    {params.${tableClass.shortClassName?uncap_first}QueryDTO.${field.fieldName}_ge}
                </if>
                <if test="params.${tableClass.shortClassName?uncap_first}QueryDTO.${field.fieldName}_le != null">
                    and ${tableClass.tableName}.${field.columnName} ${r'&lt;'}= ${r'#'}
                    {params.${tableClass.shortClassName?uncap_first}QueryDTO.${field.fieldName}_le}
                </if>
            <#else>
                <if test="params.${tableClass.shortClassName?uncap_first}QueryDTO.${field.fieldName} != null<#if field.jdbcType=="VARCHAR"> and params.${tableClass.shortClassName?uncap_first}QueryDTO.${field.fieldName} != ''</#if>">
                    and ${tableClass.tableName}.${field.columnName} = ${r'#'}
                    {params.${tableClass.shortClassName?uncap_first}QueryDTO.${field.fieldName}}
                </if>
            </#if>
        </#list>
    </sql>

    <!--基础条件-->
    <sql id="Base_Condition">
        <where>
            <#list tableClass.pkFields as field>
                <!--${field.remark!}-->
                <if test="params.${field.fieldName} != null<#if field.jdbcType=="VARCHAR"> and params.${field.fieldName} != ''</#if>">
                    and ${tableClass.tableName}.${field.columnName} = ${r'#'}{params.${field.fieldName}}
                </if>
            </#list>
            <!--当前表的查询条件-->
            <if test="params.${tableClass.shortClassName?uncap_first}QueryDTO != null">
                <include refid="DTO_Condition"/>
            </if>
            <!--TODO 继续添加查询条件-->
        </where>
    </sql>

    <sql id="Base_Page_Order_By">
        <if test="page != null and page.sort != null">
            order by
            <foreach collection="page.sort" item="item" separator=","> ${tableClass.tableName}.${r'$'}{item}</foreach>
        </if>
    </sql>

    <!--分布查询-->
    <sql id="Base_Page_Query">
        <if test="page != null">
            <choose>
                <when test="page.offset != null and page.pageSize != null">

                    <if test="_databaseId == 'mysql' or _databaseId == 'postgresql'">
                        <include refid="Base_Query"/>
                        <include refid="Base_Page_Order_By"/>
                        limit ${r'#'}{page.pageSize} offset ${r'#'}{page.offset}
                    </if>

                    <if test="_databaseId == 'oracle'">
                        SELECT offset_start.* FROM (
                        SELECT rownum rn,offset_end.* FROM (

                        <include refid="Base_Query"/>
                        <include refid="Base_Page_Order_By"/>

                        ) offset_end WHERE rownum <![CDATA[<=]]> (${r'#'}{page.offset}+${r'#'}{page.pageSize})
                        ) offset_start WHERE offset_start.rn <![CDATA[>]]> ${r'#'}{page.offset}
                    </if>

                </when>
                <otherwise>
                    <include refid="Base_Query"/>
                    <include refid="Base_Page_Order_By"/>
                </otherwise>
            </choose>
        </if>
    </sql>

</mapper>
