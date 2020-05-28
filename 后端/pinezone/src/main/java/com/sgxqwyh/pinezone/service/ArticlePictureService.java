package com.sgxqwyh.pinezone.service;

import org.springframework.web.multipart.MultipartFile;

public interface ArticlePictureService {

    //锦浩
    int uploadArticleImg(MultipartFile file, Long aid);
}
