package org.relaxation.dynamicdatasource.service;

import org.relaxation.dynamicdatasource.entity.ScctpGroup;

/**
 * <p>
 * 分组管理-一级目录下的分组 服务类
 * </p>
 *
 * @author jjsunw
 * @since 2023-11-22
 */
public interface ScctpGroupService {
    /**
     * 第一个数据源
     * @param
     */
    void pSave();

    /**
     * 第二给数据源
     * @param
     */
    void sSave();
}
