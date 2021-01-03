package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.JSONAndConfig.JsonResult;
import com.example.demo.service.typeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.var;
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

    @PostMapping(value="/getPopularMovieType")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找受欢迎或小众的电影类别", notes = "前端传递升序或降序排列")
    public JsonResult getPopularMovie(@RequestParam(value = "up") Integer up)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=typeservice.getPopularMovie(up);
        int i=temp.size()-1;
        Object counts=temp.getJSONObject(i).get("总查询数量");
        temp.fluentRemove(i);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis(),counts);
    }

    @PostMapping(value="/getMovieByType")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "根据电影类别查找电影", notes = "前端传递电影类别")
    public JsonResult getMovieByType(@RequestParam(value = "type") String type)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=typeservice.getMovieByType(type);
        int i=temp.size()-1;
        Object counts=temp.getJSONObject(i).get("总查询数量");
        temp.fluentRemove(i);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis(),counts);
    }

    @PostMapping(value="/getMovieByScore")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "根据评分查找电影", notes = "前端传递评分")
    public JsonResult getMovieByScore(@RequestParam(value = "scores") Integer scores)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=typeservice.getGreaterscoreMovie(scores);
        int i=temp.size()-1;
        Object counts=temp.getJSONObject(i).get("总查询数量");
        temp.fluentRemove(i);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis(),counts);
    }

    @PostMapping(value="/getPositiveMovie")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找有正面评价的电影", notes = "")
    public JsonResult getPositiveMovie()
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=typeservice.getPositivemovie();
        int i=temp.size()-1;
        Object counts=temp.getJSONObject(i).get("总查询数量");
        temp.fluentRemove(i);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis(),counts);
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
