package com.sgxqwyh.pinezone.pojo;

import javax.persistence.*;

@Entity
@Table(name = "place", schema = "", catalog = "pinezone")
public class PlaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String name;
    private int time;
    private double achievement;
    private double companion;
    private int state;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(int time) {
        this.time = time;
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
