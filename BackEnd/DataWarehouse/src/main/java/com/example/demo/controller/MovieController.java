package com.example.demo.controller;


import com.example.demo.Entity.year_monthPK;
import com.example.demo.Entity.year_month_release;
import com.example.demo.JSONAndConfig.JsonResult;
import com.example.demo.dao.yearMonthRepository;
import com.example.demo.service.movieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//使用该注解返回类型自动调整为json.
@RequestMapping("/movie")
@Api(value="测试按时间查找电影")
public class MovieController {


    @Autowired
    private yearMonthRepository yearmonthRepository;

    @Autowired
    private movieService movieservice;

    @PostMapping(value = "/SpecificTimeMovie")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "测试按时间查找电影", notes = "前端传递年季度月日")
    public JsonResult<Map> SpecificTimeMovie(
            @RequestParam("year") Integer year,
            @RequestParam("season") Integer season,
            @RequestParam("month")Integer month,
            @RequestParam("day")Integer day
    )
    {
        /**
         * 季度不在1~4之间表示不按季度查询，除年份以外都是如此。
         * 默认不可能查询所有电影所以year一定要合法，
         * select title,score from movie
         * where ...
         *  时间=》timeID=>product.movieID
         */
        Map<String, Object> map = new HashMap<>(4);
        if(year<2019||year>2020)
        {

            map.put("错误提示","必须指定合法年份");
            return new JsonResult<>(map);
        }
        if(season>0&&season<5)
        {

            map.put("提示","暂未处理季度查询");
            return new JsonResult<>(map);
        }
        else
        {

//            year_monthPK temp=new year_monthPK(year,month);
//            year_month_release yearMonthRelease= yearmonthRepository.getOne(temp);
//            map.put("测试",yearMonthRelease);
            map.put("电影列表",movieservice.findBySpecifidTime(year, month, day));
            return new JsonResult<> (map,"成功");

            //map.put("条件查询",yearmonthRepository.findByReleaseYearLessThan(2029));
        }
    }



}
