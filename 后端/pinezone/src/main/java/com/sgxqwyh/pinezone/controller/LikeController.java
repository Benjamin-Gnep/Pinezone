package com.sgxqwyh.pinezone.controller;

import com.sgxqwyh.pinezone.pojo.LikeEntity;
import com.sgxqwyh.pinezone.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1")
public class LikeController {

    @Autowired
    private LikeService likeService;

    //锦浩
    @PostMapping(value = "like")
    public LikeEntity addLike(LikeEntity likeEntity) {
        return likeService.addLike(likeEntity);
    }

    @DeleteMapping(value = "like")
    public int deleteLike(@RequestParam("uid") Integer uid, @RequestParam("aid") Long aid) {
        return likeService.deleteLike(uid, aid);
    }
}
