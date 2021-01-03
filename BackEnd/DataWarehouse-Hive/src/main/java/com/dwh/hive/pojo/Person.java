package com.dwh.hive.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("person")
public class Person {
    @TableField("personID")
    private Integer personID;

    @TableField("name")
    private String name;

    @TableField("actorNum")
    private Integer actorNum;

    @TableField("directorNum")
    private Integer directorNum;

    @TableField("actorTogetherDirectorNum")
    private Integer actorTogetherDirectorNum;

    @TableField("joinNum")
    private Integer joinNum;

    @TableField("actorEitherDirector")
    private Character actorEitherDirector;

    @TableField("actorAverageScore")
    private Float actorAverageScore;

    @TableField("directorAverageScore")
    private Float directorAverageScore;

    public Person(Integer personID, String name, Integer actorNum,
                  Integer directorNum, Integer actorTogetherDirectorNum,
                  Integer joinNum, Character actorEitherDirector,
                  Float actorAverageScore, Float directorAverageScore) {
        this.personID = personID;
        this.name = name;
        this.actorNum = actorNum;
        this.directorNum = directorNum;
        this.actorTogetherDirectorNum = actorTogetherDirectorNum;
        this.joinNum = joinNum;
        this.actorEitherDirector = actorEitherDirector;
        this.actorAverageScore = actorAverageScore;
        this.directorAverageScore = directorAverageScore;
    }

    public Person() {}
}
