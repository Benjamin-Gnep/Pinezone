package com.sgxqwyh.pinezone.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_picture", schema = "", catalog = "pinezone")
public class UserPictureEntity {
    private long id;
    private String path;
    private int state;

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
    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "state")
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPictureEntity that = (UserPictureEntity) o;
        return id == that.id &&
                state == that.state &&
                Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path, state);
    }
}
