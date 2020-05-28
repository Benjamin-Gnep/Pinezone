package com.sgxqwyh.pinezone.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "article_picture", schema = "", catalog = "pinezone")
public class ArticlePictureEntity {
    private long id;
    private long aid;
    private String path;

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
    @Column(name = "aid")
    public long getAid() {
        return aid;
    }

    public void setAid(long aid) {
        this.aid = aid;
    }

    @Basic
    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticlePictureEntity that = (ArticlePictureEntity) o;
        return id == that.id &&
                aid == that.aid &&
                Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, aid, path);
    }
}
