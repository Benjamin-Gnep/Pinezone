package com.sgxqwyh.pinezone.controller;

import com.sgxqwyh.pinezone.pojo.ReportEntity;
import com.sgxqwyh.pinezone.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class ReportController {

    @Autowired
    private ReportService reportService;

    //锦浩
    @PostMapping("/report")
    public ReportEntity addReport(ReportEntity reportEntity) {
        return reportService.addReport(reportEntity);
    }
}
