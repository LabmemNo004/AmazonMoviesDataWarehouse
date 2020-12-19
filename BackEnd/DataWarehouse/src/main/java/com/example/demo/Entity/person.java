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
    private int personID;

    @Column(name="name") 
    private String name;

    @Column(name="actorNum") 
    private int actorNum;

    @Column(name="directorNum") 
    private int directorNum;

    @Column(name="actorAndDirectorNum") 
    private int actorAndDirectorNum;

    @Column(name="joinNum") 
    private int joinNum;

    @Column(name="actorOrDirector") 
    private char actorOrDirector;

    @Column(name="actorAverageScore") 
    private float actorAverageScore;

    @Column(name="directorAverageScore") 
    private float directorAverageScore;

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActorNum() {
        return actorNum;
    }

    public void setActorNum(int actorNum) {
        this.actorNum = actorNum;
    }

    public int getDirectorNum() {
        return directorNum;
    }

    public void setDirectorNum(int directorNum) {
        this.directorNum = directorNum;
    }

    public int getActorAndDirectorNum() {
        return actorAndDirectorNum;
    }

    public void setActorAndDirectorNum(int actorAndDirectorNum) {
        this.actorAndDirectorNum = actorAndDirectorNum;
    }

    public int getJoinNum() {
        return joinNum;
    }

    public void setJoinNum(int joinNum) {
        this.joinNum = joinNum;
    }

    public char getActorOrDirector() {
        return actorOrDirector;
    }

    public void setActorOrDirector(char actorOrDirector) {
        this.actorOrDirector = actorOrDirector;
    }

    public float getActorAverageScore() {
        return actorAverageScore;
    }

    public void setActorAverageScore(float actorAverageScore) {
        this.actorAverageScore = actorAverageScore;
    }

    public float getDirectorAverageScore() {
        return directorAverageScore;
    }

    public void setDirectorAverageScore(float directorAverageScore) {
        this.directorAverageScore = directorAverageScore;
    }
}
