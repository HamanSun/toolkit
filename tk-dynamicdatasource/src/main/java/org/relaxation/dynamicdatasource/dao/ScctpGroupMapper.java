package org.relaxation.dynamicdatasource.dao;

import org.apache.ibatis.annotations.Mapper;
import org.relaxation.dynamicdatasource.entity.ScctpGroup;

/**
 * <p>
 * 分组管理-一级目录下的分组 Mapper 接口
 * </p>
 *
 * @author jjsunw
 * @since 2023-11-22
 */
@Mapper
public interface ScctpGroupMapper  {
    void save(ScctpGroup o);
}
