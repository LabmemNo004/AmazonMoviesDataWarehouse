package com.dwh.hive.controller;

import com.alibaba.fastjson.JSONArray;
import com.dwh.hive.GlobalResult.JsonResult;
import com.dwh.hive.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    private TypeService typeservice;

    @PostMapping(value="/getPopularMovieType")
    @ResponseBody   //
//    @ApiOperation(value = "查找受欢迎或小众的电影类别", notes = "前端传递升序或降序排列")
    public JsonResult getPopularMovie(@RequestParam(value = "up") Integer up)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=typeservice.getPopularMovie(up);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @PostMapping(value="/getMovieByType")
    @ResponseBody   //
//    @ApiOperation(value = "根据电影类别查找电影", notes = "前端传递电影类别")
    public JsonResult getMovieByType(@RequestParam(value = "type") String type)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=typeservice.getMovieByType(type);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @PostMapping(value="/getMovieByScore")
    @ResponseBody   //
//    @ApiOperation(value = "根据评分查找电影", notes = "前端传递评分")
    public JsonResult getMovieByScore(@RequestParam(value = "scores") Integer scores)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=typeservice.getGreaterscoreMovie(scores);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @PostMapping(value="/getPositiveMovie")
    @ResponseBody   //
//    @ApiOperation(value = "查找有正面评价的电影", notes = "")
    public JsonResult getPositiveMovie()
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=typeservice.getPositivemovie();
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

}
