package com.sgxqwyh.pinezone.controller;

import com.alibaba.fastjson.JSONArray;
import com.sgxqwyh.pinezone.dao.CommentDAO;
import com.sgxqwyh.pinezone.pojo.CommentEntity;
import com.sgxqwyh.pinezone.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentDAO commentDAO;

    //锦浩
    @GetMapping(value = "/comment-list")
    public JSONArray findAllComment(@RequestParam("aid") Long aid,
                                    @RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage,
                                    @RequestParam(value = "num", required = false, defaultValue = "20") Integer pageSize) {
        currentPage = currentPage - 1;
        return commentService.findAllComment(aid, currentPage, pageSize);
    }

    @PostMapping(value = "/comment")
    public CommentEntity addComment(CommentEntity commentEntity) {
        return commentService.addComment(commentEntity);
    }

    @DeleteMapping(value = "/comment")
    public int deleteComment(Long comid) {
        return commentService.deleteComment(comid);
    }


    //鹏飞
    //根据文章id返回所有评论
    @GetMapping(value = "/comments")
    public List<CommentEntity> commentList(@RequestParam(value = "aid") Long aid) {
        return commentDAO.findByAid(aid);
    }

    //根据评论id返回具体评论信息
    @GetMapping(value = "/comment")
    public CommentEntity comment(@RequestParam(value = "id") Long id) {
        CommentEntity commentEntity = commentDAO.findById(id).get();
        return commentEntity;
    }

//    //删除评论
//    @DeleteMapping(value = "/comment")
//    public void delComment(@RequestParam(value = "id") Long id) {
//        //这里没有删除数据库中的记录，而是选择将其状态置为0，表示已删除
//        CommentEntity commentEntity = commentDAO.findById(id).get();
//        commentEntity.setState(0);
//        commentDAO.save(commentEntity);
//    }
}
