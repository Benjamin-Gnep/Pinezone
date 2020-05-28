package com.sgxqwyh.pinezone.dao;

import com.sgxqwyh.pinezone.pojo.CollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionDAO extends JpaRepository<CollectionEntity, Long> {

    //锦浩
    CollectionEntity findByUidAndAid(Integer uid, Long aid);
    CollectionEntity findByUidAndAidAndState(Integer uid, Long aid, Integer state);
    List<CollectionEntity> findByUidAndState(Integer uid, int i);

    //鹏飞
    // 根据用户id返回收藏的文章
    public List<CollectionEntity> findByUid(Integer Uid);

    Integer countByAidAndState(long id, int i);


}
