package org.relaxation.dynamicdatasource.controller;


import org.relaxation.dynamicdatasource.biz.ScctpGroupBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 分组管理-一级目录下的分组 前端控制器
 * </p>
 *
 * @author jjsunw
 * @since 2023-11-22
 */
@RestController
@RequestMapping("/scctpGroup")
public class ScctpGroupController {

    @Autowired
    private ScctpGroupBiz groupBiz;

    @GetMapping("/saveGroup")
    public String saveGroup(){
        try {
            groupBiz.saveGroup();
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

    @GetMapping("/saveGroupWithException")
    public String saveGroupWithException(){
        try {
            groupBiz.saveGroupWithException();
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }
}

