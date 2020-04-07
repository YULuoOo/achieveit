package com.jcohy.scis.controller;

import com.alibaba.fastjson.JSON;
import com.jcohy.scis.ScisApplication;
import com.jcohy.scis.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.MockitoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.mail.Session;
import javax.transaction.Transactional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ScisApplication.class)
@AutoConfigureMockMvc
@Transactional //该注释可以使数据库回滚
public class LoginControllerTest
{
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private StaffService staffService;

    //登录
    @Test
    public void loginNormalTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("num","10001")
                        .param("password","123456")
                        .param("role","staff"))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void loginNullNumTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("num","")
                        .param("password","123456")
                        .param("role","staff"))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void loginNumNotExistTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("num","0000000000000")
                        .param("password","123456")
                        .param("role","staff"))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void loginWrongPasswordTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("num","10001")
                        .param("password","000000")
                        .param("role","staff"))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    //注册
    @Test
    public void registerNormalTest() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                post("/regi")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("num","999999")
                        .param("name","test")
                        .param("password","999999")
                        .param("role","staff")
                        .param("title","项目经理")
                        .param("sex","男"))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void registerNullNumTest() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                post("/regi")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("num","")
                        .param("name","test")
                        .param("password","999999")
                        .param("role","staff")
                        .param("title","项目经理")
                        .param("sex","男"))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void registerExistNumTest() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                post("/regi")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("num","10001")
                        .param("name","test")
                        .param("password","999999")
                        .param("role","staff")
                        .param("title","项目经理")
                        .param("sex","男"))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void logoutTest()throws Exception
    {
        MvcResult mvcResult = mockMvc.perform(
                get("/logout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void updatePasswordNormalTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                post("/admin/update/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .sessionAttr("role","staff")
                        .param("role","staff")
                        .param("num","10001")
                        .param("oldPassword","123456")
                        .param("newPassword","999999")
                        .param("rePassword","999999"))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void updatePasswordRoleNotExistTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                post("/admin/update/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .sessionAttr("role","")
                        .param("role","")
                        .param("num","000000000")
                        .param("oldPassword","123456")
                        .param("newPassword","999999")
                        .param("rePassword","999999"))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void updatePasswordParametersIncompleteTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                post("/admin/update/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .sessionAttr("role","staff")
                        .param("role","staff")
                        .param("num","10001")
                        .param("oldPassword","  ")
                        .param("newPassword","")
                        .param("rePassword",""))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void updatePasswordDifferentPasswordTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                post("/admin/update/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .sessionAttr("role","staff")
                        .param("role","staff")
                        .param("num","10001")
                        .param("oldPassword","123456")
                        .param("newPassword","999999")
                        .param("rePassword","999999999"))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void updatePasswordWrongOldPasswordTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                post("/admin/update/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .sessionAttr("role","staff")
                        .param("role","staff")
                        .param("num","10001")
                        .param("oldPassword","000000000")
                        .param("newPassword","999999")
                        .param("rePassword","999999"))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}