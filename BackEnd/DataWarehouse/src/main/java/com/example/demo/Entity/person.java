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

    @Column(name="actorAndDirectorNum") 
    private Integer actorAndDirectorNum;

    @Column(name="joinNum") 
    private Integer joinNum;

    @Column(name="actorOrDirector") 
    private char actorOrDirector;

    @Column(name="actorAverageScore") 
    private Float actorAverageScore;

    @Column(name="directorAverageScore") 
    private Float directorAverageScore;

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
        return actorAndDirectorNum;
    }

    public void setActorAndDirectorNum(Integer actorAndDirectorNum) {
        this.actorAndDirectorNum = actorAndDirectorNum;
    }

    public Integer getJoinNum() {
        return joinNum;
    }

    public void setJoinNum(Integer joinNum) {
        this.joinNum = joinNum;
    }

    public char getActorOrDirector() {
        return actorOrDirector;
    }

    public void setActorOrDirector(char actorOrDirector) {
        this.actorOrDirector = actorOrDirector;
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
