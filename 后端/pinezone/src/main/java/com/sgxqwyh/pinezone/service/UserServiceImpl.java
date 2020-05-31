package com.sgxqwyh.pinezone.service;

import com.sgxqwyh.pinezone.dao.FollowDAO;
import com.sgxqwyh.pinezone.dao.UserDAO;
import com.sgxqwyh.pinezone.dao.UserRoleDAO;
import com.sgxqwyh.pinezone.pojo.FollowEntity;
import com.sgxqwyh.pinezone.pojo.UserEntity;
import com.sgxqwyh.pinezone.pojo.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDao;
    @Autowired
    private FollowDAO followDao;
    @Autowired
    private UserRoleDAO userRoleDAO;


    //俊鹏
    /**
     * 用户注册，时间是系统时间，必须要写用户名、密码、手机号、性别
     * @param userEntity
     * @return
     */
    public UserEntity userAdd(UserEntity userEntity) {
        //UserPictureEntity userPictureEntity1=new UserPictureEntity();
        //UserEntity userEntity1=new UserEntity();
        //userEntity.setId(userDao.findById(userEntity.getId()).get().getId());
        //userEntity.setSno(userDao.findById(userEntity.getId()).get().getSno());
        if(userEntity.getSex()==0|| userEntity.getSex()==1||userEntity.getSex()==2){
            userEntity.setSex(userEntity.getSex());
        }
        else {
            userEntity.setSex(0);
        }
        //userEntity.setPid((long) 1);
        userEntity.setLevel(1);//
        userEntity.setState(1);

        //userEntity.setPhone(userDao.findById(userEntity.getId()).get().getPhone());
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        //;// new Date()为获取当前系统时间
        //
        //userEntity1.setPassword(userDao.findById(userEntity.getId()).get().getPassword());
        //userEntity1.setName(userDao.findById(userEntity.getId()).get().getName());
        //userEntity1.setState(1);
        //userEntity1.setProfile(userDao.findById(userEntity.getId()).get().getProfile());
        userEntity.setDate(new Timestamp(System.currentTimeMillis()));
        String md5Password = DigestUtils.md5DigestAsHex(userEntity.getPassword().getBytes());//加密
        userEntity.setPassword(md5Password);
        return userDao.save(userEntity);
    }

    /**
     * 登录1，根据用户名和密码
     * @param userEntity
     * @return
     */

    public boolean userLogin(UserEntity userEntity) {
        String md5Password = DigestUtils.md5DigestAsHex(userEntity.getPassword().substring(5).getBytes());
        UserEntity loginUser =userDao.findByNameAndPassword(userEntity.getName(),md5Password);

        return loginUser==null?false:true;
    }

    /**
     * 根据用户手机号，登录成功返回user表
     * @param userEntity
     * @return
     */
    public UserEntity userLoginByPhone(UserEntity userEntity){
        //return userDao.findByPhone(userEntity.getPhone());
        if (userDao.findByPhone(userEntity.getPhone())!=null) {
            return userDao.findByPhone(userEntity.getPhone());
        } else {
//            UserEntity newUserEntity = new UserEntity();
//            newUserEntity.setName("用户");
//            newUserEntity.setPassword("12345678");
//            newUserEntity.setSex(1);
//            Timestamp timestamp = new Timestamp(new Date().getTime());
//            newUserEntity.setDate(timestamp);
//            newUserEntity.setPhone(userEntity.getPhone());
//            newUserEntity.setProfile("这个人还没有写签名嗷！");
//            newUserEntity.setLevel(2017);
//            newUserEntity.setPid(1L);
//            newUserEntity.setState(1);
//            userDao.save(newUserEntity);
//
//            UserRoleEntity userRoleEntity = new UserRoleEntity();
//            userRoleEntity.setRoleId(1);
//            userRoleEntity.setUserId(newUserEntity.getId());
//            userRoleDAO.save(userRoleEntity);
//
//            newUserEntity.setName("用户" + newUserEntity.getId());
//            return userDao.save(newUserEntity);

            //演示版本
            return userDao.findByPhone("15059074916");
        }
    }

    /**
     * 修改资料，目前是根据ID来修改，目前只允许允许修改用户名、性别、介绍
     * @param userEntity
     * @return
     */
    public UserEntity userEdit(UserEntity userEntity){

        UserEntity userEntity1=new UserEntity();

        userEntity1.setId(userDao.findById(userEntity.getId()).get().getId());
        userEntity1.setSno(userDao.findById(userEntity.getId()).get().getSno());
        userEntity1.setState(userDao.findById(userEntity.getId()).get().getState());
        userEntity1.setPid(userDao.findById(userEntity.getId()).get().getPid());
        userEntity1.setLevel(userDao.findById(userEntity.getId()).get().getLevel());
        userEntity1.setPhone(userDao.findById(userEntity.getId()).get().getPhone());
        userEntity1.setDate(userDao.findById(userEntity.getId()).get().getDate());
        userEntity1.setPassword(userDao.findById(userEntity.getId()).get().getPassword());
        userEntity1.setName(userEntity.getName());
        userEntity1.setSex(userEntity.getSex());
        userEntity1.setProfile(userEntity.getProfile());
        return userDao.save(userEntity1);
    }

    /**
     * 绑定教务处，后台只获取学号
     * @param userEntity
     * @return
     */
    public UserEntity userBind(UserEntity userEntity){
        UserEntity userEntity1=new UserEntity();

        userEntity1.setId(userDao.findById(userEntity.getId()).get().getId());
        //userEntity1.setSno(userDao.findById(userEntity.getId()).get().getSno());
        userEntity1.setState(userDao.findById(userEntity.getId()).get().getState());
        userEntity1.setPid(userDao.findById(userEntity.getId()).get().getPid());
        userEntity1.setLevel(userDao.findById(userEntity.getId()).get().getLevel());
        userEntity1.setPhone(userDao.findById(userEntity.getId()).get().getPhone());
        userEntity1.setDate(userDao.findById(userEntity.getId()).get().getDate());
        userEntity1.setPassword(userDao.findById(userEntity.getId()).get().getPassword());
        userEntity1.setName(userDao.findById(userEntity.getId()).get().getName());
        userEntity1.setSex(userDao.findById(userEntity.getId()).get().getSex());
        userEntity1.setProfile(userDao.findById(userEntity.getId()).get().getProfile());
        userEntity1.setSno(userEntity.getSno());
        return userDao.save(userEntity1);
    }

    /**
     * 用户关注
     * @param followEntity
     * @return
     */
    public boolean userFocus(FollowEntity followEntity, UserEntity userEntity){
        Optional<UserEntity> userEntity1=userDao.findById(userEntity.getId());
        if(userEntity1!=null){
            followEntity.setUid(userEntity1.get().getId());
            //followEntity.setUuid();
            followEntity.setDate(new Timestamp(System.currentTimeMillis()));
            followEntity.setState(1);
            followDao.save(followEntity);
            return true;
        }
        else {
            return false;
        }
        /*followEntity.setUid(userDao.findById(userEntity.getId()).get().getId());
        //followEntity.setId(10);
        //followEntity.setUuid();
        followEntity.setDate(new Timestamp(System.currentTimeMillis()));
        followEntity.setState(1);
        followDao.save(followEntity);
        return true;*/
    }

    /**
     * 取消关注，将state设置为0即为取消关注
     * @param followEntity
     * @param userEntity
     * @return
     */
    public boolean userUnfollow(FollowEntity followEntity, UserEntity userEntity) {
        //UserEntity loginUser =userDao.findByNameAndPassword(userEntity.getName(),userEntity.getPassword());
        //Optional<UserEntity> userEntity1=userDao.findById(userEntity.getId());
        //FollowEntity followEntity1= (FollowEntity) followDao.findByUid(followEntity.getUid());
        //if(userEntity1!=null){
        followDao.delete(followEntity);
        return true;

        //}
        //else {
        //    return false;
        //}
        //followDao.save(followEntity);

    }

    /**
     * 返回关注者列表
     * @param userEntity
     * @return
     */
    public List<FollowEntity> followerFind(UserEntity userEntity){
        return followDao.findByUid(userEntity.getId());
    }

    /**
     * 找回密码(通过手机号判断用户存不存在)
     * @param userEntity
     * @return
     */
    public boolean backPassword(UserEntity userEntity){
        UserEntity userEntity1 =userDao.findByPhone(userEntity.getPhone());
        if(userEntity1!=null) {
            userEntity1.setPassword(userEntity.getPassword());
            userEntity1.setId(userDao.findByPhone(userEntity.getPhone()).getId());
            //userEntity1.setSno(userDao.findById(userEntity.getId()).get().getSno());
            userEntity1.setState(userDao.findByPhone(userEntity.getPhone()).getState());
            userEntity1.setPid(userDao.findByPhone(userEntity.getPhone()).getPid());
            userEntity1.setLevel(userDao.findByPhone(userEntity.getPhone()).getLevel());
            userEntity1.setPhone(userDao.findByPhone(userEntity.getPhone()).getPhone());
            userEntity1.setDate(userDao.findByPhone(userEntity.getPhone()).getDate());
            //userEntity1.setPassword(userDao.findById(userEntity.getId()).get().getPassword());
            userEntity1.setName(userDao.findByPhone(userEntity.getPhone()).getName());
            userEntity1.setSex(userDao.findByPhone(userEntity.getPhone()).getSex());
            userEntity1.setProfile(userDao.findByPhone(userEntity.getPhone()).getProfile());
            userEntity1.setSno(userDao.findByPhone(userEntity.getPhone()).getSno());
            userDao.save(userEntity1);
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * admin 登录，如果是admin并且密码正确返回1，如果错误返回0；如果不是admin返回-1;
     * @param userEntity
     * @param
     * @return
     */
    public int userLoginByAdmin(UserEntity userEntity){
        UserEntity adminUser= userDao.findByName(userEntity.getName());
        if(adminUser==null){
            return -1;
        }
        int loginId=adminUser.getId();
        if(userRoleDao.findByUserId(loginId).getRoleId()==2){
            ///UserEntity loginUser =userDao.findByNameAndPassword(userEntity.getName(),userEntity.getPassword());
            String md5Password=DigestUtils.md5DigestAsHex(userEntity.getPassword().substring(5).getBytes());
            if ((adminUser.getPassword()).equals(md5Password)){
                return 1;
            }
            else{
                /*System.out.println();
                System.out.println("用户输入"+userEntity.getPassword());
                System.out.println("数据库"+adminUser.getPassword());*/
                return 0;
            }
        }
        else{
            return -1;
        }
    }
}
