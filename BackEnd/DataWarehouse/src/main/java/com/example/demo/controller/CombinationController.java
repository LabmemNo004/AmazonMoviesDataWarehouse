package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.JSONAndConfig.JsonResult;
import com.example.demo.dao.releaseTimeRepository;
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
@RequestMapping("/Combination")
@Api(value = "Combination Test")
public class CombinationController {



    @Autowired
    private timeService timeservice;


    @GetMapping("/hello")
    @ApiOperation(value = "获取用户信息", notes = "通过用户ID获取用户信息")
    public String getHelloWorld()
    {
        return "Hello Spring";
    }

    @PostMapping(value="/getTimeDirectorType")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "组合查询时间导演类型", notes = "前端传递年,导演名，类型名")
    public JsonResult getTimeDirectorType (
            @RequestParam("year") Integer year,
            @RequestParam("name") String name,
            @RequestParam("type") String type
    )
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");
        JSONArray temp1=timeservice.getTimeDirectorType(year, name, type);
        int i=temp1.size()-1;
        Object counts=temp1.getJSONObject(i).get("总查询数量");
        temp1.fluentRemove(i);
        myWatch.stop();
        return new JsonResult(temp1,
                "成功",myWatch.getLastTaskTimeMillis(),counts);
    }

    @PostMapping(value="/getTimePopular")
    @ResponseBody   //接受前端json格式的数据
    @ApiOperation(value = "组合查询某年最受欢迎的电影类别", notes = "前端传递年")
    public JsonResult getTimePopular(
            @RequestParam("year") Integer year
            )
    {
        StopWatch myWatch = new StopWatch("myWatch");
        myWatch.start("task1");

        JSONArray temp1=timeservice.getTimePopular(year);
        int i=temp1.size()-1;
        Object counts=temp1.getJSONObject(i).get("总查询数量");
        temp1.fluentRemove(i);
        myWatch.stop();
        return new JsonResult(temp1,
                "成功",myWatch.getLastTaskTimeMillis(),counts);
    }

}
/**
 * 2019-2020 16个电影产品，一个电影一个产品
 * 每年的1~8月有一个，在每个月的第一天发布。
 * 八个类型，每个类型两种产品，分属两年
 *
 *  演员a~h 1~16
 *  导演aa~hh 17~32
 *  每部电影{a,b+aa,bb}{b,c+bb,cc}
 *
 *  第一演员actorNum 1 directorNum 2 actorAndDirectorNum 0
 *  joinNum 3 actorOrDirector ‘A’ actorAverageScore:0 directorAverageScore:0
 *  中间30演员actorNum 2 directorNum 4 actorAndDirectorNum 0
 *  joinNum 6 actorOrDirector ‘A’ actorAverageScore:0 directorAverageScore:0
 *  最后演员actorNum 1 directorNum 2 actorAndDirectorNum 0
 *  joinNum 3 actorOrDirector ‘A’ actorAverageScore:0 directorAverageScore:0
 *
 *  第一演员actorNum 2 directorNum 1 actorAndDirectorNum 0
 *  joinNum 3 actorOrDirector ‘D’ actorAverageScore:0 directorAverageScore:0
 *  中间30导演actorNum 3 directorNum 2 actorAndDirectorNum 0
 *  joinNum 5 actorOrDirector ‘D’ actorAverageScore:0 directorAverageScore:0
 *  最后演员actorNum 2 directorNum 1 actorAndDirectorNum 0
 *  joinNum 3 actorOrDirector ‘D’ actorAverageScore:0 directorAverageScore:0
 *
 *  cooperation 1-{2,1+16,1+17}以此类推到15等--
 *
 *
 *  moive:{ 1~16
 *  title:{类型；种类；品种；象征；印刷文字；图案；预示；预兆；预兆性人物；型；打；输入；
 *          使转变的人；转换器；转炉；变频器；}
 *  productNum:1
 *  directorNum:2
 *  actorNum:2
 *  commentNum:?
 *  score:1~5循环
 *  hadPositiveComment:y/n循环
 *  最后一个的{directorNum:1
 *      actorNum:1}
 *  }
 *
 *  relation:{
 *      movieID:1 - personID{1 A,1+1 A,1+16 D,1+17 D}
 *      注：movieID:16-personID{16,32}
 *  }
 *
 *  product:{
 *      1~16:1~16:1~16三个ID
 *      type:1,1+8->type[0]以此类推
 *      format:default
 *      price:10~25
 *  }
 */


//        1. 按照时间进行查询及统计（例如XX年有多少电影，XX年XX月有多少电影，XX年XX季度有多少电影，周二新增多少电影等）
//
//        提供的查询模版：（返回前n行（n<=N,N=100[暂定]）数据，和查询到的总数据行数）
//
//
//        查询XXXX年发行的电影产品对应的电影，返回电影名称、评分，并按照评分由高到低排序。
//        查询XXXX年XX月发行的电影产品对应的电影，返回电影名称、评分，并按照评分由高到低排序。

