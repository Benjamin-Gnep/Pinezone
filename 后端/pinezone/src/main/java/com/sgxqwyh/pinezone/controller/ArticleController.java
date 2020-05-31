package com.sgxqwyh.pinezone.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sgxqwyh.pinezone.dao.ArticleDAO;
import com.sgxqwyh.pinezone.dao.ArticlePictureDAO;
import com.sgxqwyh.pinezone.dao.CategoryDAO;
import com.sgxqwyh.pinezone.dao.UserDAO;
import com.sgxqwyh.pinezone.pojo.ArticleEntity;
import com.sgxqwyh.pinezone.pojo.ArticlePictureEntity;
import com.sgxqwyh.pinezone.pojo.CategoryEntity;
import com.sgxqwyh.pinezone.pojo.UserEntity;
import com.sgxqwyh.pinezone.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleDAO articleDAO;
    @Autowired
    private ArticlePictureDAO articlePictureDAO;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private UserDAO userDAO;

    //锦浩
    @GetMapping(value = "/article-list")
    public JSONArray findArticleByCid(@RequestParam("cid") Integer cid,
                                      @RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        currentPage = currentPage - 1;
        return articleService.findArticleByCid(cid, currentPage, pageSize);
    }

    @GetMapping(value = "/article")
    public JSONObject findArticleById(@RequestParam("aid") Long aid, @RequestParam("uid") Integer uid) {
        return articleService.findArticleById(aid, uid);
    }

    @PostMapping(value = "/article")
    public JSONObject addArticle(@RequestParam("uid") Integer uid, @RequestParam("cid") Integer cid,
                                 @RequestParam("title") String title, @RequestParam("content") String content,
                                 @RequestParam("imgs") MultipartFile[] imgs) {
        return articleService.addArticle(uid, cid, title, content, imgs);
    }

    @DeleteMapping(value = "/article")
    public int deleteArticle(@RequestParam("aid") Long aid) {
        return articleService.deleteArticle(aid);
    }

    @PutMapping(value = "/article")
    public JSONObject updateArticle(@RequestParam("aid") Long aid, @RequestParam("imgs") MultipartFile[] imgs,
                                    @RequestParam("title") String title, @RequestParam("content") String content) {
        return articleService.updateArticle(aid, title, content, imgs);
    }

    @GetMapping(value = "/userArticle-list")
    public JSONArray findArticleByUser(@RequestParam("uid") Integer uid,
                                       @RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage,
                                       @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        currentPage = currentPage - 1;
        return articleService.findArticleByUser(uid, currentPage, pageSize);
    }

    @GetMapping(value = "/userStar-list")
    public JSONArray findArticleByUserStar(@RequestParam("uid") Integer uid,
                                       @RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage,
                                       @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        currentPage = currentPage - 1;
        return articleService.findArticleByUserStar(uid, currentPage, pageSize);
    }

    @GetMapping(value = "/findArticle")
    public List<ArticleEntity> findArticleByContent(@RequestParam("str") String str) {
        return articleService.findArticleByContent(str);
    }



    //鹏飞
    //查询返回某一类文章列表，其中食堂类的为1
    @GetMapping(value = "/list/article")
    public List<ArticleEntity> articleEntitiesList(@RequestParam(value = "cid") Integer cId) {
        CategoryEntity categoryEntity = categoryDAO.findById(cId).get();
        return articleDAO.findByCategory(categoryEntity);
    }

    //查询返回某一用户写的文章列表
    @GetMapping(value = "/list/userArticle")
    public List<ArticleEntity> userArticles(@RequestParam(value = "uid") Integer uid) {
        UserEntity userEntity = userDAO.findById(uid).get();
        return articleDAO.findByUser(userEntity);
    }

    //获取所有文章列表
    @GetMapping(value = "/list/articles")
    public List<ArticleEntity> allArticles() {
        return articleDAO.findAll();
    }

    //获取文章信息
    @GetMapping(value = "/getarticle")
    public ArticleEntity article(@RequestParam(value = "id") Long id) {
        return articleDAO.findById(id).get();
    }

//    //删除文章
//    @DeleteMapping(value = "/article")
//    public void delArticle(@RequestParam(value = "id") Long id) {
//        ArticleEntity articleEntity = articleDAO.findById(id).get();
//        articleEntity.setState(0);
//        articleDAO.save(articleEntity);
//    }

    //根据文章id获取该文章所有图片
    @GetMapping(value = "/article/pictures")
    public List<ArticlePictureEntity> getPictures(@RequestParam(value = "id") Long id) {
        return articlePictureDAO.findByAid(id);
    }
}
