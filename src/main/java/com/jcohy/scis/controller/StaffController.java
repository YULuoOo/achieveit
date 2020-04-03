package com.jcohy.scis.controller;

import com.jcohy.lang.StringUtils;
import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageJson;
import com.jcohy.scis.model.*;
import com.jcohy.scis.service.*;
import com.sun.media.jfxmedia.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: staffController
 * Description:
 **/
@Controller
@RequestMapping("/staff")
public class StaffController extends BaseController{

    @Autowired
    private StaffService staffService;
    @Autowired
    private AchProjectService achProjectService;
    @Autowired
    private WorkingHourService workingHourService;
    private SendMailService sendMailService=new SendMailService();

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

    @GetMapping("/project/joinlist")
    @ResponseBody
    public PageJson<Ach_project> projectJoinList(HttpServletRequest request){
        PageRequest pageRequest = getPageRequest();
        HttpSession session = request.getSession();
        Staff user= (Staff) session.getAttribute("user");
        List<Ach_project> text_messages = achProjectService.getUserProjectList(user.getId());
        // List<Project> collect = projects.getContent().stream().filter(x -> x.getEStatus() == 1).collect(Collectors.toList());
        PageJson<Ach_project> page = new PageJson<>();
        page.setCode(0);
        page.setMsg("成功");
        page.setCount(text_messages.size());
        page.setData(text_messages);
        return page;
    }


    @GetMapping("/workinghour/list")
    @ResponseBody
    public PageJson<WorkingHourTable> allWorkingHour(){
        PageRequest pageRequest = getPageRequest();
        List<WorkingHour> text_messages = workingHourService.getWorkingHourList();
        List<WorkingHourTable> workingHourTableList = new ArrayList<>();
        for(WorkingHour workingHour : text_messages){
            Integer staff_id = workingHour.getStaff_id();
            Staff staff = staffService.findById(staff_id);
            workingHourTableList.add(new WorkingHourTable(workingHour,staff));
        }

        PageJson<WorkingHourTable> page = new PageJson<>();
        page.setCode(0);
        page.setMsg("成功");
        page.setCount(workingHourTableList.size());
        page.setData(workingHourTableList);
        return page;
    }

    //对不同的角色返回不同的需要审批内容
    @GetMapping("/project/process")
    @ResponseBody
    public PageJson<Ach_project> getProcess(HttpServletRequest request){
        HttpSession session = request.getSession();
        Staff user = (Staff)session.getAttribute("user");
        int status = -1;
        //每个角色看到的不同 乳配置管理员 看到1-4
        switch (user.getTitle()){
            case "项目经理":
                status = -1;
                break;
            case "项目成员":
                status = -1;
                break;
            case "配置管理员":
                status = 1;
                break;
            case "EPG Leader":
                status = 2;
                break;
            case "QA管理员":
                status = 3;
                break;
            case "项目上级":
                status = -1;
                break;
            default:
                break;
        }
        PageRequest pageRequest = getPageRequest();
        List<Ach_project> text_messages = achProjectService.getAchProjectProcessList(status);

        // List<Project> collect = projects.getContent().stream().filter(x -> x.getEStatus() == 1).collect(Collectors.toList());
        PageJson<Ach_project> page = new PageJson<>();
        page.setCode(0);
        page.setMsg("成功");
        page.setCount(text_messages.size());
        page.setData(text_messages);
        return page;
    }

    //返回参与项目成员列表
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

