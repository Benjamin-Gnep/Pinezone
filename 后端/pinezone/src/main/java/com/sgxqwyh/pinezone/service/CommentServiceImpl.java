package com.sgxqwyh.pinezone.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sgxqwyh.pinezone.dao.ArticlePictureDAO;
import com.sgxqwyh.pinezone.dao.CommentDAO;
import com.sgxqwyh.pinezone.dao.UserDAO;
import com.sgxqwyh.pinezone.dao.UserPictureDAO;
import com.sgxqwyh.pinezone.pojo.ArticleEntity;
import com.sgxqwyh.pinezone.pojo.CategoryEntity;
import com.sgxqwyh.pinezone.pojo.CommentEntity;
import com.sgxqwyh.pinezone.pojo.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserPictureDAO userPictureDAO;


    //锦浩
    public JSONArray findAllComment(Long aid, Integer currentPage, Integer pageSiz) {
        //分页查询
        int pageSize = pageSiz;
        Pageable pageable = (Pageable) PageRequest.of(currentPage, pageSize);
        Specification<CommentEntity> specification = (Specification<CommentEntity>) (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            Predicate p1 = criteriaBuilder.equal(root.get("aid"), aid);
            list.add(p1);
            Predicate p2 = criteriaBuilder.equal(root.get("state"),1);
            list.add(p2);
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
        Page<CommentEntity> commentEntityPage = commentDAO.findAll(specification, pageable);
        List<CommentEntity> commentEntityList = commentEntityPage.getContent();
        //if (commentDAO.findByAidAndState(aid, state).isEmpty());
//        List<CommentEntity> commentEntityList = commentDAO.findByAidAndState(aid, state);
        JSONArray jsonArray = new JSONArray();
        for (CommentEntity commentEntity : commentEntityList) {
            UserEntity userEntity = userDAO.findById(commentEntity.getUid()).get();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("comid", commentEntity.getId());
            jsonObject.put("uid", userEntity.getId());
            jsonObject.put("username",userEntity.getName());
            jsonObject.put("uimg", userPictureDAO.findByIdAndState(userEntity.getPid(), 1).getPath());
            jsonObject.put("content", commentEntity.getContent());
            jsonObject.put("datetime", commentEntity.getDate());
            jsonArray.add(jsonObject);
        }
//        jsonArray.sort(Comparator.comparing(obj -> ((JSONObject) obj).getBigDecimal("comid")).reversed());
        return jsonArray;
    }

    public CommentEntity addComment(CommentEntity commentEntity) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        commentEntity.setDate(timestamp);
        commentEntity.setState(1);
        return commentDAO.save(commentEntity);
    }

    public int deleteComment(Long comid) {
        if (commentDAO.findById(comid).isPresent()) {
            CommentEntity commentEntity = commentDAO.findById(comid).get();
            commentEntity.setState(0);
            commentDAO.save(commentEntity);
            return 1;
        } else {
            return 0;
        }
    }

    public static String dealDateFormat(Timestamp timeStamp) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(timeStamp);
        return sd;
    }
}
