package com.example.demo.Entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "release_time")
public class release_time implements Serializable {
    @Id
    @Column(name = "timeID")
    private Integer timeID;

    @Column(name = "releaseYear")
    private Integer releaseYear;

    @Column(name = "releaseMonth")
    private Integer releaseMonth;

    @Column(name = "releaseDay")
    private Integer releaseDay;

    @Column(name = "dailyReleaseNum")
    private Integer dailyReleaseNum;

    public Integer getTimeID() {
        return timeID;
    }

    public void setTimeID(Integer timeID) {
        this.timeID = timeID;
    }

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

    public Integer getReleaseDay() {
        return releaseDay;
    }

    public void setReleaseDay(Integer releaseDay) {
        this.releaseDay = releaseDay;
    }

    public Integer getDailyReleaseNum() {
        return dailyReleaseNum;
    }

    public void setDailyReleaseNum(Integer dailyReleaseNum) {
        this.dailyReleaseNum = dailyReleaseNum;
    }
}
