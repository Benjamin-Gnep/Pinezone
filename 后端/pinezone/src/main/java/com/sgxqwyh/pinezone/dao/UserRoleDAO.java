package com.sgxqwyh.pinezone.dao;

import com.sgxqwyh.pinezone.pojo.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleDAO extends JpaRepository<UserRoleEntity,Integer>{
    //根据用户的角色获取用户角色列表
    public List<UserRoleEntity> findByRoleId(Integer RoleId);
    //龚俊鹏
    UserRoleEntity findByUserId(int id);
}

