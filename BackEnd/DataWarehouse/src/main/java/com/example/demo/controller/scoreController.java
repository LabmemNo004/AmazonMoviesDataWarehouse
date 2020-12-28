package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.JSONAndConfig.JsonResult;
import com.example.demo.service.typeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
//使用该注解返回类型自动调整为json.
@RequestMapping("/score")
@Api(value="根据各个维度的评分查找电影")
public class scoreController {

    @Autowired
    private typeService typeservice;

    @PostMapping(value="/getPopularMovie")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找受欢迎或小众的电影类别", notes = "前端传递升序或降序排列")
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
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "根据电影类别查找电影", notes = "前端传递电影类别")
    public JsonResult getMovieByType(@RequestParam(value = "type") String type)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONObject temp=typeservice.getMovieByType(type);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @PostMapping(value="/getMovieByScore")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "根据评分查找电影", notes = "前端传递评分")
    public JsonResult getMovieByScore(@RequestParam(value = "scores") Integer scores)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONObject temp=typeservice.getGreaterscoreMovie(scores);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @PostMapping(value="/getPositiveMovie")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找有正面评价的电影", notes = "")
    public JsonResult getPositiveMovie()
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONObject temp=typeservice.getPositivemovie();
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }





    //    @PostMapping(value="/getMovieUser2")
//    @ResponseBody   //接受前端json格式的数据
//    @ApiOperation(value = "查找电影的评论者", notes = "前端传递电影名称")
//    public JsonResult getMovieUser2()
//    {
//        StopWatch myWatch = new StopWatch("myWatch");
//        myWatch.start("task1");
//
//
//        int temp1=0;
//        myWatch.stop();
//        return new JsonResult(temp1,
//                "成功",myWatch.getLastTaskTimeMillis());
//    }


}
