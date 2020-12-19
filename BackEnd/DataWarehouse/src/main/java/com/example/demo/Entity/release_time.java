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
    private int timeID;

    @Column(name = "releaseYear")
    private int releaseYear;

    @Column(name = "releaseMonth")
    private int releaseMonth;

    @Column(name = "releaseDay")
    private int releaseDay;

    @Column(name = "dailyReleaseNum")
    private int dailyReleaseNum;

    public int getTimeID() {
        return timeID;
    }

    public void setTimeID(int timeID) {
        this.timeID = timeID;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getReleaseMonth() {
        return releaseMonth;
    }

    public void setReleaseMonth(int releaseMonth) {
        this.releaseMonth = releaseMonth;
    }

    public int getReleaseDay() {
        return releaseDay;
    }

    public void setReleaseDay(int releaseDay) {
        this.releaseDay = releaseDay;
    }

    public int getDailyReleaseNum() {
        return dailyReleaseNum;
    }

    public void setDailyReleaseNum(int dailyReleaseNum) {
        this.dailyReleaseNum = dailyReleaseNum;
    }
}
