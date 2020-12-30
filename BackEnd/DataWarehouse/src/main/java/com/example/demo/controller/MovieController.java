package com.example.demo.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.JSONAndConfig.JsonResult;
import com.example.demo.service.movieService;
import com.example.demo.service.timeService;
import com.example.demo.service.typeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
//使用该注解返回类型自动调整为json.
@RequestMapping("/movie")
@Api(value="查找电影相关信息")
public class MovieController {

    @Autowired
    private movieService movieservice;

    @Autowired
    private timeService timeservice;

    @Autowired
    private typeService typeservice;

    @PostMapping(value = "/SpecificTimeMovie")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "测试按时间查找电影", notes = "前端传递年季度月日")
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

            List<Integer> temp1= timeservice.provideQualifiedYear();
            myWatch.stop();
            long temp=myWatch.getLastTaskTimeMillis();
            return new JsonResult(temp1,"失败",temp);
        }
        if(season>0&&season<5)
        {
            List<Map<String, Float>> temp1= movieservice.findBySpecificSeason(year, season);
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
            map.put("电影列表",movieservice.findBySpecifidTime(year, month, day));

            /**
             * service层对month ,day缺省的情况做分别处理。
             */
            map.put("测试所查timeID",movieservice.findBySpecificTime(year, month, day));

            myWatch.stop();
            long temp=myWatch.getLastTaskTimeMillis();

            return new JsonResult(map,"成功",temp);
        }
    }


    @PostMapping(value="/SpecificYearMovie")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "测试按时间查找电影数量", notes = "前端传递年")
    public JsonResult SpecificYearMovie( @RequestParam("year") Integer year)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");

        JSONArray temp1= timeservice.findYearMovie(year);
        myWatch.stop();
        return new JsonResult(temp1,
                "成功",myWatch.getLastTaskTimeMillis());
    }


    @PostMapping(value="/ListMovie")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "测试按时间查找电影数量", notes = "前端无参数")
    public JsonResult ListMovie()
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp1= timeservice.findAllMovie();
        myWatch.stop();
        return new JsonResult(temp1,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @PostMapping(value="/SimpleMovie")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找具体电影的简要情况", notes = "前端传递电影名称")
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
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找具体电影的具体情况", notes = "前端传递电影名称")
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
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找电影的导演", notes = "前端传递电影名称")
    public JsonResult getMovieDirector(@RequestParam("title") String title)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=movieservice.getDirectorOrActor(title,'D');
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @PostMapping(value="/getMovieActor")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找电影的演员", notes = "前端传递电影名称")
    public JsonResult getMovieActor(@RequestParam("title") String title)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=movieservice.getDirectorOrActor(title,'A');
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

}
