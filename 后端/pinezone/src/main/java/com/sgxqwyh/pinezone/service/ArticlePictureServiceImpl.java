package com.sgxqwyh.pinezone.service;

import com.sgxqwyh.pinezone.dao.ArticlePictureDAO;
import com.sgxqwyh.pinezone.pojo.ArticlePictureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service("articlePicture")
public class ArticlePictureServiceImpl implements ArticlePictureService {

    @Autowired
    private ArticlePictureDAO articlePictureDAO;

    @Value("${articlePicturePath}")
    private String articlePicturePath;
    @Value("${uploadPath}")
    private String uploadPath;

    //锦浩
    public int uploadArticleImg(MultipartFile file, Long aid) {
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
//        if(!suffixName.equals(".jpg") && !suffixName.equals(".png")){
//            return 0;
//        }
        String filePath = uploadPath; // 上传后的路径
        fileName = java.util.UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = articlePicturePath + fileName;
        ArticlePictureEntity articlePictureEntity = new ArticlePictureEntity();
        articlePictureEntity.setAid(aid);
        articlePictureEntity.setPath(filename);
        articlePictureDAO.save(articlePictureEntity);
        return 1;
    }
}
