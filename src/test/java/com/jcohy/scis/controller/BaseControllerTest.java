package com.jcohy.scis.controller;

import com.jcohy.scis.ScisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ScisApplication.class)
@AutoConfigureMockMvc
@Transactional //该注释可以使数据库回滚
public class BaseControllerTest
{
    @Autowired
    private BaseController baseController;

    @Test
    public void getPageRequestTest()
    {
        PageRequest pageRequest = baseController.getPageRequest();
        assertEquals(0,pageRequest.getPageNumber());
        assertEquals(10,pageRequest.getPageSize());
    }
}