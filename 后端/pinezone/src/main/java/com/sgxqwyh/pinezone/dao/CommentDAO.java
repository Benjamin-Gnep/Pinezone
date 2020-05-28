package com.sgxqwyh.pinezone.dao;

import com.sgxqwyh.pinezone.pojo.ArticleEntity;
import com.sgxqwyh.pinezone.pojo.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDAO extends JpaRepository<CommentEntity, Long> , JpaSpecificationExecutor<CommentEntity> {

    //锦浩
    List<CommentEntity> findByAidAndState(Long id, int state);
    Integer countByAidAndState(long id, int i);
    Page<CommentEntity> findAll(Specification<CommentEntity> specification, Pageable pageable);

    //鹏飞
    //根据文章id号返回文章的所有评论
    public List<CommentEntity> findByAid(Long Aid);
    //根据用户返回用户的所有评论
    public List<CommentEntity> findByUid(Integer Uid);


}
