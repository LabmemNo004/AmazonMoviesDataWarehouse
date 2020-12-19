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
    private int movieID;

    @Column(name="title")
    private String title;

    @Column(name="productNum")
    private int productNum;

    @Column(name="directorNum")
    private int directorNum;

    @Column(name="actorNum")
    private int actorNum;

    @Column(name="score")
    private float score;

    @Column(name="hasPositiveComment")
    private char hasPositiveComment;

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public int getDirectorNum() {
        return directorNum;
    }

    public void setDirectorNum(int directorNum) {
        this.directorNum = directorNum;
    }

    public int getActorNum() {
        return actorNum;
    }

    public void setActorNum(int actorNum) {
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
