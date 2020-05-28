package com.sgxqwyh.pinezone.pojo;

import java.util.List;

public class UserNumEntity {
    public int UserNum;//用户总数
    public int pageNum;//某页的用户总数
    public List<UserEntity> userEntities;
    //public int UserNum;


    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setUserNum(int userNum) {
        UserNum = userNum;
    }

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }
}
