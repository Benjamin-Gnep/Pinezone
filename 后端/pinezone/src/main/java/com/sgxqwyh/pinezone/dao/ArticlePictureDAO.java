package com.sgxqwyh.pinezone.dao;

import com.sgxqwyh.pinezone.pojo.ArticlePictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticlePictureDAO extends JpaRepository<ArticlePictureEntity, Long> {

    //锦浩
    List<ArticlePictureEntity> findByAid(long id);

//    //鹏飞
//    //根据文章id获取图片
//    public List<ArticlePictureEntity> findByAid(Long Aid);
}
