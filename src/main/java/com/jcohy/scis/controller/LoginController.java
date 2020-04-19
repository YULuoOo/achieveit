package com.jcohy.scis.controller;

import com.jcohy.lang.StringUtils;
import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.exception.ServiceException;
import com.jcohy.scis.model.*;
import com.jcohy.scis.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ClassName  : LoginController
 * Description  :登录模块处理
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private StaffService staffService;


    /**
     * 登录处理
     * @param num
     * @param password
     * @param role
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public JsonResult login(Long num, String password,
                            @RequestParam(required = false) String role, HttpServletRequest request){
        try {
            if(num == null || StringUtils.isEmpty(password)){
                return JsonResult.fail("用户名或者密码不能为空");
            }
            HttpSession session = request.getSession();
            session.setAttribute("role",role);
            logger.error("name:{}  password:{}  type:{}",num,password,role);
                Staff login = staffService.login(num, password);
                if(login == null){
                    return JsonResult.fail("登录失败,用户名不存在");
                }
                if(!login.getPassword().equals(password)){
                    return JsonResult.fail("登录失败,用户名账号密码不匹配");
                }
                session.setAttribute("user",login);
                return JsonResult.ok().set("returnUrl", "/staff/main").set("userId",login.getId()).set("userTitle",login.getTitle());
        } catch (Exception e) {
            return JsonResult.fail(e.getMessage());
        }
    }

    /**
     * 注册处理
     * @param num
     * @param name
     * @param password
     * @param role
     * @param sex
     * @return
     */
    @PostMapping("/regi")
    @ResponseBody
    public JsonResult register(Long num,
                               String name,
                               String password,
                               @RequestParam(required = false) String role,
                               @RequestParam(required = false) String title,
                               @RequestParam(required = false) String sex,
                               @RequestParam(required = false) String email,
                               HttpServletRequest request){
        try {
            if(num == null || StringUtils.isEmpty(password) || StringUtils.isEmpty(name)){
                return JsonResult.fail("用户名或者名字或者密码不能为空");
            }
            HttpSession session = request.getSession();
            session.setAttribute("role",role);
            session.setAttribute("sex",sex);

            logger.error("num:{} name:{} password:{}  type:{}  sex:{}",num,name,password,role,sex);
                session.setAttribute("title",title);
                Staff login = staffService.login(num, password);
                if(login != null){
                    return JsonResult.fail("注册失败,用户名已存在");
                }
                else {
                    staffService.register(num, name, password,sex,title,email);
                    login = staffService.login(num, password);
                    session.setAttribute("user",login);
                    return JsonResult.ok().set("returnUrl", "/staff/main");
                }
        } catch (Exception e) {
            return JsonResult.fail(e.getMessage());
        }
    }

    /**
     * 注销用户
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){

        return "redirect:/";
    }



    @PostMapping("/admin/update/")
    @ResponseBody
    public JsonResult updatePassword(@SessionAttribute("role") String role,@RequestParam Long num,@RequestParam String oldPassword, @RequestParam String newPassword,
                                 @RequestParam String rePassword, ModelMap map){
        logger.error("role:{}",role);
        if(StringUtils.isEmpty(role)){
            return JsonResult.fail("此用户不存在");
        }
        if(StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword) || StringUtils.isBlank(rePassword)){
            return JsonResult.fail("参数不完整");
        }
        if(!newPassword.equals(rePassword)){
            return JsonResult.fail("两次输入密码不一致");
        }
        if(role.equals("staff")){
            Staff dbUser = staffService.findByNum(num);
            if(!dbUser.getPassword().equals(oldPassword)){
                return JsonResult.fail("旧密码不正确");
            }
            dbUser.setPassword(newPassword);
            staffService.updatePassword(dbUser);
        }
        return JsonResult.ok();
    }


}
