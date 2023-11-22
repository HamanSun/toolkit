package org.relaxation.dynamicdatasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.relaxation.dynamicdatasource.dao.ScctpGroupMapper;
import org.relaxation.dynamicdatasource.entity.ScctpGroup;
import org.relaxation.dynamicdatasource.service.ScctpGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 分组管理-一级目录下的分组 服务实现类
 * </p>
 *
 * @author jjsunw
 * @since 2023-11-22
 */
@Service
public class ScctpGroupServiceImpl  implements ScctpGroupService {

    @Autowired
    private ScctpGroupMapper groupMapper;
    @DS("master")
    @Override
    public void pSave() {
        groupMapper.save(build(1L,"master1"));
    }

    @DS("slave")
    @Override
    public void sSave() {
        groupMapper.save(build(1L,"slave1"));
    }

    private ScctpGroup build(Long directoryId, String groupName){
        Date now = new Date();
        ScctpGroup scctpGroup = new ScctpGroup();
        scctpGroup.setDirectoryId(directoryId);
        scctpGroup.setGroupName(groupName);
        scctpGroup.setCreateTime(now);
        scctpGroup.setModifyTime(now);
        scctpGroup.setCreator("jjsunw");
        scctpGroup.setModifier("jjsunw");
        return scctpGroup;
    }
}
