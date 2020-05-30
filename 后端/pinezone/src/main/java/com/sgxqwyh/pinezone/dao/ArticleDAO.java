package com.sgxqwyh.pinezone.dao;

import com.sgxqwyh.pinezone.pojo.ArticleEntity;
import com.sgxqwyh.pinezone.pojo.CategoryEntity;
import com.sgxqwyh.pinezone.pojo.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDAO extends JpaRepository<ArticleEntity, Long>, JpaSpecificationExecutor<ArticleEntity> {

    //锦浩
    Page<ArticleEntity> findAll(Specification<ArticleEntity> specification, Pageable pageable);

    //鹏飞
    //根据类型返回文章列表
    List<ArticleEntity> findByCategory(CategoryEntity categoryEntity);
    //根据用户id返回该用户的所有文章
    List<ArticleEntity> findByUser(UserEntity userEntity);

    //章权


    @Query(value = "SELECT cid,count(id) FROM pinezone.article group by cid", nativeQuery = true)
    public List<Object[]> findPro();

    @Query(value = "SELECT date_format(date, '%Y-%m-%d'),count(id) FROM pinezone.article group by date_format(date, '%Y-%m-%d');", nativeQuery = true)
    public List<Object[]> findAdd();
    
    @Query(value = "SELECT count(distinct uid)/(select count(id) from pinezone.user) FROM pinezone.article where date > date_sub(curdate(),interval 1 month) ",nativeQuery = true)
    public float findActive();

    @Query(value = "SELECT date_format(date,'%k'),count(distinct uid) FROM pinezone.article group by date_format(date,'%k');",nativeQuery = true)
    public List<Object[]> findPeri();

    @Query(value = "SELECT user.* FROM pinezone.article inner join pinezone.user on article.uid = user.id where article.date > date_sub(curdate(),interval 1 month)",nativeQuery = true)
    public List<Object[]> findActList();

    @Query(value = "SELECT date_format(date,'%m-%d'),COUNT(read_record.id) FROM pinezone.read_record GROUP BY date_format(date,'%m-%d') ",nativeQuery = true)
    public List<Object[]> findDayRead();
    @Query(value = "SELECT date_format(date,'%m-%d'),COUNT(article.id) FROM pinezone.article GROUP BY date_format(date,'%m-%d')  ",nativeQuery = true)
    public List<Object[]> findDayArticle();
    @Query(value = "SELECT date_format(date,'%m-%d'),COUNT(`comment`.id) FROM pinezone.`comment` GROUP BY date_format(date,'%m-%d')  ",nativeQuery = true)
    public List<Object[]> findDayComment();

}
