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
    public JSONArray readActivity1(){
        List<Object[]> list1 = articleDAO.findDayRead();
        List<Object[]> list2 = articleDAO.findDayArticle();
        List<Object[]> list3 = articleDAO.findDayComment();
        HashMap<String, BigInteger> hashMap = new HashMap<>();
        JSONArray jsonArray = new JSONArray();
        for (Object[] object: list1) {
            hashMap.put((String)object[0], (BigInteger)object[1]);
        }
        for(Object[] object:list2){
            if(hashMap.containsKey(object[0])){
                hashMap.put((String)object[0], hashMap.get(object[0]).add((BigInteger)object[1]));
            }
            else{
                hashMap.put((String)object[0],(BigInteger)object[1]);
            }
        }
        for(Object[] object:list3){
            if(hashMap.containsKey(object[0])){
                hashMap.put((String)object[0],hashMap.get(object[0]).add((BigInteger)object[1]));
            }
            else{
                hashMap.put((String)object[0],(BigInteger)object[1]);
            }
        }
        Iterator<Map.Entry<String, BigInteger>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, BigInteger> entry = iterator.next();
            String key = entry.getKey();
            BigInteger value = entry.getValue();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("date", key);
            jsonObject.put("activity", value);
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }

    @GetMapping(value = "/v1/statistics/articles/active-period")
    public JSONArray readActivity2(){
        List<Object[]> list1 = articleDAO.findHonrRead();
        List<Object[]> list2 = articleDAO.findHonrArticle();
        List<Object[]> list3 = articleDAO.findHonrComment();
        HashMap<String, BigInteger> hashMap = new HashMap<>();
        JSONArray jsonArray = new JSONArray();
        for (Object[] object: list1) {
            hashMap.put((String)object[0],(BigInteger)object[1]);
        }
        for(Object[] object:list2){
            if(hashMap.containsKey(object[0])){
                hashMap.put((String)object[0],hashMap.get(object[0]).add((BigInteger)object[1]));
            }
            else{
                hashMap.put((String)object[0],(BigInteger)object[1]);
            }
        }
        for(Object[] object:list3){
            if(hashMap.containsKey(object[0])){
                hashMap.put((String)object[0],hashMap.get(object[0]).add((BigInteger)object[1]));
            }
            else{
                hashMap.put((String)object[0],(BigInteger)object[1]);
            }
        }
        Iterator<Map.Entry<String, BigInteger>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, BigInteger> entry = iterator.next();
            String key = entry.getKey();
            BigInteger value = entry.getValue();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("period", key);
            jsonObject.put("activity", value);
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }
}
