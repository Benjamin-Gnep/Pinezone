package com.sgxqwyh.pinezone.pojo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "read_record", schema = "", catalog = "pinezone")
public class ReadRecordEntity implements Comparable<ReadRecordEntity>{
    private long id;
    private int uid;
    private long aid;
    private Timestamp date;
    private int state;
    private String day;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uid")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "aid")
    public long getAid() {
        return aid;
    }

    public void setAid(long aid) {
        this.aid = aid;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "state")
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Transient
    public String getDay() {
        DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date(this.date.getTime()));
    }

    public void setDay() {
        DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
        this.day = df.format(new Date(this.date.getTime()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadRecordEntity that = (ReadRecordEntity) o;
        return id == that.id &&
                uid == that.uid &&
                aid == that.aid &&
                state == that.state &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uid, aid, date, state);
    }

    @Override
    public int compareTo(ReadRecordEntity r) {
        int i ;
        if(this.date.getTime()>r.date.getTime()) {
            i = 1;
        }
        else if(this.date.getTime()<r.date.getTime()) {
            i = -1;
        }
        else {
            i = 0;
        }
        if(i == 0) {
            if (r.id > this.id)
                i = 1;
            else
                i = -1;
        }
        return -i;
    }
}

