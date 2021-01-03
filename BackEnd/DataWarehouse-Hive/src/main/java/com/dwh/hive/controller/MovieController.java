package com.dwh.hive.controller;

import com.alibaba.fastjson.JSONArray;
import com.dwh.hive.GlobalResult.JsonResult;
import com.dwh.hive.service.MovieService;
import com.dwh.hive.service.RelationService;
import com.dwh.hive.service.YearMonthService;
import com.dwh.hive.service.YearReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieservice;

    @Autowired
    private YearReleaseService yearReleaseService;

    @Autowired
    private YearMonthService yearMonthService;

    @Autowired
    private RelationService relationService;

    @PostMapping(value = "/SpecificTimeMovie")
    @ResponseBody   
//    @ApiOperation(value = "测试按时间查找电影", notes = "前端传递年季度月日")
    public JsonResult SpecificTimeMovie(
            @RequestParam("year") Integer year,
            @RequestParam(value="season",required = false,defaultValue = "0") Integer season,
            @RequestParam(value="month",required = false,defaultValue = "0")Integer month,
            @RequestParam(value="day",required = false,defaultValue = "0")Integer day
    )
    {
        /**
         * 季度不在1~4之间表示不按季度查询，除年份以外都是如此。
         * 默认不可能查询所有电影所以year一定要合法，
         */
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");

        Map<String, Object> map = new HashMap<>(4);
        if(year<2019||year>2020)
        {
            /**
             * 年限范围有待确定。
             */

            List<Integer> temp1= yearReleaseService.provideQualifiedYear();
            myWatch.stop();
            long temp=myWatch.getLastTaskTimeMillis();
            return new JsonResult(temp1,"失败",temp);
        }
        if(season>0&&season<5)
        {
            JSONArray temp1= movieservice.findBySpecificSeason(year, season);
            myWatch.stop();
            return new JsonResult(temp1,
                    "成功",myWatch.getLastTaskTimeMillis());
        }
        else if(season<0||season>=5)
        {
            myWatch.stop();
            return new JsonResult("返回合理的季度",
                    "失败",myWatch.getLastTaskTimeMillis());
        }
        else {

            map.put("电影列表",movieservice.findBySpecificTime(year, month, day));

            myWatch.stop();
            long temp=myWatch.getLastTaskTimeMillis();
            return new JsonResult(map,"成功",temp);
        }
    }


    @PostMapping(value="/SpecificYearMovie")
    @ResponseBody   
//    @ApiOperation(value = "测试按时间查找电影数量", notes = "前端传递年")
    public JsonResult SpecificYearMovie( @RequestParam("year") Integer year)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");

        JSONArray temp1 = yearMonthService.findYearMovie(year);
        myWatch.stop();
        return new JsonResult(temp1,
                "成功",myWatch.getLastTaskTimeMillis());
    }


    @PostMapping(value="/ListMovie")
    @ResponseBody   
//    @ApiOperation(value = "测试按时间查找电影数量", notes = "前端无参数")
    public JsonResult ListMovie()
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp1= yearReleaseService.findAllMovie();
        myWatch.stop();
        return new JsonResult(temp1,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @PostMapping(value="/SimpleMovie")
    @ResponseBody   
//    @ApiOperation(value = "查找具体电影的简要情况", notes = "前端传递电影名称")
    public JsonResult SimpleMovie(@RequestParam("title") String title)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        //产品数量、导演数量、演员数量、评论数量、评分
        JSONArray temp=movieservice.getSimpleMovie(title);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @PostMapping(value="/DetailMovie")
    @ResponseBody   
//    @ApiOperation(value = "查找具体电影的具体情况", notes = "前端传递电影名称")
    public JsonResult DetailMovie(@RequestParam("title") String title)
    {
        /**
         * 产品列表：返回电影产品名称、ASIN号、发布时间、
         *         格式、类型、Amazon网址，并按照发布时间先后排序
         */
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");

        JSONArray temp=movieservice.getDetailProduct(title);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @PostMapping(value="/getMovieDirector")
    @ResponseBody   
//    @ApiOperation(value = "查找电影的导演", notes = "前端传递电影名称")
    public JsonResult getMovieDirector(@RequestParam("title") String title)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp = relationService.getDirectorOrActor(title,'D');
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @PostMapping(value="/getMovieActor")
    @ResponseBody   
//    @ApiOperation(value = "查找电影的演员", notes = "前端传递电影名称")
    public JsonResult getMovieActor(@RequestParam("title") String title)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=relationService.getDirectorOrActor(title,'A');
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }
}
