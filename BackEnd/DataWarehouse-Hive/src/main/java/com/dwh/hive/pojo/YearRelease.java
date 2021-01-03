package com.dwh.hive.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("year_release")
public class YearRelease {
    @TableField("releaseYear")
    private Integer releaseYear;


    @TableField("releaseNum")
    private Integer releaseNum;
}
