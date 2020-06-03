package com.sgxqwyh.pinezone.controller;

import com.alibaba.fastjson.JSONObject;
import com.sgxqwyh.pinezone.dao.PlaceDAO;
import com.sgxqwyh.pinezone.dao.SquirrelDAO;
import com.sgxqwyh.pinezone.pojo.PlaceEntity;
import com.sgxqwyh.pinezone.pojo.SquirrelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/v1")
public class SquirrelController {

    @Autowired
    private SquirrelDAO squirrelDAO;
    @Autowired
    private PlaceDAO placeDAO;

    @GetMapping(value = "/squirrel")
    public SquirrelEntity getSquirrel(@RequestParam("uid") Integer uid) {
        return squirrelDAO.findByUid(uid);
    }

    @PostMapping(value = "/squirrel/hp")
    public SquirrelEntity setHp(@RequestParam("uid") Integer uid, @RequestParam("hp") Integer hp) {
        SquirrelEntity squirrelEntity = squirrelDAO.findByUid(uid);
        squirrelEntity.setHp(hp);
        return squirrelDAO.save(squirrelEntity);
    }

    @PostMapping(value = "/squirrel/pinecone")
    public SquirrelEntity setPinecone(@RequestParam("uid") Integer uid, @RequestParam("pinecone") Integer pinecone) {
        SquirrelEntity squirrelEntity = squirrelDAO.findByUid(uid);
        squirrelEntity.setPinecone(pinecone);
        return squirrelDAO.save(squirrelEntity);
    }

    @PostMapping(value = "/squirrel/food")
    public SquirrelEntity setFood(@RequestParam("uid") Integer uid, @RequestParam("food") Float food) {
        SquirrelEntity squirrelEntity = squirrelDAO.findByUid(uid);
        squirrelEntity.setFood(food);
        return squirrelDAO.save(squirrelEntity);
    }

    @PostMapping(value = "/squirrel/achievement")
    public SquirrelEntity setAchievement(@RequestParam("uid") Integer uid, @RequestParam("achievement") Float achievement) {
        SquirrelEntity squirrelEntity = squirrelDAO.findByUid(uid);
        squirrelEntity.setAchievement(achievement);
        return squirrelDAO.save(squirrelEntity);
    }

    @PostMapping(value = "/squirrel/companion")
    public SquirrelEntity setCompanion(@RequestParam("uid") Integer uid, @RequestParam("companion") Float companion) {
        SquirrelEntity squirrelEntity = squirrelDAO.findByUid(uid);
        squirrelEntity.setCompanion(companion);
        return squirrelDAO.save(squirrelEntity);
    }

    @PostMapping(value = "/squirrel/name")
    public SquirrelEntity setName(@RequestParam("uid") Integer uid, @RequestParam("name") String name) {
        SquirrelEntity squirrelEntity = squirrelDAO.findByUid(uid);
        squirrelEntity.setName(name);
        return squirrelDAO.save(squirrelEntity);
    }

}
