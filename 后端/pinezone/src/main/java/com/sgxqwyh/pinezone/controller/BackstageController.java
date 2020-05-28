package com.sgxqwyh.pinezone.controller;

import com.sgxqwyh.pinezone.dao.NoticeDAO;
import com.sgxqwyh.pinezone.pojo.NoticeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class BackstageController {

    @Autowired
    private NoticeDAO noticeDAO;


    //世鑫
    /**
     * 获取公告列表
     * @return
     */
    //@RequestMapping(value = "/notices",method = RequestMethod.GET)
    @GetMapping(value = "/notices")
    public List<NoticeEntity> listNotice() {
        return noticeDAO.findAll();
    }

    /**
     * 发布公告
     * @param uid
     * @param title
     * @param content
     * @param date
     * @return
     */
    //@RequestMapping(value = "/notices",method = RequestMethod.POST)
    @PostMapping(value = "/notices")
    public NoticeEntity addNotice(@RequestParam("uid") int uid,
                                  @RequestParam("title") String title,
                                  @RequestParam("content") String content,
                                  @RequestParam("date") Timestamp date) {
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setUid(uid);
        noticeEntity.setTitle(title);
        noticeEntity.setContent(content);
        noticeEntity.setDate(date);
        noticeEntity.setState(2);

        return noticeDAO.save(noticeEntity);
    }

    /**
     * 删除公告
     * @param id
     * @return
     */
    //@RequestMapping(value = "/notices/{id}",method = RequestMethod.DELETE)
    @DeleteMapping(value = "/notices/{id}")
    public NoticeEntity deleteNotice(@PathVariable("id") long id) {
        NoticeEntity noticeEntity = noticeDAO.findById(id);
        noticeEntity.setState(0);
        noticeDAO.save(noticeEntity);

        return noticeEntity;
    }
}
