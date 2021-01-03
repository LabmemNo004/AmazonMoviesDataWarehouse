package com.dwh.hive.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dwh.hive.pojo.Cooperation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface CooperationDAO extends BaseMapper<Cooperation> {
    @Select(value = "with summarys as ( select s.personID from person s where s.name=#{param1}) " +
            "select s.name,c.cooperateNum as Num " +
            "from person s join cooperation c on s.personID = c.leftPersonID" +
            " join summarys on c.rightPersonID=summarys.personID" +
            " where s.actorEitherDirector='D'" +
            "UNION all " +
            "select s.name,c.cooperateNum as Num " +
            "from person s join cooperation c on s.personID = c.rightPersonID " +
            "join summarys on c.leftPersonID=summarys.personID " +
            "where s.actorEitherDirector='D' " +
            "order by Num DESC;") //,nativeQuery = true
    List<Map<String,String>> getCoDirector(@Param("param1") String name);

    @Select(value = "with summarys as ( select s.personID from person s where s.name=#{param1}) " +
            "select s.name,c.cooperateNum as Num " +
            "from person s join cooperation c on s.personID = c.leftPersonID" +
            " join summarys on c.rightPersonID=summarys.personID" +
            " where s.actorEitherDirector='A'" +
            "UNION all " +
            "select s.name,c.cooperateNum as Num " +
            "from person s join cooperation c on s.personID = c.rightPersonID " +
            "join summarys on c.leftPersonID=summarys.personID " +
            "where s.actorEitherDirector='A' " +
            "order by Num DESC;")
    List<Map<String,String>> getCoActor(@Param("param1") String name);

    @Select(value = "select " +
            "    leftPersonID as leftPersonID," +
            "    rightPersonID as rightPersonID," +
            "    cooperateNum as cooperateNum," +
            "    leftPersonType as leftPersonType," +
            "    rightPersonType as rightPersonType," +
            "    leftPersonName as leftPersonName," +
            "    rightPersonName as rightPersonName" +
            " from cooperation c" +
            " where c.leftPersonType='A' AND c.rightPersonType='A'" +
            " AND c.cooperateNum>=5" +
            " ORDER BY c.cooperateNum DESC")
    List<Cooperation> getCoopratorActor();

    @Select(value = "select " +
            "    leftPersonID as leftPersonID," +
            "    rightPersonID as rightPersonID," +
            "    cooperateNum as cooperateNum," +
            "    leftPersonType as leftPersonType," +
            "    rightPersonType as rightPersonType," +
            "    leftPersonName as leftPersonName," +
            "    rightPersonName as rightPersonName" +
            " from cooperation c" +
            " where c.leftPersonType='D' AND c.rightPersonType='D'" +
            " AND c.cooperateNum>=2" +
            " ORDER BY c.cooperateNum DESC")
    List<Cooperation> getCoopratorDirector();

    @Select(value = "select " +
            "    leftPersonID as leftPersonID," +
            "    rightPersonID as rightPersonID," +
            "    cooperateNum as cooperateNum," +
            "    leftPersonType as leftPersonType," +
            "    rightPersonType as rightPersonType," +
            "    leftPersonName as leftPersonName," +
            "    rightPersonName as rightPersonName" +
            " from cooperation c" +
            " where c.leftPersonType='A' AND c.rightPersonType='D'" +
            " AND c.cooperateNum>=5" +
            " ORDER BY c.cooperateNum DESC")
    List<Cooperation> getCoopratorAD();

    @Select(value = "select " +
            "    leftPersonID as leftPersonID," +
            "    rightPersonID as rightPersonID," +
            "    cooperateNum as cooperateNum," +
            "    leftPersonType as leftPersonType," +
            "    rightPersonType as rightPersonType," +
            "    leftPersonName as leftPersonName," +
            "    rightPersonName as rightPersonName" +
            " from cooperation c" +
            " where " +
            "c.leftPersonType='D' AND c.rightPersonType='A'" +
            " AND c.cooperateNum>=5" +
            " ORDER BY c.cooperateNum DESC")
    List<Cooperation> getCoopratorDA();
}
