package com.sgxqwyh.pinezone.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sgxqwyh.pinezone.pojo.ArticleEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {

    //锦浩
    JSONObject findArticleById(Long id, Integer uid);
    JSONObject addArticle(Integer uid, Integer cid, String content, String title, MultipartFile aimg[]);
    JSONArray findArticleByCid(Integer cid, Integer currentPage, Integer pageSize);
    int deleteArticle(Long id);
    JSONObject updateArticle(Long aid, String title, String content, MultipartFile[] imgs);
    JSONArray findArticleByUser(Integer uid, Integer currentPage, Integer pageSiz);
    JSONArray findArticleByUserStar(Integer uid, Integer currentPage, Integer pageSiz);
    List<ArticleEntity> findArticleByContent(String s1);
}
