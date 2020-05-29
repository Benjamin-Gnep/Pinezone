package com.sgxqwyh.pinezone.dao;

import com.sgxqwyh.pinezone.pojo.ReadRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadRecordDAO extends JpaRepository<ReadRecordEntity, Long> {
    //根据用户id返回用户历史阅读记录
    public List<ReadRecordEntity> findByUid(int Uid);
}
