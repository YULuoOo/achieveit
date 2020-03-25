package com.jcohy.scis.controller;

import com.jcohy.lang.StringUtils;
import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageJson;
import com.jcohy.scis.model.*;
import com.jcohy.scis.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: staffController
 * Description:
 **/
@Controller
@RequestMapping("/staff")
public class StaffController extends BaseController{

    @Autowired
    private AchProjectService achProjectService;
    @GetMapping("/project/list")
    @ResponseBody
    public PageJson<Ach_project> all(){
        PageRequest pageRequest = getPageRequest();
        List<Ach_project> text_messages = achProjectService.getAchProjectList();

        // List<Project> collect = projects.getContent().stream().filter(x -> x.getEStatus() == 1).collect(Collectors.toList());
        PageJson<Ach_project> page = new PageJson<>();
        page.setCode(0);
        page.setMsg("成功");
        page.setCount(text_messages.size());
        page.setData(text_messages);
        return page;
    }

    @GetMapping("/project/{id}/member")
    @ResponseBody
    public JsonResult getMember(@PathVariable("id") Integer id) {
        List<String> members;
        try
        {
            members= achProjectService.getProjectMemberList(id);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.fail("获取数据失败");
        }
        return JsonResult.ok().set("data",members);
    }

    @GetMapping("/project/{id}/get")
    @ResponseBody
    public JsonResult getProject(@PathVariable("id") Integer id){
        Ach_project ach_project;
        try
        {
            ach_project = achProjectService.getAchProject(id);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.fail("获取数据失败");
        }
        return JsonResult.ok().set("data",ach_project);
    }

    @PostMapping("/project/create")
    @ResponseBody
    public JsonResult createProject(@RequestParam(required = false)  String name,
                                   @RequestParam(required = false)  String desc,
                                   @RequestParam(required = false)  String tech,
                                   @RequestParam(required = false)  String area,
                                   @RequestParam(required = false)  String func,
//                                   @RequestParam(required = false)  int status,
                                   @RequestParam(required = false) String enddate,
                                   @RequestParam(required = false) String startdate
                                   ){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            achProjectService.createProject(name,desc,tech,area,func,0,format.parse(enddate),format.parse(startdate));
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @DeleteMapping("/project/{id}/del")
    @ResponseBody
    public JsonResult del(@PathVariable("id") Integer id){
        try {
            achProjectService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("删除失败");
        }
        return JsonResult.ok();
    }

    @PutMapping("/project/{id}/update")
    @ResponseBody
    public JsonResult updateProject(@RequestParam(required = false)  String name,
                                    @RequestParam(required = false)  String desc,
                                    @RequestParam(required = false)  String tech,
                                    @RequestParam(required = false)  String area,
                                    @RequestParam(required = false)  String func,
//                                   @RequestParam(required = false)  int status,
                                    @RequestParam(required = false) String enddate,
                                    @RequestParam(required = false) String startdate,
                                    @PathVariable("id") Integer id
    ){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            achProjectService.updateProject(name,desc,tech,area,func,format.parse(enddate),format.parse(startdate),id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

}
