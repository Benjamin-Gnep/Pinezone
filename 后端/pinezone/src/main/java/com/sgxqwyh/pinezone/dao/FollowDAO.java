package com.sgxqwyh.pinezone.dao;

import com.sgxqwyh.pinezone.pojo.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowDAO extends JpaRepository<FollowEntity,Integer> {

    //俊鹏
    public List<FollowEntity> findByUid(int Uid);
    void deleteByUid(int Uid);

    //鹏飞
    //根据用户id查询关注列表
    public List<FollowEntity> findByUid(Integer Uid);
    //根据被关注用户id返回列表
    public List<FollowEntity> findByUuid(Integer Uuid);
}
