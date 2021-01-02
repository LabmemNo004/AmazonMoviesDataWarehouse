package com.example.demo.Entity;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "cooperation")
@IdClass(cooperationPK.class)
@Proxy(lazy = false)
//取消懒加载避免奇怪bug
public class cooperation implements Serializable {
    @Id
    @Column(name = "leftPersonID")
    private Integer leftPersonID;

    @Id
    @Column(name = "rightPersonID")
    private Integer rightPersonID;

    @Column(name = "cooperateNum")
    private Integer cooperateNum;

    @Column(name = "leftPersonType")
    private Character leftPersonType;

    @Column(name = "rightPersonType")
    private Character rightPersonType;

    @Column(name = "leftPersonName")
    private String leftPersonName;

    @Column(name = "rightPersonName")
    private String rightPersonName;

    public Character getLeftPersonType() {
        return leftPersonType;
    }

    public void setLeftPersonType(Character leftPersonType) {
        this.leftPersonType = leftPersonType;
    }

    public Character getRightPersonType() {
        return rightPersonType;
    }

    public void setRightPersonType(Character rightPersonType) {
        this.rightPersonType = rightPersonType;
    }

    public String getLeftPersonName() {
        return leftPersonName;
    }

    public void setLeftPersonName(String leftPersonName) {
        this.leftPersonName = leftPersonName;
    }

    public String getRightPersonName() {
        return rightPersonName;
    }

    public void setRightPersonName(String rightPersonName) {
        this.rightPersonName = rightPersonName;
    }

    public cooperation() {
    }

    public cooperation(Integer leftPersonID, Integer rightPersonID,
                       Integer cooperateNum, Character leftPersonType,
                       Character rightPersonType, String leftPersonName,
                       String rightPersonName) {
        this.leftPersonID = leftPersonID;
        this.rightPersonID = rightPersonID;
        this.cooperateNum = cooperateNum;
        this.leftPersonType = leftPersonType;
        this.rightPersonType = rightPersonType;
        this.leftPersonName = leftPersonName;
        this.rightPersonName = rightPersonName;
    }

    public Integer getLeftPersonID() {
        return leftPersonID;
    }

    public void setLeftPersonID(Integer leftPersonID) {
        this.leftPersonID = leftPersonID;
    }

    public Integer getRightPersonID() {
        return rightPersonID;
    }

    public void setRightPersonID(Integer rightPersonID) {
        this.rightPersonID = rightPersonID;
    }

    public Integer getCooperateNum() {
        return cooperateNum;
    }

    public void setCooperateNum(Integer cooperateNum) {
        this.cooperateNum = cooperateNum;
    }
}
