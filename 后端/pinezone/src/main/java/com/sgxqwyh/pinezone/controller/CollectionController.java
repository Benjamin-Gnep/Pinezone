package com.sgxqwyh.pinezone.controller;

import com.sgxqwyh.pinezone.pojo.CollectionEntity;
import com.sgxqwyh.pinezone.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    //锦浩
    @PostMapping(value = "/star")
    public CollectionEntity addCollection(CollectionEntity collectionEntity) {
        return collectionService.addCollection(collectionEntity);
    }

    @DeleteMapping(value = "/star")
    public int deleteCollection(@RequestParam("uid") Integer uid, @RequestParam("aid") Long aid) {
        return collectionService.deleteCollection(uid, aid);
    }
}
