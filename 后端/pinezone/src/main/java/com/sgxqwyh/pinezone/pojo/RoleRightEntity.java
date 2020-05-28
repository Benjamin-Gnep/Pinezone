package com.sgxqwyh.pinezone.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "role_right", schema = "", catalog = "pinezone")
public class RoleRightEntity {
    private int id;
    private int roleId;
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
    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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
        RoleRightEntity that = (RoleRightEntity) o;
        return id == that.id &&
                roleId == that.roleId &&
                rightId == that.rightId &&
                rightType == that.rightType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, rightId, rightType);
    }
}
