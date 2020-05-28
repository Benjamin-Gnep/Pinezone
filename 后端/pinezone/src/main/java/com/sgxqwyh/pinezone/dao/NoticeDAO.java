package com.sgxqwyh.pinezone.dao;

import com.sgxqwyh.pinezone.pojo.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeDAO extends JpaRepository<NoticeEntity,Long> {

    //世鑫
    NoticeEntity findById(long id);
}
