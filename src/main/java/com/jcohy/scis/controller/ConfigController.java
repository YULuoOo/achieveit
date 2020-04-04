package com.jcohy.scis.controller;

import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.model.Config;
import com.jcohy.scis.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    private ConfigService configService;
    @GetMapping("/{id}/get")
    @ResponseBody
    public JsonResult getConfig(@PathVariable("id") Integer id){
        Config config;
        try
        {
            config = configService.getOne(id);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.ok().set("data",new Config());
        }
        return JsonResult.ok().set("data",config);
    }

    @PostMapping("/{id}/create")
    @ResponseBody
    public JsonResult createConfig(HttpServletRequest request,
                                        @PathVariable("id") Integer id,
                                        @RequestParam(required = false)  String giturl,
                                        @RequestParam(required = false)  String root,
                                        @RequestParam(required = false)  String disk_size
                                        ){
        try {
            configService.createConfig(id,giturl,root,disk_size);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }
    @PostMapping("/{id}/update")
    @ResponseBody
    public JsonResult updateConfig(HttpServletRequest request,
                                        @PathVariable("id") Integer id,
                                        @RequestParam(required = false)  String giturl,
                                        @RequestParam(required = false)  String root,
                                        @RequestParam(required = false)  String disk_size
    ){
        try {
            Config config = configService.getOne(id);
            if(config != null && config.getProject_id()==id){
                configService.updateConfig(id,giturl,root,disk_size);
            } else{
                configService.createConfig(id,giturl,root,disk_size);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }


}