//        查询XXXX年每个月发行的电影产品数量，返回月份和该月的电影产品数量，并按照月份先后排序。

//        查询每一年发行的电影产品数量，返回年份和该年的电影产品数量，并按照年份先后排序。
//
//        2. 按照电影名称进行查询及统计（例如 XX电影共有多少版本等）
//
//        提供的查询模版：（返回前n行（n<=N,N=100[暂定]）数据，和查询到的总数据行数）
//
//        查询电影《XXX》的电影产品，返回电影产品对应的 ASIN号(productID)、发布时间、格式、
//        类型、Amazon网址("http://amazon.dp.com/"+productID)，并按照发布时间先后排序。
//
//        查询电影《XXX》的基本信息，返回产品数量、导演数量、演员数量、评论数量、评分，
//
//        并按照评分由高到低排序。??????
//
//        查询电影《XXX》的参演演员，并按照首字母A-Z排序。
//
//        查询电影《XXX》的导演，并按照首字母A-Z排序。
//
//
//            3. 按照导演进行查询及统计（例如 XX导演共有多少电影等）
//
//        提供的查询模版：（返回前n行（n<=N,N=100[暂定]）数据，和查询到的总数据行数）
//
//        查询XX导演所执导的电影，返回电影名称、评分，并按照电影评分由高到低排序。
//        查询执导过至少X部电影的导演，返回导演名称、执导电影数量、执导电影平均评分，按照执导过的电影数量从高到低排序。

//        查询XX导演自导自演的电影，返回电影名称、评分，并按照电影评分由高到低排序。
//        查询自导自演电影数量较多的导演，返回导演名称、自导自演电影数量，并按照自导自演电影数量由高到低排序。


//        4. 按照演员进行查询及统计（例如 XX演员主演多少电影，XX演员参演多少电影等）
//
//        提供的查询模版：（返回前n行（n<=N,N=100[暂定]）数据，和查询到的总数据行数）
//        查询XX演员所参演的电影，返回电影名称、评分，并按照电影评分由高到低排序。
//        查询参演过至少X部电影的演员，返回演员名称、参演电影数量、参演电影平均评分，按照参演过的电影数量从高到低排序。

//        5. 按照演员和导演的关系进行查询及统计（例如经常合作的演员有哪些，经常合作的导演和演员有哪些）
//
//        提供的查询模版：（返回前n行（n<=N,N=100[暂定]）数据，和查询到的总数据行数）
//
//        默认出演电影次数大于执导电影次数的人为演员，出演电影次数小于等于执导电影次数的人为导演,和XX合作过的演员（该演员在这部电影的合作中即使充当导演，也计算在内）
//
//        查询和XX合作过的演员，返回演员名称、合作次数，并按照合作次数由多到少排序。
//        查询和XX合作过的导演，返回导演名称、合作次数，并按照合作次数由多到少排序。

//        查询经常合作的演员，返回演员组合{A，B}、合作次数，并按照合作次数由多到少排序。
//        查询经常合作的演员和导演，返回演员导演组合{A，B}、合作次数，并按照合作次数由多到少排序。

//        6. 按照电影类别进行查询及统计（例如 Action电影共有多少，Adventure电影共有多少等）
//
//        提供的查询模版：（返回前n行（n<=N,N=100[暂定]）数据，和查询到的总数据行数）
//
//        查询XXX类别的电影，返回电影名称、评分，并按照评分由高到低排序。

//        查询最受欢迎的电影类别，返回电影类别、电影类别包含的电影产品数量，并按照电影类别包含的电影产品数量由高到低排序。（数据治理，提前分析出电影类别包含的电影数量）
//        查询最小众的电影类别，返回电影类别、电影类别包含的电影产品数量，并按照电影类别包含的电影产品数量由低到高排序。

//        7. 按照用户评价进行查询及统计（例如用户评分3分以上的电影有哪些，用户评价中有正面评价的电影有哪些等）
//
//        提供的查询模版：（返回前n行（n<=N,N=100[暂定]）数据，和查询到的总数据行数）
//
//        查询用户评价中有正面评价的电影，返回电影名称、评分，并按照评分由高到低排序。
//
//        查询历史上最受欢迎的电影，返回电影名称、评分，并按照评分由高到低排序。
//
//
//        8. 按照上述条件的组合查询和统计
//
//        提供的查询模版：（返回前n行（n<=N,N=100[暂定]）数据，和查询到的总数据行数）
//
//        查询XXXX年以来XX执导的所有XXX类型的电影，返回电影名称、评分，并按照评分排序。
//        查询XXXX年最受欢迎的电影类别，返回电影类别、电影类别包含的电影产品数量，
//        并按照电影类别包含的电影产品数量由高到低排序。
