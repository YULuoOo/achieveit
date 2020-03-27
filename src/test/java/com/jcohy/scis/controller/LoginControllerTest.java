package com.jcohy.scis.controller;

import com.alibaba.fastjson.JSON;
import com.jcohy.scis.ScisApplication;
import com.jcohy.scis.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ScisApplication.class)
@AutoConfigureMockMvc

public class LoginControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ExpertService expertService;

    @Autowired
    private StaffService staffService;

    @Test
    public void login() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                get("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString("")))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void register() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                get("/regi")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString("")))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void logout()
    {
    }

    @Test
    public void updatePassword() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                get("/admin/update/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString("")))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}