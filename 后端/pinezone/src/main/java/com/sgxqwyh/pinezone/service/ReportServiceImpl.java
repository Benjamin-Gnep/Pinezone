package com.sgxqwyh.pinezone.service;

import com.sgxqwyh.pinezone.dao.ReportDAO;
import com.sgxqwyh.pinezone.pojo.ReportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDAO reportDAO;


    //锦浩
    public ReportEntity addReport(ReportEntity reportEntity) {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        reportEntity.setDate(timestamp);
        reportEntity.setState(1);
        return reportDAO.save(reportEntity);
    }
}
