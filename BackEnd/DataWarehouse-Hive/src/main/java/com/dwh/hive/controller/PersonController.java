package com.dwh.hive.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dwh.hive.GlobalResult.JsonResult;
import com.dwh.hive.service.CooperationService;
import com.dwh.hive.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private CooperationService cooperationService;

    @GetMapping(value="/getDirectorWorks")
    @ResponseBody   
    //@ApiOperation(value = "查找导演所指导的电影", notes = "前端传递导演名称")
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
    @ResponseBody   
    //@ApiOperation(value = "按作品数量查询导演", notes = "前端传递作品数量")
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
    @ResponseBody   
    //@ApiOperation(value = "查找演员所参加的电影", notes = "前端传递演员名称")
    public JsonResult getActorWorks(@RequestParam(value = "name") String name)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=personService.getActorWork(name);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @GetMapping(value="/getGreaterActor")
    @ResponseBody   
    //@ApiOperation(value = "按作品数量查询演员", notes = "前端传递作品数量")
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
    @ResponseBody   
    //@ApiOperation(value = "查找所有合作导演", notes = "前端传递人名")
    public JsonResult getCodirector(@RequestParam(value = "name") String name)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=cooperationService.getDirectorCompanion(name);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @GetMapping(value="/getCoactor")
    @ResponseBody   
    //@ApiOperation(value = "查找所有合作演员", notes = "前端传递人名")
    public JsonResult getCoactor(@RequestParam(value = "name") String name)
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=cooperationService.getActorCompanion(name);
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @GetMapping(value="/getCooperatorActor")
    @ResponseBody   
    //@ApiOperation(value = "查找经常合作的演员", notes = "前端无参数传递??")
    public JsonResult getCooperatorActor()
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=cooperationService.getCooperatorActor();
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }


    @GetMapping(value="/getCooperatorDirector")
    @ResponseBody   
    //@ApiOperation(value = "查找经常合作的导演", notes = "前端无参数传递??")
    public JsonResult getCooperatorDirector()
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=cooperationService.getCooperatorDirector();
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

    @GetMapping(value="/getCooperatorAD")
    @ResponseBody   
   // @ApiOperation(value = "查找经常合作的导演演员组合", notes = "前端无参数传递??")
    public JsonResult getCooperatorAD()
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp=cooperationService.getCooperatorAD();
        myWatch.stop();
        return new JsonResult(temp,
                "成功",myWatch.getLastTaskTimeMillis());
    }

}
