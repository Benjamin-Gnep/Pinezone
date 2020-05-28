package com.sgxqwyh.pinezone.service;

import com.alibaba.fastjson.JSONArray;
import com.sgxqwyh.pinezone.pojo.CommentEntity;

import java.util.List;

public interface CommentService {

    //锦浩
    JSONArray findAllComment(Long id, Integer currentPage, Integer pageSize);
    CommentEntity addComment(CommentEntity commentEntity);
    int deleteComment(Long id);
}
