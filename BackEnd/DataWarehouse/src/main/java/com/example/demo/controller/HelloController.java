package com.example.demo.controller;

import com.example.demo.Entity.person;
import com.example.demo.JSON.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jsonresult")
public class HelloController {

//    @RequestMapping("/person")
//    public JsonResult<person> getperson() {
//        person persons = new person("倪升武", "123456","~/static/img/1.png");
//        return new JsonResult<>(persons);
//    }
//
//    @RequestMapping("/list")
//    public JsonResult<List> getpersonList() {
//        List<person> personList = new ArrayList<>();
//        person person1 = new person("倪升武1", "123456","~/static/img/1.png");
//        person person2 = new person("达人课2", "123456","~/static/img/1.png");
//        personList.add(person1);
//        personList.add(person2);
//        return new JsonResult<>(personList, "获取用户列表成功");
//    }

    @RequestMapping("/map")
    public JsonResult<Map> getMap() {
        Map<String, Object> map = new HashMap<>(1);
        //person person = new person("倪升武3", null,null);
        //map.put("作者信息", person);
        map.put("博客地址", "http://blog.itcodai.com");
        map.put("CSDN地址", null);
        map.put("粉丝数量", 4153);
        return new JsonResult<>(map,"成功");
    }

    @RequestMapping("/hello")
    public String getHelloWorld()
    {
        return "Hello Spring";
    }
}
