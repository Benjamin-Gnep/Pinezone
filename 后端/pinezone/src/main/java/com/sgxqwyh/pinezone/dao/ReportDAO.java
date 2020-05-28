package com.sgxqwyh.pinezone.dao;

import com.sgxqwyh.pinezone.pojo.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportDAO extends JpaRepository<ReportEntity, Long> {

    //章权
    @Query(value = "SELECT aid,count(id) FROM pinezone.report where report.aid=report.aid group by aid;", nativeQuery = true)
    public List<Object[]> findReport();

    //鹏飞
    //根据用户id返回举报表
    public List<ReportEntity> findByUid(Integer Uid);

}
