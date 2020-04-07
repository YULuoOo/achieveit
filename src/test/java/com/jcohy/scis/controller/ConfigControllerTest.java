package com.jcohy.scis.controller;

import com.alibaba.fastjson.JSON;
import com.jcohy.scis.ScisApplication;
import com.jcohy.scis.service.AchProjectService;
import com.jcohy.scis.service.ConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.HttpSession;
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
public class ConfigControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AchProjectService achProjectService;
    @Autowired
    private ConfigService configService;

    @Test
    public void getConfigTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                get("/config/1/get")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString("")))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getConfigNullIdTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                get("/config/null/get")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void createConfigTest() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                post("/config/1/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("giturl","aaa")
                        .param("root","test")
                        .param("disk_size","8gb")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void createConfigNullIdTest() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                post("/config/0/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("giturl","aaa")
                        .param("root","test")
                        .param("disk_size","8gb")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void updateConfigTest1() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                post("/config/1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("giturl","bbb")
                        .param("root","test")
                        .param("disk_size","8gb")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void updateConfigTest2() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                post("/config/0/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("giturl","bbb")
                        .param("root","test")
                        .param("disk_size","8gb")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}