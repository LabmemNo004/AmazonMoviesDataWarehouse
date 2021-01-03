package com.dwh.hive.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("release_time")
public class ReleaseTime {
    @TableField("timeID")
    private Integer timeID;

    @TableField("releaseYear")
    private Integer releaseYear;

    @TableField("releaseMonth")
    private Integer releaseMonth;

    @TableField("releaseDay")
    private Integer releaseDay;

    @TableField("dailyReleaseNum")
    private Integer dailyReleaseNum;
}
