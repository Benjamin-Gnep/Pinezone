package com.sgxqwyh.pinezone.pojo;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "article", schema = "", catalog = "pinezone")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    //private int uid;
    @ManyToOne(fetch = FetchType.EAGER) //多对一，默认立即加载
    @JoinColumn(name = "uid")      //外键
    @Fetch(value = FetchMode.JOIN)   //加载关联关系的获取策略
    private UserEntity user;

    private String title;
    private String content;
    private Timestamp date;
    private long num;

    //private Integer cid;
    @ManyToOne(fetch = FetchType.EAGER) //多对一，默认立即加载
    @JoinColumn(name = "cid")      //外键
    @Fetch(value = FetchMode.JOIN)   //加载关联关系的获取策略
    private CategoryEntity category;

    private int state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    @Basic
//    @Column(name = "uid")
//    public int getUid() {
//        return uid;
//    }
//
//    public void setUid(int uid) {
//        this.uid = uid;
//    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    @Column(name = "num")
    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

//    @Basic
//    @Column(name = "cid")
//    public Integer getCid() {
//        return cid;
//    }
//
//    public void setCid(Integer cid) {
//        this.cid = cid;
//    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    @Basic
    @Column(name = "state")
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ArticleEntity that = (ArticleEntity) o;
//        return id == that.id &&
//                uid == that.uid &&
//                num == that.num &&
//                state == that.state &&
//                Objects.equals(title, that.title) &&
//                Objects.equals(content, that.content) &&
//                Objects.equals(date, that.date) &&
//                Objects.equals(cid, that.cid);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, uid, title, content, date, num, cid, state);
//    }
}
