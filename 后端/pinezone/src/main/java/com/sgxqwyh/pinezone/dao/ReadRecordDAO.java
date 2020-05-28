package com.sgxqwyh.pinezone.dao;

import com.sgxqwyh.pinezone.pojo.ReadRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadRecordDAO extends JpaRepository<ReadRecordEntity, Long> {
}
