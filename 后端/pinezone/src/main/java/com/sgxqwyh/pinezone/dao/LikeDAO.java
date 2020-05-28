package com.sgxqwyh.pinezone.dao;

import com.sgxqwyh.pinezone.pojo.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeDAO extends JpaRepository<LikeEntity, Long> {

    //锦浩
    Integer countByAid(long id);
    Integer countByAidAndState(long id, int i);
    LikeEntity findByUidAndAid(int uid, long aid);
    LikeEntity findByUidAndAidAndState(Integer uid, Long aid, int i);

    //鹏飞
    //返回点赞文章列表
    public List<LikeEntity> findByUid(Integer Uid);


}
