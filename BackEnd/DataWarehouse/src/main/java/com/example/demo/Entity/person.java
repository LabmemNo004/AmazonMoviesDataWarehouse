package com.example.demo.Entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "person")
public class person
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //配置主键的生成策略
    @Column(name="personID") 
    private Integer personID;

    @Column(name="name") 
    private String name;

    @Column(name="actorNum") 
    private Integer actorNum;

    @Column(name="directorNum") 
    private Integer directorNum;

    @Column(name= "actorTogetherDirectorNum")
    private Integer actorTogetherDirectorNum;

    @Column(name="joinNum") 
    private Integer joinNum;

    @Column(name= "actorEitherDirector") 
    private Character actorEitherDirector;

    @Column(name="actorAverageScore") 
    private Float actorAverageScore;

    @Column(name="directorAverageScore") 
    private Float directorAverageScore;

    public person(Integer personID, String name, Integer actorNum,
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

    public person() {
    }

    public Integer getPersonID() {
        return personID;
    }

    public void setPersonID(Integer personID) {
        this.personID = personID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getActorNum() {
        return actorNum;
    }

    public void setActorNum(Integer actorNum) {
        this.actorNum = actorNum;
    }

    public Integer getDirectorNum() {
        return directorNum;
    }

    public void setDirectorNum(Integer directorNum) {
        this.directorNum = directorNum;
    }

    public Integer getActorAndDirectorNum() {
        return actorTogetherDirectorNum;
    }

    public void setActorAndDirectorNum(Integer actorTogetherDirectorNum) {
        this.actorTogetherDirectorNum = actorTogetherDirectorNum;
    }

    public Integer getJoinNum() {
        return joinNum;
    }

    public void setJoinNum(Integer joinNum) {
        this.joinNum = joinNum;
    }

    public Character getActorOrDirector() {
        return actorEitherDirector;
    }

    public void setActorOrDirector(Character actorEitherDirector) {
        this.actorEitherDirector = actorEitherDirector;
    }

    public Float getActorAverageScore() {
        return actorAverageScore;
    }

    public void setActorAverageScore(Float actorAverageScore) {
        this.actorAverageScore = actorAverageScore;
    }

    public Float getDirectorAverageScore() {
        return directorAverageScore;
    }

    public void setDirectorAverageScore(Float directorAverageScore) {
        this.directorAverageScore = directorAverageScore;
    }
}
