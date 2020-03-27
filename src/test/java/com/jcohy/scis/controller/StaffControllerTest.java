package com.jcohy.scis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.jcohy.scis.ScisApplication;
import com.jcohy.scis.service.AchProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ScisApplication.class)
@AutoConfigureMockMvc

public class StaffControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AchProjectService achProjectService;
    @Test
    public void all() {
    }

    @Test
    public void getProcess() {
    }

    @Test
    public void getMember() {
    }

    @Test
    public void getProject() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                get("/staff/project/1/get")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString("")))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void createProject() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                post("/staff/project/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "test")
                        .param("desc","test")
                        .param("tech","test")
                        .param("area","test")
                        .param("func","test")
                        .param("enddate","1-1-1")
                        .param("startdate","1-1-1")
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void del() {
    }

    @Test
    public void updateProject() {
    }

    @Test
    public void acceptProject() {
    }

    @Test
    public void refuseProject() {
    }
}