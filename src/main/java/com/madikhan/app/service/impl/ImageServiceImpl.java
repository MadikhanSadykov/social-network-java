package com.madikhan.app.service.impl;

import com.madikhan.app.dao.impl.ImageDAOImpl;
import com.madikhan.app.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ImageServiceImpl implements ImageService {

    private final ImageDAOImpl imageDAO;

    @Autowired
    public ImageServiceImpl(ImageDAOImpl imageDAO) {
        this.imageDAO = imageDAO;
    }

    @Override
    public void saveAvatar(MultipartFile image) throws IOException {
        imageDAO.saveProfileAvatar(image);
    }

    @Override
    public byte[] listImageByName(String name) throws IOException {
        return imageDAO.getImageByName(name);
    }
}
