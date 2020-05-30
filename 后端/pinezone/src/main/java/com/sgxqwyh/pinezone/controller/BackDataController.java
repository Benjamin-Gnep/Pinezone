package com.sgxqwyh.pinezone.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sgxqwyh.pinezone.dao.*;
import com.sgxqwyh.pinezone.pojo.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BackDataController {

    @Autowired
    private ArticleDAO articleDAO;

    @Autowired
    private ReportDAO reportDAO;

    @Autowired
    private UserDAO userDAO;

    //章权


    @GetMapping(value = "/v1/statistics/articles/proportion")
    public JSONArray readPro(){
        List<Object[]> list = articleDAO.findPro();
        JSONArray jsonArray = new JSONArray();
        for (Object[] object : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("cid", object[0]);
            jsonObject.put("num", object[1]);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @GetMapping(value = "/v1/statistics/articles/new")
    public JSONArray readAdd(){
        List<Object[]> list = articleDAO.findAdd();
        JSONArray jsonArray = new JSONArray();
        for (Object[] object : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("date", object[0]);
            jsonObject.put("num", object[1]);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @GetMapping(value = "/v1/statistics/articles/reported-articles-list")
    public JSONArray readReport(){
        List<Object[]> list = reportDAO.findReport();
        JSONArray jsonArray = new JSONArray();
        for (Object[] object : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("aid", object[0]);
            jsonObject.put("num", object[1]);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @GetMapping(value = "/v1/statistics/articles/sex-radio")
    public JSONArray readASex(){
        List<Object[]> list = userDAO.findSex();
        JSONArray jsonArray = new JSONArray();
        for (Object[] object : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sex", object[0].equals(1)?"男":"女");
            jsonObject.put("num", object[1]);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @GetMapping(value = "/v1/statistics/articles/active-period")
    public JSONArray readPeri(){
        List<Object[]> list = articleDAO.findPeri();
        JSONArray jsonArray = new JSONArray();
        for (Object[] object : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("time", object[0]);
            jsonObject.put("num", object[1]);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @GetMapping(value = "/v1/statistics/articles/active-users")
    public float readActive(){
        return articleDAO.findActive();
    }

    @GetMapping(value = "/v1/statistics/articles/active-users-list")
//    public List<UserEntity> readActiveList(){
//        return articleDAO.findActList();
//    }
    public JSONArray readActiveList(){
        List<Object[]> list = articleDAO.findActList();
        JSONArray jsonArray = new JSONArray();
        for (Object[] object : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", object[0]);
            jsonObject.put("name", object[1]);
            jsonObject.put("password", object[2]);
            jsonObject.put("sex", object[3]);
            jsonObject.put("date", object[4]);
            jsonObject.put("phone", object[5]);
            jsonObject.put("sno", object[6]);
            jsonObject.put("profile", object[7]);
            jsonObject.put("level", object[8]);
            jsonObject.put("pid", object[9]);
            jsonObject.put("state", object[10]);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    @GetMapping(value = "/v1/statistics/articles/activity")
    public JSONArray readActivity(){
        List<Object[]> list = articleDAO.findActivity();
        JSONArray jsonArray = new JSONArray();
        for (Object[] object : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("time", object[0]);
            jsonObject.put("num", object[1]);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

}
