package com.sgxqwyh.pinezone.service;

import com.sgxqwyh.pinezone.dao.LikeDAO;
import com.sgxqwyh.pinezone.pojo.LikeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service("likeService")
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeDAO likeDAO;


    //锦浩
    public LikeEntity addLike(LikeEntity likeEntity) {
        if (likeDAO.findByUidAndAid(likeEntity.getUid(), likeEntity.getAid()) != null) {
            likeEntity.setId(likeDAO.findByUidAndAid(likeEntity.getUid(), likeEntity.getAid()).getId());
        }
        Timestamp timestamp = new Timestamp(new Date().getTime());
        likeEntity.setDate(timestamp);
        likeEntity.setState(1);
        return likeDAO.save(likeEntity);
    }

    public int deleteLike(Integer uid, Long aid) {
        if (likeDAO.findByUidAndAid(uid, aid)!=null) {
            LikeEntity likeEntity = likeDAO.findByUidAndAid(uid, aid);
            likeEntity.setState(0);
            likeDAO.save(likeEntity);
            return 1;
        } else {
            return 0;
        }
    }

}
