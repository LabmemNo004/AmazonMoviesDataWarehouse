package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.person;
import com.example.demo.JSONAndConfig.JsonResult;
import com.example.demo.dao.personRepository;
import com.example.demo.service.personService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
//使用该注解返回类型自动调整为json.
@RequestMapping("/person")
@Api(value="查找人员相关信息")
public class PersonController {

    @Autowired
    private personService personService;

//    @GetMapping(value = "/watchList")
//    public List<person> getWatchList()
//    {
//        return personService.findAll();
//    }
//
//    @GetMapping(value="test")
//    public List<String> test()
//    {
//        Integer[] array = { 1, 2, 3,4,5,6,7,8,9,10,11,12,13,14,15,16 };
//        List<Integer> result=new ArrayList<>();
//
//        List<Integer> a= Arrays.asList(array);
//
//        List<String>positive=new ArrayList<String>();
//        List<String>negative=new ArrayList<String>();
//
//        for(int x:a)
//        {
//            if(x<16)
//            {
//                result.add(x + 1);
//                result.add(x + 16);
//                result.add(x + 17);
//            }
//            else
//            {
//                break;
//            }
//        }
//        result.add(16);
//        result.add(32);
//        for(Integer x:result)
//        {
//            if(x<=16)
//            {
//                positive.add("T");
//                negative.add("F");
//            }
//            else
//            {
//                positive.add("F");
//                negative.add("T");
//            }
//        }
//
//
//        List<String> strings = result.stream().map(x -> x + "").collect(Collectors.toList());
//        return strings;
//
//    }

    @GetMapping(value="/getDirectorWorks")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找导演所指导的电影", notes = "前端传递导演名称")
    public JsonResult getDirectorWorks(@RequestParam(value = "name") String name)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONObject temp=personService.getDirectorWork(name);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @GetMapping(value="/getGreaterDirector")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "按作品数量查询导演", notes = "前端传递作品数量")
    public JsonResult getGreaterDirector(@RequestParam(value = "number") int number)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=personService.getGreaterDirector(number);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @GetMapping(value="/getActorWorks")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找演员所参加的电影", notes = "前端传递演员名称")
    public JsonResult getActorWorks(@RequestParam(value = "name") String name)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONObject temp=personService.getActorWork(name);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @GetMapping(value="/getGreaterActor")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "按作品数量查询演员", notes = "前端传递作品数量")
    public JsonResult getGreaterActor(@RequestParam(value = "number") int number)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=personService.getGreaterActor(number);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }


    @GetMapping(value="/getCodirector")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找所有合作导演", notes = "前端传递人名")
    public JsonResult getCodirector(@RequestParam(value = "name") String name)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=personService.getDirectorCompanion(name);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @GetMapping(value="/getCoactor")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找所有合作演员", notes = "前端传递人名")
    public JsonResult getCoactor(@RequestParam(value = "name") String name)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=personService.getActorCompanion(name);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @GetMapping(value="/getCooperatorActor")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找经常合作的演员", notes = "前端无参数传递??")
    public JsonResult getCooperatorActor()
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=personService.getCooperatorActor();
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }


    @GetMapping(value="/getCooperatorDirector")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找经常合作的导演", notes = "前端无参数传递??")
    public JsonResult getCooperatorDirector()
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=personService.getCooperatorDirector();
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @GetMapping(value="/getCooperatorAD")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "查找经常合作的导演演员组合", notes = "前端无参数传递??")
    public JsonResult getCooperatorAD()
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=personService.getCooperatorAD();
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

}
