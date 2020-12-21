package com.example.demo.Entity;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "year_month_release")
@IdClass(year_monthPK.class)
@Proxy(lazy = false)
//取消懒加载避免奇怪bug
public class year_month_release implements Serializable {
    @Id
    @Column(name = "releaseYear")
    private Integer releaseYear;

    @Id
    @Column(name = "releaseMonth")
    private Integer releaseMonth;

    @Column(name = "releaseNum")
    private Integer releaseNum;

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getReleaseMonth() {
        return releaseMonth;
    }

    public void setReleaseMonth(Integer releaseMonth) {
        this.releaseMonth = releaseMonth;
    }

    public Integer getReleaseNum() {
        return releaseNum;
    }

    public void setReleaseNum(Integer releaseNum) {
        this.releaseNum = releaseNum;
    }
    
    //    public year_month_release() {
//        super();
//    }
//
//    public year_month_release(Integer releaseYear, Integer releaseMonth, Integer releaseNum) {
//        super();
//        this.releaseYear = releaseYear;
//        this.releaseMonth = releaseMonth;
//        this.releaseNum = releaseNum;
//    }
}
