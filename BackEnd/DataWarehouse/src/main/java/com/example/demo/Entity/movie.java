package com.example.demo.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "movie")
public class movie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //配置主键的生成策略
    @Column(name="movieID")
    private Integer movieID;

    @Column(name="title")
    private String title;

    @Column(name="productNum")
    private Integer productNum;

    @Column(name="directorNum")
    private Integer directorNum;

    @Column(name="actorNum")
    private Integer actorNum;

    @Column(name="score")
    private float score;

    @Column(name="hasPositiveComment")
    private char hasPositiveComment;

    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Integer getDirectorNum() {
        return directorNum;
    }

    public void setDirectorNum(Integer directorNum) {
        this.directorNum = directorNum;
    }

    public Integer getActorNum() {
        return actorNum;
    }

    public void setActorNum(Integer actorNum) {
        this.actorNum = actorNum;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public char getHasPositiveComment() {
        return hasPositiveComment;
    }

    public void setHasPositiveComment(char hasPositiveComment) {
        this.hasPositiveComment = hasPositiveComment;
    }
}
