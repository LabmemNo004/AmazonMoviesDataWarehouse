package com.example.demo.Entity;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "relation")
@IdClass(relation.class)
@Proxy(lazy = false)
//取消懒加载避免奇怪bug
public class relation implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //配置主键的生成策略
    @Column(name="personID") //指定和表中cust_id字段的映射关系
    private Integer personID;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //配置主键的生成策略
    @Column(name="movieID")
    private String movieID;

    @Column(name="isActor")
    private String isActor;

    @Column(name="isDirector")
    private String isDirector;

    public Integer getPersonID() {
        return personID;
    }

    public void setPersonID(Integer personID) {
        this.personID = personID;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public String getIsActor() {
        return isActor;
    }

    public void setIsActor(String isActor) {
        this.isActor = isActor;
    }

    public String getIsDirector() {
        return isDirector;
    }

    public void setIsDirector(String isDirector) {
        this.isDirector = isDirector;
    }
}
