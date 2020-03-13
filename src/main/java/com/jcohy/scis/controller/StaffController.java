package com.jcohy.scis.controller;

import com.jcohy.lang.StringUtils;
import com.jcohy.scis.common.PageJson;
import com.jcohy.scis.model.Dept;
import com.jcohy.scis.model.Project;
import com.jcohy.scis.model.Teacher;
import com.jcohy.scis.service.DeptService;
import com.jcohy.scis.service.ProjectService;
import com.jcohy.scis.service.StudentService;
import com.jcohy.scis.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: staffController
 * Description:
 **/
@Controller
@RequestMapping("/staff")
public class StaffController extends BaseController{

}
