package com.sgxqwyh.pinezone.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sgxqwyh.pinezone.dao.*;
import com.sgxqwyh.pinezone.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDAO articleDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private LikeDAO likeDAO;
    @Autowired
    private ArticlePictureDAO articlePictureDAO;
    @Autowired
    private UserPictureDAO userPictureDAO;
    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private CollectionDAO collectionDAO;
    @Autowired
    private ReadRecordDAO readRecordDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;

    @Value("${articlePicturePath}")
    private String articlePicturePath;
    @Value("${uploadPath}")
    private String uploadPath;

    //锦浩
    public JSONArray findArticleByCid(Integer cid, Integer currentPage, Integer pageSiz) {
        //分页查询
        CategoryEntity categoryEntity = categoryDAO.findById(cid).get();
        int pageSize = pageSiz;
        Pageable pageable = (Pageable) PageRequest.of(currentPage, pageSize);
        Specification<ArticleEntity> specification = (Specification<ArticleEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            Predicate p1 = criteriaBuilder.equal(root.get("category"), categoryEntity);
            list.add(p1);
            Predicate p2 = criteriaBuilder.equal(root.get("state"),1);
            list.add(p2);
            Predicate[] p = new Predicate[list.size()];
            query.where(criteriaBuilder.and(list.toArray(p)));
            query.orderBy(criteriaBuilder.desc(root.get("num")));
            return query.getRestriction();
        };
        Page<ArticleEntity> articleEntityPage = articleDAO.findAll(specification, pageable);
        //返回格式
        List<ArticleEntity> articleList = articleEntityPage.getContent();
        JSONArray jsonArray = new JSONArray();
        for (ArticleEntity article : articleList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("aid", article.getId());
            jsonObject.put("title", article.getTitle());
            jsonObject.put("aimg", articlePictureDAO.findByAid(article.getId()));
            jsonObject.put("uid", article.getUser().getId());
            jsonObject.put("username", article.getUser().getName());
            jsonObject.put("uimg", userPictureDAO.findByIdAndState(article.getUser().getPid(), 1).getPath());
            jsonObject.put("likenum", likeDAO.countByAidAndState(article.getId(), 1));
            jsonObject.put("date", article.getDate());
            jsonObject.put("num", article.getNum());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    public JSONObject findArticleById(Long aid, Integer uid) {
        ArticleEntity articleEntity = articleDAO.findById(aid).get();
        // 阅读量
        int roleId = userRoleDAO.findByUserId(uid).getRoleId();
        if (roleId == 1) {  // 若为普通用户发起的请求，则增加阅读量
            articleEntity.setNum(articleEntity.getNum() + 1);
            articleDAO.save(articleEntity);
            ReadRecordEntity readRecordEntity = new ReadRecordEntity();
            readRecordEntity.setUid(uid);
            readRecordEntity.setAid(aid);
            readRecordEntity.setState(1);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            readRecordEntity.setDate(timestamp);
            readRecordDAO.save(readRecordEntity);
        }
        // 返回值
        UserEntity userEntity = userDAO.findById(articleEntity.getUser().getId()).get();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aid", articleEntity.getId());
        jsonObject.put("title", articleEntity.getTitle());
        jsonObject.put("num", articleEntity.getNum());
        jsonObject.put("aimg", articlePictureDAO.findByAid(articleEntity.getId()));
        jsonObject.put("content", articleEntity.getContent());
        jsonObject.put("likenum", likeDAO.countByAidAndState(articleEntity.getId(), 1));
        jsonObject.put("commentnum", commentDAO.countByAidAndState(articleEntity.getId(), 1));
        jsonObject.put("starnum", collectionDAO.countByAidAndState(articleEntity.getId(), 1));
        jsonObject.put("describe", "福州大学在读生");
        jsonObject.put("islike", likeDAO.findByUidAndAidAndState(uid, aid, 1)!=null?1:0);
        jsonObject.put("isStar", collectionDAO.findByUidAndAidAndState(uid, aid, 1)!=null?1:0);
        jsonObject.put("datetime", dealDateFormat(articleEntity.getDate()));
        jsonObject.put("uid", articleEntity.getUser().getId());
        jsonObject.put("username", articleEntity.getUser().getName());
        jsonObject.put("uimg", userPictureDAO.findByIdAndState(articleEntity.getUser().getPid(), 1).getPath());
        return jsonObject;
    }

    public JSONObject addArticle(Integer uid, Integer cid, String title, String content, MultipartFile[] imgs) {
        //添加文章
        UserEntity userEntity = userDAO.findById(uid).get();
        CategoryEntity categoryEntity = categoryDAO.findById(cid).get();
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setUser(userEntity);
        articleEntity.setCategory(categoryEntity);
        articleEntity.setTitle(title);
        articleEntity.setContent(content);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        articleEntity.setDate(timestamp);
        articleEntity = articleDAO.save(articleEntity);
        //上传图片
        if (imgs != null && imgs.length>=1) {
            for (MultipartFile aimg : imgs) {
                String fileName = aimg.getOriginalFilename();  // 文件名
                String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
//                if(!suffixName.equals(".jpg") && !suffixName.equals(".png")){
//                    continue;
//                }
                //String filePath = articlePicturePath; // 上传后的路径
                String filePath = uploadPath;
                fileName = java.util.UUID.randomUUID() + suffixName; // 新文件名
                File dest = new File(filePath + fileName);
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                try {
                    aimg.transferTo(dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String filename = articlePicturePath + fileName;
                ArticlePictureEntity articlePictureEntity = new ArticlePictureEntity();
                articlePictureEntity.setAid(articleEntity.getId());
                articlePictureEntity.setPath(filename);
                articlePictureDAO.save(articlePictureEntity);
            }
        }
        articleEntity.setState(1);
        articleDAO.save(articleEntity);
        //返回格式
        UserPictureEntity userPictureEntity;
        if (userEntity.getPid()!=null){
            userPictureEntity = userPictureDAO.findById(userEntity.getPid()).get();
        } else {
            userPictureEntity = new UserPictureEntity();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aid", articleEntity.getId());
        jsonObject.put("title", articleEntity.getTitle());
//        JSONObject imgJsonObject = new JSONObject();
//        List<ArticlePictureEntity> articlePictureEntityList = articlePictureDAO.findByAid(articleEntity.getId());
//        int i = 1;
//        for (ArticlePictureEntity articlePictureEntity : articlePictureEntityList) {
//            imgJsonObject.put(String.valueOf(i), articlePictureEntity.getPath());
//            i++;
//        }
//        jsonObject.put("aimg", imgJsonObject);
        jsonObject.put("aimg", articlePictureDAO.findByAid(articleEntity.getId()));
        jsonObject.put("content", articleEntity.getContent());
        jsonObject.put("likenum", likeDAO.countByAidAndState(articleEntity.getId(), 1));
        jsonObject.put("datetime", dealDateFormat(articleEntity.getDate()));
        jsonObject.put("uid", articleEntity.getUser().getId());
        jsonObject.put("username", articleEntity.getUser().getName());
        jsonObject.put("uimg", userPictureEntity.getPath());
        return jsonObject;
    }

    public int deleteArticle(Long aid) {
        ArticleEntity articleEntity = articleDAO.findById(aid).get();
        articleEntity.setState(0);
        articleDAO.save(articleEntity);
        return 1;
    }

    public JSONObject updateArticle(Long aid, String title, String content, MultipartFile[] imgs) {
        ArticleEntity articleEntity = articleDAO.findById(aid).get();
        articleEntity.setTitle(title);
        articleEntity.setContent(content);
        //上传图片
        List<ArticlePictureEntity> articlePictureEntities = articlePictureDAO.findByAid(aid);
        for (ArticlePictureEntity articlePictureEntity : articlePictureEntities) {
            articlePictureDAO.delete(articlePictureEntity);
        }
        if (imgs != null && imgs.length>=1) {
            for (MultipartFile aimg : imgs) {
                String fileName = aimg.getOriginalFilename();  // 文件名
                String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
//                if(!suffixName.equals(".jpg") && !suffixName.equals(".png")){
//                    continue;
//                }
                String filePath = articlePicturePath; // 上传后的路径
                fileName = java.util.UUID.randomUUID() + suffixName; // 新文件名
                File dest = new File(filePath + fileName);
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                try {
                    aimg.transferTo(dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String filename = articlePicturePath + fileName;
                ArticlePictureEntity articlePictureEntity = new ArticlePictureEntity();
                articlePictureEntity.setAid(articleEntity.getId());
                articlePictureEntity.setPath(filename);
                articlePictureDAO.save(articlePictureEntity);
            }
        }
        //返回格式
        UserEntity userEntity = userDAO.findById(articleEntity.getUser().getId()).get();
        UserPictureEntity userPictureEntity;
        if (userEntity.getPid()!=null){
            userPictureEntity = userPictureDAO.findById(userEntity.getPid()).get();
        } else {
            userPictureEntity = new UserPictureEntity();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aid", articleEntity.getId());
        jsonObject.put("title", articleEntity.getTitle());
//        JSONObject imgJsonObject = new JSONObject();
//        List<ArticlePictureEntity> articlePictureEntityList = articlePictureDAO.findByAid(articleEntity.getId());
//        int i = 1;
//        for (ArticlePictureEntity articlePictureEntity : articlePictureEntityList) {
//            imgJsonObject.put(String.valueOf(i), articlePictureEntity.getPath());
//            i++;
//        }
//        jsonObject.put("aimg", imgJsonObject);
        jsonObject.put("aimg", articlePictureDAO.findByAid(articleEntity.getId()));
        jsonObject.put("content", articleEntity.getContent());
        jsonObject.put("likenum", likeDAO.countByAidAndState(articleEntity.getId(), 1));
        jsonObject.put("datetime", dealDateFormat(articleEntity.getDate()));
        jsonObject.put("uid", articleEntity.getUser().getId());
        jsonObject.put("username", articleEntity.getUser().getName());
        jsonObject.put("uimg", userPictureEntity.getPath());
        return jsonObject;
    }

    public JSONArray findArticleByUser(Integer uid, Integer currentPage, Integer pageSiz) {
        //分页查询
        UserEntity userEntity = userDAO.findById(uid).get();
        int pageSize = pageSiz;
        Pageable pageable = (Pageable) PageRequest.of(currentPage, pageSize);
        Specification<ArticleEntity> specification = (Specification<ArticleEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            Predicate p1 = criteriaBuilder.equal(root.get("user"), userEntity);
            list.add(p1);
            Predicate p2 = criteriaBuilder.equal(root.get("state"),1);
            list.add(p2);
            Predicate[] p = new Predicate[list.size()];
            query.where(criteriaBuilder.and(list.toArray(p)));
            query.orderBy(criteriaBuilder.desc(root.get("date")));
            return query.getRestriction();
//            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
        Page<ArticleEntity> articleEntityPage = articleDAO.findAll(specification, pageable);
        //返回格式
        List<ArticleEntity> articleList = articleEntityPage.getContent();
        JSONArray jsonArray = new JSONArray();
        for (ArticleEntity article : articleList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("aid", article.getId());
            jsonObject.put("title", article.getTitle());
            jsonObject.put("aimg", articlePictureDAO.findByAid(article.getId()));
            jsonObject.put("uid", article.getUser().getId());
            jsonObject.put("username", article.getUser().getName());
            jsonObject.put("uimg", userPictureDAO.findByIdAndState(article.getUser().getPid(), 1).getPath());
            jsonObject.put("likenum", likeDAO.countByAidAndState(article.getId(), 1));
            jsonObject.put("date", article.getDate());
            jsonObject.put("num", article.getNum());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    public JSONArray findArticleByUserStar(Integer uid, Integer currentPage, Integer pageSiz) {
        List<CollectionEntity> aidList = collectionDAO.findByUidAndState(uid, 1);
        List<CollectionEntity> curList;
        JSONArray jsonArray = new JSONArray();
        if (pageSiz * currentPage<aidList.size() && pageSiz*(currentPage+1)<aidList.size()) {
            curList = aidList.subList(pageSiz * currentPage, pageSiz * (currentPage + 1));
        } else if(pageSiz * currentPage<aidList.size() && pageSiz*(currentPage+1)>=aidList.size()) {
            curList = aidList.subList(pageSiz * currentPage, aidList.size());
        } else {
            return jsonArray;
        }
        for (CollectionEntity collection : curList) {
            ArticleEntity article = articleDAO.findById(collection.getAid()).get();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("aid", article.getId());
            jsonObject.put("title", article.getTitle());
            jsonObject.put("aimg", articlePictureDAO.findByAid(article.getId()));
            jsonObject.put("uid", article.getUser().getId());
            jsonObject.put("username", article.getUser().getName());
            jsonObject.put("uimg", userPictureDAO.findByIdAndState(article.getUser().getPid(), 1).getPath());
            jsonObject.put("likenum", likeDAO.countByAidAndState(article.getId(), 1));
            jsonObject.put("date", article.getDate());
            jsonObject.put("num", article.getNum());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    public List<ArticleEntity> findArticleByContent(String str) {
        str = "%" + str + "%";
        List<ArticleEntity> articleEntityList = articleDAO.findByTitleLikeOrContentLike(str, str);
        return articleEntityList;
    }


    public static String dealDateFormat(Timestamp timeStamp) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(timeStamp);
        return sd;
    }


}
