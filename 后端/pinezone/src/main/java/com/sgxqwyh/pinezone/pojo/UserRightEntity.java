package com.sgxqwyh.pinezone.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_right", schema = "", catalog = "pinezone")
public class UserRightEntity {
    private int id;
    private int userId;
    private int rightId;
    private int rightType;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "right_id")
    public int getRightId() {
        return rightId;
    }

    public void setRightId(int rightId) {
        this.rightId = rightId;
    }

    @Basic
    @Column(name = "right_type")
    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRightEntity that = (UserRightEntity) o;
        return id == that.id &&
                userId == that.userId &&
                rightId == that.rightId &&
                rightType == that.rightType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, rightId, rightType);
    }
}
