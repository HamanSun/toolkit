package org.relaxation.dynamicdatasource.entity;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 分组管理-一级目录下的分组
 * </p>
 *
 * @author jjsunw
 * @since 2023-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ScctpGroup {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 分组所在一级目录主键
     */
    private Long directoryId;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 分组创建时间
     */
    private Date createTime;

    /**
     * 分组更新时间
     */
    private Date modifyTime;

    /**
     * 分组的创建人,与所在目录的创建人一致
     */
    private String creator;

    /**
     * 更新人(工号)
     */
    private String modifier;

    /**
     * 分组的排序编号(冗余列: 支持自定义排序使用),无值则按创建时间排序
     */
    private Integer sort;

}
