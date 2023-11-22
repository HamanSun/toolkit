package org.relaxation.dynamicdatasource.biz.impl;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import org.relaxation.dynamicdatasource.biz.ScctpGroupBiz;
import org.relaxation.dynamicdatasource.service.ScctpGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScctpGroupBizImpl implements ScctpGroupBiz {
    @Autowired
    private ScctpGroupService groupService;

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void saveGroup() {
        groupService.pSave();
        groupService.sSave();
    }

    @Override
    @DSTransactional(rollbackFor = Exception.class)
    public void saveGroupWithException() {
        groupService.pSave();
        groupService.sSave();
        int a = 1/0;
    }
}
