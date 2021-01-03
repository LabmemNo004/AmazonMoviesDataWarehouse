package com.dwh.hive.controller;

import com.alibaba.fastjson.JSONArray;
import com.dwh.hive.GlobalResult.JsonResult;
import com.dwh.hive.service.ReleaseTimeService;
import com.dwh.hive.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Combination")
public class CombinationController {

    @Autowired
    private ReleaseTimeService releaseTimeService;

    @Autowired
    private TypeService typeService;

    @GetMapping("/hello")
//    @ApiOperation(value = "获取用户信息", notes = "通过用户ID获取用户信息")
    public String getHelloWorld()
    {
        return "Hello Spring";
    }

    @PostMapping(value="/getTimeDirectorType")
    @ResponseBody   
//    @ApiOperation(value = "组合查询时间导演类型", notes = "前端传递年,导演名，类型名")
    public JsonResult getTimeDirectorType (
            @RequestParam("year") Integer year,
            @RequestParam("name") String name,
            @RequestParam("type") String type
    )
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp1=releaseTimeService.getTimeDirectorType(year, name, type);
        myWatch.stop();
        return new JsonResult(temp1,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @PostMapping(value="/getTimePopular")
    @ResponseBody   
//    @ApiOperation(value = "组合查询某年最受欢迎的电影类别", notes = "前端传递年")
    public JsonResult getTimePopular(
            @RequestParam("year") Integer year
    )
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");

        JSONArray temp1=typeService.getTimePopular(year);
        myWatch.stop();
        return new JsonResult(temp1,
                "成功",myWatch.getLastTaskTimeMillis());
    }
}
