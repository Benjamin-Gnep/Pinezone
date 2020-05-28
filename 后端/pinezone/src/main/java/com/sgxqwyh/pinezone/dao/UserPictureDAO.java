package com.sgxqwyh.pinezone.dao;

import com.sgxqwyh.pinezone.pojo.UserPictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPictureDAO extends JpaRepository<UserPictureEntity,Long> {
    //通过路径和是否在用查询用户现在的头像
    public UserPictureEntity findByIdAndState(Long Pid, Integer State);
}
