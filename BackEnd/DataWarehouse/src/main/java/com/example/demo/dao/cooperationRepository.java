package com.example.demo.dao;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.Entity.cooperation;
import com.example.demo.Entity.cooperationPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface cooperationRepository extends JpaRepository<cooperation, cooperationPK>,
        JpaSpecificationExecutor<cooperation> {

    /**
     * 对于with使用的临时表一定要在from中语句有体现有用到。
     * @param name
     * @return
     */

    @Query(value = "with summarys as ( select s.personID from person s where s.name=?1) " +
            "select s.name,c.cooperateNum as Num " +
            "from person s join cooperation c on s.personID = c.leftPersonID" +
            " join summarys on c.rightPersonID=summarys.personID" +
            " where s.actorEitherDirector='D'" +
            "UNION all " +
            "select s.name,c.cooperateNum as Num " +
            "from person s join cooperation c on s.personID = c.rightPersonID " +
            "join summarys on c.leftPersonID=summarys.personID " +
            "where s.actorEitherDirector='D' " +
            "order by Num DESC;",nativeQuery = true)
    List<Map<String,String>> getCoDirector(String name);


    @Query(value = "with summarys as ( select s.personID from person s where s.name=?1) " +
            "select s.name,c.cooperateNum as Num " +
            "from person s join cooperation c on s.personID = c.leftPersonID" +
            " join summarys on c.rightPersonID=summarys.personID" +
            " where s.actorEitherDirector='A'" +
            "UNION all " +
            "select s.name,c.cooperateNum as Num " +
            "from person s join cooperation c on s.personID = c.rightPersonID " +
            "join summarys on c.leftPersonID=summarys.personID " +
            "where s.actorEitherDirector='A' " +
            "order by Num DESC;",nativeQuery = true)
    List<Map<String,String>> getCoActor(String name);





    @Query(value = "select * from cooperation c" +
            " where c.leftPersonType='A' AND c.rightPersonType='A'" +
            " AND c.cooperateNum>=5" +
            " ORDER BY c.cooperateNum DESC",nativeQuery = true)
    List<cooperation> getCoopratorActor();

    @Query(value = "select * from cooperation c" +
            " where c.leftPersonType='D' AND c.rightPersonType='D'" +
            " AND c.cooperateNum>=2" +
            " ORDER BY c.cooperateNum DESC",nativeQuery = true)
    List<cooperation> getCoopratorDirector();

    @Query(value = "select * from cooperation c" +
            " where c.leftPersonType='A' AND c.rightPersonType='D'" +
            " AND c.cooperateNum>=5" +
            " ORDER BY c.cooperateNum DESC",nativeQuery = true)
    List<cooperation> getCoopratorAD();

    @Query(value = "select * from cooperation c" +
            " where " +
            "c.leftPersonType='D' AND c.rightPersonType='A'" +
            " AND c.cooperateNum>=5" +
            " ORDER BY c.cooperateNum DESC",nativeQuery = true)
    List<cooperation> getCoopratorDA();


}
