package com.sgxqwyh.pinezone.service;

import com.sgxqwyh.pinezone.pojo.LikeEntity;

public interface LikeService {

    //锦浩
    LikeEntity addLike(LikeEntity likeEntity);
    int deleteLike(Integer uid, Long aid);
}
