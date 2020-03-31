package com.jcohy.scis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.jcohy.scis.ScisApplication;
import com.jcohy.scis.model.Staff;
import com.jcohy.scis.service.AchProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

//测试方法、规则说明：
//下方@Transitional 注释能使数据库回滚，若想通过数据库观察结果可先删掉该注释，测试完后需要加上该注释
//测试函数在函数名后加上Test， 有些函数可能需要多个测试则在函数名中描述清楚测试内容
//注意区分get、post、del（delete）、put 要与controller中设置的对应
//需要string int参数的时可用param传参数
//需要HttpServletRequest参数时 用with设置request的内容
//request传递的session无法直接创建， 需要用到mock相关知识让其返回需要的user 具体可以参考createProjectTest中的代码
//对于没有assert的test，绿色不一定代表没问题，要记得点开测试看打印的内容是否正确
//提示：调试某个Test的时候可以单独运行该Test节省时间

//特例：若需要传递参数在源代码中有@SessionAttribute 注释，使用sessionAttr传递该参数 参考LoginControllerTest中的updatePasswordTest

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ScisApplication.class)
@AutoConfigureMockMvc
@Transactional //该注释可以使数据库回滚
public class StaffControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AchProjectService achProjectService;

    //OK
    @Test
    public void allTest() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                get("/staff/project/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());

    }

    //not OK
    @Test
    public void projectJoinListTest() throws Exception{
        Staff user = new Staff();
        user.setId(1);
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("user")).thenReturn(user);
        MvcResult mvcResult = mockMvc.perform(
                get("/staff/project/joinlist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(new RequestPostProcessor()
                        {
                            @Override
                            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest mockHttpServletRequest)
                            {
                                mockHttpServletRequest.setSession(session);
                                return mockHttpServletRequest;
                            }
                        }))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    //OK
    @Test
    public void allWorkingHourTest() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                get("/staff/workinghour/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    //OK
    @Test
    public void getProcessTest() throws Exception{
        Staff user = new Staff();
        user.setTitle("项目经理");
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("user")).thenReturn(user);
        MvcResult mvcResult = mockMvc.perform(
                get("/staff/project/process")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(new RequestPostProcessor()
                        {
                            @Override
                            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest mockHttpServletRequest)
                            {
                                mockHttpServletRequest.setSession(session);
                                return mockHttpServletRequest;
                            }
                        }))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    //OK
    @Test
    public void getMemberTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                get("/staff/project/1/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        )
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    //not OK
    @Test
    public void getOtherStaffsTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                get("/staff/project/1/others")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString("")))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    //OK
    @Test
    public void getProjectTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                get("/staff/project/1/get")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString("")))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    //OK
    @Test
    public void createProjectTest() throws Exception {
        Staff user = new Staff();
        user.setId(1);
        user.setName("员工1");
        user.setEmail("test@163.com");
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("user")).thenReturn(user);
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
                        .with(new RequestPostProcessor()
                        {
                            @Override
                            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest mockHttpServletRequest)
                            {
                                mockHttpServletRequest.setSession(session);
                                return mockHttpServletRequest;
                            }
                        })
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    //OK
    @Test
    public void createWorkingHourTest() throws Exception{
        Staff user = new Staff();
        user.setId(1);
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("user")).thenReturn(user);
        MvcResult mvcResult = mockMvc.perform(
                post("/staff/workinghour/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("work_content", "test content")
                        .param("work_date","2020-01-01")
                        .param("work_length","8")
                        .with(new RequestPostProcessor()
                        {
                            @Override
                            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest mockHttpServletRequest)
                            {
                                mockHttpServletRequest.setSession(session);
                                return mockHttpServletRequest;
                            }
                        })
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    //OK
    @Test
    public void delTest() throws Exception{
        MvcResult mvcResult = mockMvc.perform(
                delete("/staff/project/4/del")
                        .contentType(MediaType.APPLICATION_JSON)
                        )
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    //OK
    @Test
    public void updateProjectTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                put("/staff/project/1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "test")
                        .param("desc","test")
                        .param("tech","test")
                        .param("area","test")
                        .param("func","test")
                        .param("enddate","1-1-1")
                        .param("startdate","1-1-1")
                        .param("id","1")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    //OK
    @Test
    public void acceptProjectTest() throws Exception {
        Staff user = new Staff();
        user.setTitle("项目上级");
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("user")).thenReturn(user);
        MvcResult mvcResult = mockMvc.perform(
                put("/staff/project/1/accept")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(new RequestPostProcessor()
                        {
                            @Override
                            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest mockHttpServletRequest)
                            {
                                mockHttpServletRequest.setSession(session);
                                return mockHttpServletRequest;
                            }
                        }))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void refuseProjectTest() throws Exception{
        Staff user = new Staff();
        user.setTitle("项目上级");
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("user")).thenReturn(user);
        MvcResult mvcResult = mockMvc.perform(
                put("/staff/project/1/refuse")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(new RequestPostProcessor()
                        {
                            @Override
                            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest mockHttpServletRequest)
                            {
                                mockHttpServletRequest.setSession(session);
                                return mockHttpServletRequest;
                            }
                        }))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    //OK
    @Test
    public void updateMembersTest() throws Exception{
        String[] staff_ids = {"1","2"};
        MvcResult mvcResult = mockMvc.perform(
                post("/staff/project/1/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(new RequestPostProcessor()
                        {
                            @Override
                            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest mockHttpServletRequest)
                            {
                                mockHttpServletRequest.addParameter("check",staff_ids);
                                return mockHttpServletRequest;
                            }
                        })
                        )
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }


}