package com.sgxqwyh.pinezone.controller;

import com.sgxqwyh.pinezone.dao.*;
import com.sgxqwyh.pinezone.pojo.*;
import com.sgxqwyh.pinezone.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;
    @Autowired
    private NoticeDAO noticeDAO;
    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private ArticleDAO articleDAO;
    @Autowired
    private FollowDAO followDAO;
    @Autowired
    private LikeDAO likeDAO;
    @Autowired
    private CollectionDAO collectionDAO;
    @Autowired
    private ReportDAO reportDAO;
    @Autowired
    private UserPictureDAO userPictureDAO;
    @Autowired
    private ReadRecordDAO readRecordDAO;

    //俊鹏
    /*@GetMapping(value = "/girls/{id}")
    /*public UserEntity girlFindOne(@PathVariable("id") Integer id){
        return userDao.findById(id).get();
    }*/

    /**
     * 注册
     * @param userEntity
     * @return
     */
    @PostMapping("/register")
    public UserEntity register(UserEntity userEntity){
        return userServiceImpl.userAdd(userEntity);
    }
    /*public UserEntity register(@RequestParam("name") String name,@RequestParam("password") String password,
                               @RequestParam("sex") int sex,@RequestParam("date") Timestamp date,
                               @RequestParam("phone") String phone,@RequestParam("sno") String sno,
                               @RequestParam("profile") String profile,@RequestParam("level") int level,
                               @RequestParam("pid") Long pid,@RequestParam("state") int state){

        //return user;
        UserEntity userEntity1=new UserEntity();
        userEntity1.setSno(sno);
        if(sex==0||sex==1||sex==2){
            userEntity1.setSex(sex);
        }
        else {
            userEntity1.setSex(0);
        }

        userEntity1.setPid((long) 1);//
        userEntity1.setLevel(1);//

        userEntity1.setPhone(phone);
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        //;// new Date()为获取当前系统时间
        Timestamp nousedate = new Timestamp(System.currentTimeMillis());
        userEntity1.setDate(nousedate);//
        userEntity1.setPassword(password);
        userEntity1.setName(name);
        userEntity1.setState(1);
        userEntity1.setProfile(profile);

        return userDao.save(userEntity1);
    }*/

    /**
     * 登录
     * @param userEntity
     * @return
     */
    @GetMapping("/login")
    public boolean login(UserEntity userEntity){
        return userServiceImpl.userLogin(userEntity);
    }

    @GetMapping("/loginByPhone/{phone}")
    public UserEntity loginByPhone(UserEntity userEntity){
        return userServiceImpl.userLoginByPhone(userEntity);
    }

    /**
     * 修改资料
     * @param userEntity
     * @return
     */
    @PutMapping(value = "/edit/{id}")
    public UserEntity userUpdate(UserEntity userEntity){

        return userServiceImpl.userEdit(userEntity);
    }

    /**
     * 绑定教务处(存入学号)
     * @param userEntity
     * @return
     */
    @PutMapping(value = "/bind/{id}")
    public UserEntity Bind(UserEntity userEntity){
        return userServiceImpl.userBind(userEntity);
    }

    /**
     * 找回密码
     * @param userEntity
     * @return
     */
    @PutMapping("/backPassword/{phone}")
    public Boolean backPassword(UserEntity userEntity){
        return userServiceImpl.backPassword(userEntity);
    }

    /**
     * 关注
     * @param followEntity
     * @param userEntity
     * @return
     */
    @PutMapping("/focus/{id}")
    public boolean userFollow(FollowEntity followEntity, UserEntity userEntity){
        return userServiceImpl.userFocus(followEntity,userEntity);
    }

    /**
     * 取消用户关注
     * @param followEntity
     * @param userEntity
     * @return
     */
    @GetMapping("/unfocus/{id}")
    public boolean userUnFollow(FollowEntity followEntity, UserEntity userEntity){
        return userServiceImpl.userUnfollow(followEntity,userEntity);
    }

    /**
     * 返回关注者列表
     * @param userEntity
     * @return
     */
    @GetMapping("/follower/{id}")
    public List<FollowEntity> followers(UserEntity userEntity){
        return userServiceImpl.followerFind(userEntity);
    }



    //鹏飞
    /**
     * 查询返回用户列表
     * @return
     */
    @GetMapping(value = "/list/users")
    public List<UserEntity> userEntitiesList() {
        List<UserEntity> userEntities =new ArrayList<>();
        List<UserRoleEntity> userRoleEntities= userRoleDAO.findByRoleId(1);
        for (UserRoleEntity u: userRoleEntities) {
            UserEntity us = userDAO.findById(u.getUserId()).get();
            userEntities.add(us);
        }
        return userEntities;
    }

    /**
     * 查询返回管理员列表
     * @return
     */
    @GetMapping(value = "/list/managers")
    public List<UserEntity> mgeEntitiesList() {
        List<UserEntity> managers =new ArrayList<>();
        List<UserRoleEntity> userRoleEntities= userRoleDAO.findByRoleId(2);
        for (UserRoleEntity u: userRoleEntities) {
            UserEntity us = userDAO.findById(u.getUserId()).get();
            managers.add(us);
        }
        return managers;
    }

    //根据每条条数，页数和用户分类（用户/管理员）返回用户列表和该页的数目
    @GetMapping (value = "/list/usersByPage")
    public UserNumEntity usersByPage(@RequestParam(value = "roleId") int roleId , @RequestParam
            (value = "pageNum") int pageNum , @RequestParam(value = "page") int page) {
        List<UserEntity> userEntities =new ArrayList<>();
        List<UserRoleEntity> userRoleEntities= userRoleDAO.findByRoleId(roleId);
        for (UserRoleEntity u: userRoleEntities) {
            UserEntity us = userDAO.findById(u.getUserId()).get();
            userEntities.add(us);
        }
        Collections.sort(userEntities);
        int num;//总条数
        int pNum;//该页的总数目
        num = userEntities.size();
        List<UserEntity> users = new ArrayList<>();
        if(num < page * pageNum){
            for(int i=pageNum*(page-1); i<num;i++) {
                users.add(userEntities.get(i));
            }
        }
        else {
            for (int i=0;i<pageNum;i++) {
                users.add(userEntities.get(i+pageNum*(page-1)));
            }
        }
        pNum = users.size();
        UserNumEntity userNumEntity = new UserNumEntity();
        userNumEntity.setUserNum(num);
        userNumEntity.setPageNum(pNum);
        userNumEntity.setUserEntities(users);
        return userNumEntity;
    }

    //查询一个用户
    @GetMapping(value = "/user")
    public UserEntity userSelect(@RequestParam("id") Integer id) {
        return userDAO.findById(id).get();
    }

    //向用户发送消息
    @PostMapping(value = "/user")
    public NoticeEntity user_postMassage(@RequestParam(value = "uid") Integer Uid,
                                         @RequestParam(value = "title") String title,
                                         @RequestParam(value = "content") String content) {
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setUid(Uid);
        noticeEntity.setTitle(title);
        noticeEntity.setContent(content);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        noticeEntity.setDate(timestamp);
        noticeEntity.setState(0);//假设0为未发布状态
        return noticeDAO.save(noticeEntity);
    }

    //根据用户id查询历史评论（无论删除与否）
    @GetMapping(value = "/user/comments")
    public List<CommentEntity> getComments(@RequestParam(value = "uid") Integer Uid) {
        List<CommentEntity> commentEntities = commentDAO.findByUid(Uid);
        Collections.sort(commentEntities);
        return commentEntities;
    }

    // 根据用户id查询关注表
    @GetMapping(value = "/user/follows")
    public List<FollowEntity> getFollows(@RequestParam(value = "uid") Integer Uid) {
        return followDAO.findByUid(Uid);
    }

    //根据用户id查询关注数量
    @GetMapping(value = "/user/followNum")
    public Integer getFollowNum(@RequestParam(value = "Uid") Integer Uid) {
        List<FollowEntity> followEntities = followDAO.findByUid(Uid);
        Integer num = followEntities.size();
        return num;
    }

    //根据用户id查询粉丝数
    @GetMapping(value = "/user/FansNum")
    public Integer getFansNum(@RequestParam(value = "Uuid") Integer Uuid) {
        List<FollowEntity> followEntities = followDAO.findByUuid(Uuid);
        Integer num = followEntities.size();
        return num;
    }

    //根据用户id查询文章发布数
    @GetMapping(value = "/user/hasArticles")
    public Integer getUserArticles(@RequestParam(value = "Uid") Integer Uid) {
        UserEntity userEntity = userDAO.findById(Uid).get();
        List<ArticleEntity>  articleEntities = articleDAO.findByUser(userEntity);
        return articleEntities.size();
    }

    //根据用户id查询点赞表
    @GetMapping(value = "/user/likes")
    public List<LikeEntity> getLikes(@RequestParam(value = "uid") Integer Uid) {
        return likeDAO.findByUid(Uid);
    }

    //根据用户id查询收藏表
    @GetMapping(value = "/user/collections")
    public List<CollectionEntity> getCollections(@RequestParam(value = "uid") Integer Uid) {
        return collectionDAO.findByUid(Uid);
    }

    //根据用户id查询举报表
    @GetMapping(value = "/user/reports")
    public List<ReportEntity> getReports(@RequestParam(value = "uid") Integer Uid) {
        return reportDAO.findByUid(Uid);
    }

    //根据用户id查询头像表
    @GetMapping(value = "/user/picture")
    public UserPictureEntity getPicture(@RequestParam(value = "uid") Integer Uid) {
        UserEntity userEntity = userDAO.findById(Uid).get();
        Long Pid = userEntity.getPid();
        return userPictureDAO.findByIdAndState(Pid,1);
    }

    //根据用户id查询历史阅读记录
    @GetMapping(value = "/user/readRecord")
    public List<ReadRecordEntity> getReadRecord(@RequestParam(value = "uid") Integer Uid) {
        List<ReadRecordEntity> readRecordEntities = readRecordDAO.findByUid(Uid);
        Collections.sort(readRecordEntities);
        return readRecordEntities;
    }
}
