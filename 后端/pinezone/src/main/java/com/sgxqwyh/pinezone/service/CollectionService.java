package com.sgxqwyh.pinezone.service;

import com.sgxqwyh.pinezone.pojo.CollectionEntity;

public interface CollectionService {

    //锦浩
    CollectionEntity addCollection(CollectionEntity collectionEntity);
    int deleteCollection(Integer uid, Long aid);
}
