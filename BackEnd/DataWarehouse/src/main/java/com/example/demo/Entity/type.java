package com.example.demo.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "type")
public class type {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //配置主键的生成策略
    @Column(name="type") //指定和表中cust_id字段的映射关系
    private String type;

    @Column(name="movieNum")
    private int movieNum;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMovieNum() {
        return movieNum;
    }

    public void setMovieNum(int movieNum) {
        this.movieNum = movieNum;
    }
}
