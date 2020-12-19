package com.example.demo.Entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "cooperation")
public class cooperation implements Serializable {
    @Id
    @Column(name = "leftPersonID")
    private int leftPersonID;

    @Id
    @Column(name = "rightPersonID")
    private int rightPersonID;

    @Column(name = "cooperateNum")
    private int cooperateNum;

    public int getLeftPersonID() {
        return leftPersonID;
    }

    public void setLeftPersonID(int leftPersonID) {
        this.leftPersonID = leftPersonID;
    }

    public int getRightPersonID() {
        return rightPersonID;
    }

    public void setRightPersonID(int rightPersonID) {
        this.rightPersonID = rightPersonID;
    }

    public int getCooperateNum() {
        return cooperateNum;
    }

    public void setCooperateNum(int cooperateNum) {
        this.cooperateNum = cooperateNum;
    }
}
