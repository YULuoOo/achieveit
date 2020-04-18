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
    @Autowired
    private DeviceService deviceService;
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

    //返回项目成员列表，包括id、工号、姓名、当前项目角色
    @GetMapping("/project/{id}/projectStaffs")
    @ResponseBody
    public PageJson getProjectStaffs(@PathVariable("id") Integer id) {
        PageRequest pageRequest = getPageRequest();
        List<Map<String,Object>> text_messages = achProjectService.getProjectStaffs(id);

        // List<Project> collect = projects.getContent().stream().filter(x -> x.getEStatus() == 1).collect(Collectors.toList());
        PageJson<Map<String,Object>> page = new PageJson<>();
        page.setCode(0);
        page.setMsg("成功");
        page.setCount(text_messages.size());
        page.setData(text_messages);
        return page;
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
    public JsonResult delWorkingHour(@PathVariable("id") Integer id){
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
                case "项目经理":
                    status = 4;
                    break;
                default:
                    status = 9;
                    break;
            }
            String[] returnFailString = new String[]{"项目通过失败","配置库建立失败","EPG分配失败","QA分配失败","没有权限"};
            Ach_project project = achProjectService.getAchProject(id);
            if(status == 9)//没有权限
                return JsonResult.fail(returnFailString[4]);
            if(user.getTitle().equals("项目经理")){
                if(project.getPro_status() == 4){
                    achProjectService.updateStatus(5,id);
                    return JsonResult.ok("交付成功");
                }
                if(project.getPro_status() == 5){
                    achProjectService.updateStatus(6,id);
                    return JsonResult.ok("项目完成");
                }
                return JsonResult.fail(returnFailString[4]);
            }
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

    //调整项目成员角色
    @PostMapping("/project/{id}/modifyMemberRole")
    @ResponseBody
    public JsonResult updateMembersRole(@PathVariable("id") Integer pro_id, HttpServletRequest request
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
                achProjectService.updateMembersRole(pro_id,staff_id_int,staff_roles[i]);

                //发邮件
                Staff member = staffService.findById(staff_id_int);
                Ach_project project = achProjectService.getAchProject(pro_id);
                if(member.getEmail()!=null){
                    sendMailService.sendmail_addmember(member.getEmail(), member.getName(),project.getPro_name(),staff_roles[i]);
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
                Ach_project project = achProjectService.getAchProject(pro_id);
                if(member.getEmail()!=null){
                    sendMailService.sendmail_addmember(member.getEmail(), member.getName(),project.getPro_name(),staff_roles[i]);
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

    //获取设备列表
    @GetMapping("/device/list")
    @ResponseBody
    public PageJson<Device> getDeviceList(){
        List<Device> text_messages = deviceService.getDeviceList();
        PageJson<Device> page = new PageJson<>();
        page.setCode(0);
        page.setMsg("成功");
        page.setCount(text_messages.size());
        page.setData(text_messages);
        return page;
    }

    //通过ownerId获取设备列表
    @GetMapping("/device/listbyowner")
    @ResponseBody
    public PageJson<Device> getDeviceListByOwnerId(HttpServletRequest request){
        HttpSession session = request.getSession();
        Staff user = (Staff)session.getAttribute("user");
        List<Device> text_messages = deviceService.getDeviceListByOwnerId(user.getId());
        PageJson<Device> page = new PageJson<>();
        page.setCode(0);
        page.setMsg("成功");
        page.setCount(text_messages.size());
        page.setData(text_messages);
        return page;
    }

    //创建设备
    @PostMapping("/device/create")
    @ResponseBody
    public JsonResult createDevice(HttpServletRequest request,
                                        @RequestParam(required = false)  String name,
                                        @RequestParam(required = false)  String category,
                                        @RequestParam(required = false)  String condition
    ){
        HttpSession session = request.getSession();
        Staff user = (Staff)session.getAttribute("user");
        try {
            Staff owner = staffService.findById(user.getId());
            deviceService.createDevice(name,category,owner.getId(),owner.getName(),"可借用",condition,"");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @DeleteMapping("/device/{id}/del")
    @ResponseBody
    public JsonResult deleteDevice(@PathVariable("id") Integer id){
        try {
            deviceService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("删除失败");
        }
        return JsonResult.ok();
    }

    //通过ownerId获取设备列表
    @GetMapping("/device/{id}/borrowlist")
    @ResponseBody
    public PageJson<Map<String,Object>> getDeviceBorrowList(@PathVariable("id") Integer id){

        List<Map<String,Object>> text_messages = deviceService.getDeviceBorrowList(id);
        PageJson<Map<String,Object>> page = new PageJson<>();
        page.setCode(0);
        page.setMsg("成功");
        page.setCount(text_messages.size());
        page.setData(text_messages);
        return page;
    }

    //通过borrowerId获取设备列表
    @GetMapping("/device/listbyborrower")
    @ResponseBody
    public PageJson<Map<String,Object>> getDeviceListByBorrowerId(HttpServletRequest request){
        HttpSession session = request.getSession();
        Staff user = (Staff)session.getAttribute("user");
        List<Map<String,Object>> text_messages = deviceService.getDeviceListByBorrowerId(user.getId());
        PageJson<Map<String,Object>> page = new PageJson<>();
        page.setCode(0);
        page.setMsg("成功");
        page.setCount(text_messages.size());
        page.setData(text_messages);
        return page;
    }

    //通过
    @GetMapping("/device/listborrowable")
    @ResponseBody
    public PageJson<Device> getBorrowableDeviceList(){
        List<Device> text_messages = deviceService.getBorrowableDeviceList();
        PageJson<Device> page = new PageJson<>();
        page.setCode(0);
        page.setMsg("成功");
        page.setCount(text_messages.size());
        page.setData(text_messages);
        return page;
    }

    //申请借用设备
    @PostMapping("/device/{id}/borrow")
    @ResponseBody
    public JsonResult createBorrowDeviceRequest(HttpServletRequest request,
                                                @PathVariable("id") Integer id
    ){
        HttpSession session = request.getSession();
        Staff user = (Staff)session.getAttribute("user");
        String state = "借用审核中";
        Date borrowDate = new Date();
        Date returnDate = null;
        String condition = "";
        String detail = "";
        try {
            deviceService.createBorrowDeviceRequest(id, user.getId(),state,borrowDate,returnDate,condition,detail);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    //通过申请
    @PutMapping("/device/{id}/borrowaccept")
    @ResponseBody
    public JsonResult acceptBorrowDeviceRequest(@PathVariable("id") Integer id,
                                                @RequestParam(required = false)  Integer device_id,
                                                @RequestParam(required = false)  String state,
                                                @RequestParam(required = false)  String condition,
                                                @RequestParam(required = false)  String detail
    ){
        String nextState = "借用中";
        Date returnDate = null;
        if(state.equals("归还审核中")){
            nextState = "已归还";
            returnDate = new Date();
        }
        try {
            deviceService.updateDeviceBorrowRecord(id,device_id,nextState,returnDate,condition,detail);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    //申请归还设备
    @PutMapping("/device/{id}/return")
    @ResponseBody
    public JsonResult createReturnDeviceRequest(@PathVariable("id") Integer id,
                                                @RequestParam(required = false)  Integer device_id
    ){
        String state = "归还审核中";
        try {
            deviceService.updateDeviceBorrowRecord(id,device_id,state);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }
}
