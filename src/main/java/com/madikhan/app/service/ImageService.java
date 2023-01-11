package com.madikhan.app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface ImageService {

    void saveAvatar(MultipartFile image) throws IOException;

    byte[] listImageByName(String name) throws IOException;

}