    //返回该项目以外员工列表
    @GetMapping("/project/{id}/others")
    @ResponseBody
    public PageJson getOtherStaffs(@PathVariable("id") Integer id) {
        PageRequest pageRequest = getPageRequest();
        List<Map<String,Object>> text_messages = achProjectService.getOtherStaffs(id);

        // List<Project> collect = projects.getContent().stream().filter(x -> x.getEStatus() == 1).collect(Collectors.toList());
        PageJson<Map<String,Object>> page = new PageJson<>();
        page.setCode(0);
        page.setMsg("成功");
        page.setCount(text_messages.size());
        page.setData(text_messages);
        return page;
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
                                   @RequestParam(required = false) String startdate,
                                    HttpServletRequest request
                                   ){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        HttpSession session = request.getSession();
        try {
            achProjectService.createProject(name,desc,tech,area,func,0,format.parse(enddate),format.parse(startdate));
            Ach_project project=achProjectService.getAchProjectByName(name);
            Staff user= (Staff) session.getAttribute("user");
            achProjectService.updateMembers(project.getId(),user.getId() ,"项目经理");
            try {
                sendMailService.sendmail(user.getEmail(), user.getName());
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @PostMapping("/workinghour/create")
    @ResponseBody
    public JsonResult createWorkingHour(HttpServletRequest request,
                                        @RequestParam(required = false)  Integer staff_id,
                                        @RequestParam(required = false)  String work_content,
                                        @RequestParam(required = false)  String work_date,
                                        @RequestParam(required = false)  float work_length
    ){
        HttpSession session = request.getSession();
        Staff user = (Staff)session.getAttribute("user");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if(work_length > 20 || work_length < 0){
            return JsonResult.fail("请输入正确的工作时长");
        }
        try {
            workingHourService.createWorkingHour(user.getId(),work_content,format.parse(work_date),work_length);
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

    @DeleteMapping("/workinghour/{id}/del")
    @ResponseBody
    public JsonResult delete(@PathVariable("id") Integer id){
        try {
            workingHourService.delete(id);
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

    //项目上级通过审批
    @PutMapping("/project/{id}/accept")
    @ResponseBody
    public JsonResult acceptProject(@PathVariable("id") Integer id, HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            Staff user = (Staff)session.getAttribute("user");
            if(user == null)
                return JsonResult.fail("user is null");
            int status = -1;
            //每个角色看到的不同 乳配置管理员 看到1-4
            switch (user.getTitle()){
                case "配置管理员":
                    status = 1;
                    break;
                case "EPG Leader":
                    status = 2;
                    break;
                case "QA管理员":
                    status = 3;
                    break;
                case "项目上级":
                    status = 0;
                    break;
                default:
                    status = 4;
                    break;
            }
            String[] returnFailString = new String[]{"项目通过失败","配置库建立失败","EPG分配失败","QA分配失败","没有权限"};
            Ach_project project = achProjectService.getAchProject(id);
            if(status == 4)//没有权限
                return JsonResult.fail(returnFailString[status]);

            //类似状态机的转移
            if(project.getPro_status()==status){
                achProjectService.updateStatus(status+1,id);
                return JsonResult.ok();
            } else {
                return JsonResult.fail(returnFailString[status]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
    }
    //项目上级通过审批
    @PutMapping("/project/{id}/refuse")
    @ResponseBody
    public JsonResult refuseProject(@PathVariable("id") Integer id, HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            Staff user = (Staff)session.getAttribute("user");
            if(user == null)
                return JsonResult.fail("user is null");
            if (user.getTitle().equals("项目上级")){
                Ach_project project = achProjectService.getAchProject(id);
                if(project.getPro_status()==0){
                    achProjectService.updateStatus(-1,id);
                    return JsonResult.ok();
                } else {
                    return JsonResult.fail("项目拒绝失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    //为项目添加新成员
    @PostMapping("/project/{id}/add")
    @ResponseBody
    public JsonResult updateMembers(@PathVariable("id") Integer pro_id, HttpServletRequest request
    ){
        String[] staff_ids = request.getParameterValues("check");
        String[] staff_roles = request.getParameterValues("role");
        try {
            for(int i=0;i<staff_ids.length;i++){
                System.out.println(staff_ids[i]);
                Integer staff_id_int = null;
                if(staff_ids[i]!=null){
                    staff_id_int = Integer.valueOf(staff_ids[i]);
                }
                achProjectService.updateMembers(pro_id,staff_id_int,staff_roles[i]);

                //发邮件
                Staff member = staffService.findById(staff_id_int);
                if(member.getEmail()!=null){
                    sendMailService.sendmail(member.getEmail(), member.getName());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @GetMapping("/workinghour/{id}/get")
    @ResponseBody
    public JsonResult getWorkingHour(@PathVariable("id") Integer id){
        WorkingHour workingHour;
        try
        {
            workingHour = workingHourService.getWorkingHour(id);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.fail("获取数据失败");
        }
        return JsonResult.ok().set("data",workingHour);
    }


    //工时编辑提交
    @PutMapping("/workinghour/{id}/update")
    @ResponseBody
    public JsonResult updateWorkingHour(HttpServletRequest request,
                                        @RequestParam(required = false)  String work_content,
                                        @RequestParam(required = false)  String work_date,
                                        @RequestParam(required = false)  float work_length,
                                        @PathVariable("id") Integer id
    ){
        HttpSession session = request.getSession();
        Staff user = (Staff)session.getAttribute("user");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if(work_length > 20 || work_length < 0){
            return JsonResult.fail("请输入正确的工作时长");
        }
        try {
            workingHourService.updateWorkingHour(user.getId(), work_content, format.parse(work_date), work_length, id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }
}
