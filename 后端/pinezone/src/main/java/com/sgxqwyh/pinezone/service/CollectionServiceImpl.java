package com.sgxqwyh.pinezone.service;

import com.sgxqwyh.pinezone.dao.CollectionDAO;
import com.sgxqwyh.pinezone.pojo.CollectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service("collectionService")
public class CollectionServiceImpl implements CollectionService{

    @Autowired
    private CollectionDAO collectionDAO;

    //锦浩
    public CollectionEntity addCollection(CollectionEntity collectionEntity) {
        if (collectionDAO.findByUidAndAid(collectionEntity.getUid(), collectionEntity.getAid()) != null) {
            collectionEntity.setId(collectionDAO.findByUidAndAid(collectionEntity.getUid(), collectionEntity.getAid()).getId());
        }
        Timestamp timestamp = new Timestamp(new Date().getTime());
        collectionEntity.setDate(timestamp);
        collectionEntity.setState(1);
        return collectionDAO.save(collectionEntity);
    }

    public int deleteCollection(Integer uid, Long aid) {
//        if (collectionDAO.findById(id).isPresent()) {
//            CollectionEntity collectionEntity = collectionDAO.findById(id).get();
//            collectionEntity.setState(0);
//            collectionDAO.save(collectionEntity);
//            return 1;
//        } else {
//            return 0;
//        }
        CollectionEntity collectionEntity = collectionDAO.findByUidAndAid(uid, aid);
        collectionEntity.setState(0);
        collectionDAO.save(collectionEntity);
        return 1;
    }
}
