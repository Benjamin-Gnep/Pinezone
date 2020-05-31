package com.sgxqwyh.pinezone.service;

import com.sgxqwyh.pinezone.pojo.FollowEntity;
import com.sgxqwyh.pinezone.pojo.UserEntity;

public interface UserService {

    //俊鹏
    UserEntity userAdd(UserEntity userEntity);
    boolean userLogin(UserEntity userEntity);
    UserEntity userEdit(UserEntity userEntity);
    UserEntity userBind(UserEntity userEntity);
    boolean userFocus(FollowEntity followEntity, UserEntity userEntity);
    boolean backPassword(UserEntity userEntity);
    boolean userUnfollow(FollowEntity followEntity,UserEntity userEntity);
    UserEntity userLoginByPhone(UserEntity userEntity);
    public int userLoginByAdmin(UserEntity userEntity);
}
