package com.sgxqwyh.pinezone.controller;

import com.sgxqwyh.pinezone.dao.UserDAO;
import com.sgxqwyh.pinezone.dao.UserPictureDAO;
import com.sgxqwyh.pinezone.pojo.ArticlePictureEntity;
import com.sgxqwyh.pinezone.pojo.UserEntity;
import com.sgxqwyh.pinezone.pojo.UserPictureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping(value = "/v1")
public class UserPictureController {

    @Autowired
    private UserPictureDAO userPictureDAO;
    @Autowired
    private UserDAO userDAO;

    @Value("${userPicturePath}")
    private String userPicturePath;
    @Value("${uploadPath}")
    private String uploadPath;

    //锦浩
    @PostMapping(value = "/uploadUserImg")
    public UserPictureEntity uploadUserImg(@RequestParam("uid") Integer uid,
                                           @RequestParam("img") MultipartFile img) {
        if (img.isEmpty()) {
            return null;
        }
        String fileName = img.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
//        if(!suffixName.equals(".jpg") && !suffixName.equals(".png")){
//            return null;
//        }
        String filePath = uploadPath; // 上传后的路径
        fileName = java.util.UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            img.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = userPicturePath + fileName;
        UserPictureEntity userPictureEntity = new UserPictureEntity();
        userPictureEntity.setPath(filename);
        userPictureEntity.setState(1);
        UserPictureEntity userPictureEntity1 =  userPictureDAO.save(userPictureEntity);
        UserEntity userEntity = userDAO.findById(uid).get();
        userEntity.setPid(userPictureEntity1.getId());
        userDAO.save(userEntity);
        return userPictureEntity1;
    }
}
