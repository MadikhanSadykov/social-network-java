package com.madikhan.app.dao.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

@Data
@Slf4j
@Component
public class ImageDAOImpl {

    @Resource
    private final Environment environment;
    private File IMAGES_DIR_FILE;

    @Autowired
    public ImageDAOImpl(Environment environment) {
        this.environment = environment;
        this.IMAGES_DIR_FILE = new File(Objects.requireNonNull(environment.getProperty("image.dir")));
        if (!IMAGES_DIR_FILE.exists()) {
            IMAGES_DIR_FILE.mkdirs();
        }
    }

    public void saveProfileAvatar(MultipartFile image) throws IOException {
        File uploadedFile = new File(environment.getProperty("image.dir") + image.getOriginalFilename());

        byte[] bytes = image.getBytes();
        BufferedOutputStream stream =
                new BufferedOutputStream(new FileOutputStream(uploadedFile));
        stream.write(bytes);
        stream.close();
    }

    public byte[] getImageByName(String name) throws IOException {
        File imageFile = new File(environment.getProperty("image.dir") + name);
        return  Files.readAllBytes(imageFile.toPath());
    }
}
