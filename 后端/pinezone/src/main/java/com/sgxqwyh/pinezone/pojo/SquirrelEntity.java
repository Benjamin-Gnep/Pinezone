package com.sgxqwyh.pinezone.pojo;

import javax.persistence.*;

@Entity
@Table(name = "squirrel", schema = "", catalog = "pinezone")
public class SquirrelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private int uid;
    private String name;
    private int hp;
    private int pinecone;
    private double food;
    private double achievement;
    private double companion;
    private int state;

    public long getId() {
        return id;
    }

    public int getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getPinecone() {
        return pinecone;
    }

    public double getFood() {
        return food;
    }

    public double getAchievement() {
        return achievement;
    }

    public double getCompanion() {
        return companion;
    }

    public int getState() {
        return state;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setPinecone(int pinecone) {
        this.pinecone = pinecone;
    }

    public void setFood(double food) {
        this.food = food;
    }

    public void setAchievement(double achievement) {
        this.achievement = achievement;
    }

    public void setCompanion(double companion) {
        this.companion = companion;
    }

    public void setState(int state) {
        this.state = state;
    }
}


