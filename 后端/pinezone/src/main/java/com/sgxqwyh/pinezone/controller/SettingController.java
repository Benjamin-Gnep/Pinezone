package com.sgxqwyh.pinezone.controller;

import com.sgxqwyh.pinezone.dao.SettingDAO;
import com.sgxqwyh.pinezone.dao.UserDAO;
import com.sgxqwyh.pinezone.pojo.SuggestionEntity;
import com.sgxqwyh.pinezone.pojo.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;


@RestController
@RequestMapping(value = "/v1")
public class SettingController {

    @Autowired
    private SettingDAO settingDAO;
    @Autowired
    private UserDAO userDAO;


    //世鑫
    /**
     * 保存反馈意见
     * @param feedback
     * @param id
     * @param date
     * @param uid
     * @return
     */
    //@RequestMapping(value = "/feedback",method = RequestMethod.POST)
    @PostMapping(value = "/feedback")
    public SuggestionEntity feedBack(@RequestParam("feedback") String feedback,
                                     @RequestParam("date") Timestamp date,
                                     @RequestParam("uid") int uid) {
        SuggestionEntity suggestionEntity = new SuggestionEntity();
        suggestionEntity.setContent(feedback);
        suggestionEntity.setDate(date);
        suggestionEntity.setUid(uid);
        suggestionEntity.setState(1);

        return settingDAO.save(suggestionEntity);
    }


    /**
     * 注销用户
     * @param id
     * @return
     */
    //@RequestMapping(value = "/logoff/{id}",method = RequestMethod.DELETE)
    @DeleteMapping(value = "/logoff/{id}")
    public UserEntity deleteUser(@PathVariable("id") Integer id){
        UserEntity user = userDAO.findById(id).get();
        userDAO.delete(user);
        return user;
    }
}
