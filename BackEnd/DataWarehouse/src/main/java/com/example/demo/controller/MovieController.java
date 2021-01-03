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
            JSONArray temp1= movieservice.findBySpecificSeason(year, season);
            int i=temp1.size()-1;
            Object counts=temp1.getJSONObject(i).get("总查询数量");
            temp1.fluentRemove(i);
            myWatch.stop();
            return new JsonResult(temp1,
                    "成功",myWatch.getLastTaskTimeMillis(),counts);
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
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "测试按时间查找电影数量", notes = "前端传递年")
    public JsonResult SpecificYearMovie( @RequestParam("year") Integer year)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");

        JSONArray temp1= timeservice.findYearMovie(year);
        int i=temp1.size()-1;
        Object counts=temp1.getJSONObject(i).get("总查询数量");
        temp1.fluentRemove(i);
        myWatch.stop();
        return new JsonResult(temp1,
                "成功",myWatch.getLastTaskTimeMillis(),counts);
    }


    @PostMapping(value="/ListMovie")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "测试按时间查找电影数量", notes = "前端无参数")
    public JsonResult ListMovie()
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp1= timeservice.findAllMovie();
        int i=temp1.size()-1;
        Object counts=temp1.getJSONObject(i).get("总查询数量");
        temp1.fluentRemove(i);
        myWatch.stop();
        return new JsonResult(temp1,
                "成功",myWatch.getLastTaskTimeMillis(),counts);
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
        int i=temp.size()-1;
        Object counts=temp.getJSONObject(i).get("总查询数量");
        temp.fluentRemove(i);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis(),counts);
    }

    @PostMapping(value="/SimpleMovieLike")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找具体电影的简要情况", notes = "前端传递电影名称")
    public JsonResult SimpleMovieLike(@RequestParam("title") String title)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        //产品数量、导演数量、演员数量、评论数量、评分
        JSONArray temp=movieservice.getSimpleMovieLike(title);
        int i=temp.size()-1;
        Object counts=temp.getJSONObject(i).get("总查询数量");
        temp.fluentRemove(i);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis(),counts);
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
        int i=temp.size()-1;
        Object counts=temp.getJSONObject(i).get("总查询数量");
        temp.fluentRemove(i);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis(),counts);
    }

    @PostMapping(value="/DetailMovieLike")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找具体电影的具体情况", notes = "前端传递电影名称")
    public JsonResult DetailMovieLike(@RequestParam("title") String title)
    {
        /**
         * 产品列表：返回电影产品名称、ASIN号、发布时间、
         *         格式、类型、Amazon网址，并按照发布时间先后排序
         */
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");

        JSONArray temp=movieservice.getDetailProductLike(title);
        int i=temp.size()-1;
        Object counts=temp.getJSONObject(i).get("总查询数量");
        temp.fluentRemove(i);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis(),counts);
    }



    @PostMapping(value="/getMovieDirector")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找电影的导演", notes = "前端传递电影名称")
    public JsonResult getMovieDirector(@RequestParam("title") String title)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=movieservice.getDirectorOrActor(title,'D');
        int i=temp.size()-1;
        Object counts=temp.getJSONObject(i).get("总查询数量");
        temp.fluentRemove(i);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis(),counts);
    }

    @PostMapping(value="/getMovieDirectorLike")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找电影的导演", notes = "前端传递电影名称")
    public JsonResult getMovieDirectorLike(@RequestParam("title") String title)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=movieservice.getDirectorOrActorLike(title,'D');
        int i=temp.size()-1;
        Object counts=temp.getJSONObject(i).get("总查询数量");
        temp.fluentRemove(i);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis(),counts);
    }




    @PostMapping(value="/getMovieActor")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找电影的演员", notes = "前端传递电影名称")
    public JsonResult getMovieActor(@RequestParam("title") String title)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=movieservice.getDirectorOrActor(title,'A');
        int i=temp.size()-1;
        Object counts=temp.getJSONObject(i).get("总查询数量");
        temp.fluentRemove(i);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis(),counts);
    }


    @PostMapping(value="/getMovieActorLike")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找电影的演员", notes = "前端传递电影名称")
    public JsonResult getMovieActorLike(@RequestParam("title") String title)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=movieservice.getDirectorOrActorLike(title,'A');
        int i=temp.size()-1;
        Object counts=temp.getJSONObject(i).get("总查询数量");
        temp.fluentRemove(i);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis(),counts);
    }

}
