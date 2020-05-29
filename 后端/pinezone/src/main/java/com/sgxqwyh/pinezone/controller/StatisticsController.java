package com.sgxqwyh.pinezone.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sgxqwyh.pinezone.dao.CommentDAO;
import com.sgxqwyh.pinezone.dao.LikeDAO;
import com.sgxqwyh.pinezone.dao.ReadRecordDAO;
import com.sgxqwyh.pinezone.pojo.ReadRecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/v1")
public class StatisticsController {

    @Autowired
    private ReadRecordDAO readRecordDAO;
    @Autowired
    private LikeDAO likeDAO;
    @Autowired
    private CommentDAO commentDAO;

    //锦浩
    @PostMapping(value = "/article/read")
    public ReadRecordEntity addRecord(@RequestParam("uid") Integer uid, @RequestParam("aid") Integer aid) {
        ReadRecordEntity readRecordEntity = new ReadRecordEntity();
        readRecordEntity.setUid(uid);
        readRecordEntity.setAid(aid);
        readRecordEntity.setState(1);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        readRecordEntity.setDate(timestamp);
        return readRecordDAO.save(readRecordEntity);
    }

    @GetMapping(value = "/statistics/articleReadNum")
    public JSONArray getArticleReadNum(@RequestParam("start") String start, @RequestParam("end") String end) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse(start);
        Date endDate = dateFormat.parse(end);
        start = dateFormat.format(startDate);
        end = dateFormat.format(endDate);

        List<ReadRecordEntity> readRecordEntityList = readRecordDAO.findAll();
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (ReadRecordEntity readRecordEntity : readRecordEntityList) {
            String date = readRecordEntity.getDay();
            if (hashMap.containsKey(date)) {
                hashMap.put(date, hashMap.get(date) + 1);
            } else {
                hashMap.put(date, 1);
            }
        }

        JSONArray jsonArray = new JSONArray();
        while (true) {
            JSONObject jsonObject = new JSONObject();
            if (hashMap.containsKey(start)) {
                jsonObject.put("date", start);
                jsonObject.put("num", hashMap.get(start));
                jsonArray.add(jsonObject);
            } else {
                jsonObject.put("date", start);
                jsonObject.put("num", 0);
                jsonArray.add(jsonObject);
            }
            if (end.equals(start)) {
//                System.out.println("阅读量结束");
                break;
            } else {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(startDate);
                calendar.add(calendar.DATE,1); //把日期往后增加一天,整数往后推,负数往前移动
                startDate = calendar.getTime(); //这个时间就是日期往后推一天的结果
                start = dateFormat.format(startDate);
            }
        }
        return jsonArray;
    }
}
