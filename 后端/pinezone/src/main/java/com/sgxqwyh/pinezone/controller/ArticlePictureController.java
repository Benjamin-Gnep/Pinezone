package com.sgxqwyh.pinezone.controller;

import com.sgxqwyh.pinezone.service.ArticlePictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/v1")
public class ArticlePictureController {

    @Autowired
    private ArticlePictureService articlePictureService;

    //锦浩
    @PostMapping(value = "/uploadArticleImg")
    public int uploadArticleImg(@RequestParam(value = "file") MultipartFile file, Long aid) throws Exception{
        return articlePictureService.uploadArticleImg(file, aid);
    }

}
