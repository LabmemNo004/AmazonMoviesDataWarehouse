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
