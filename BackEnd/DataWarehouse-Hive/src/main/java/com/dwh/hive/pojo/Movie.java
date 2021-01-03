package com.dwh.hive.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("movie")
public class Movie {
    @TableField("movieID")
    private Integer movieID;

    @TableField("title")
    private String title;

    @TableField("productNum")
    private Integer productNum;

    @TableField("directorNum")
    private Integer directorNum;

    @TableField("actorNum")
    private Integer actorNum;

    @TableField("commentNum")
    private Integer commentNum;

    @TableField("score")
    private Float score;

    @TableField("hadPositiveComment")
    private Character hasPositiveComment;

    public Movie() {}

    public Movie(Integer movieID, String title, Integer productNum,
                 Integer directorNum, Integer actorNum,
                 Integer commentNum, Float score,
                 Character hasPositiveComment) {
        this.movieID = movieID;
        this.title = title;
        this.productNum = productNum;
        this.directorNum = directorNum;
        this.actorNum = actorNum;
        this.commentNum = commentNum;
        this.score = score;
        this.hasPositiveComment = hasPositiveComment;
    }
}
