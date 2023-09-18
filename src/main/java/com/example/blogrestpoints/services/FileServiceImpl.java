package com.example.blogrestpoints.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class FileServiceImpl implements FileService{


    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        String name = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        String filename1=randomId.concat(name.substring(name.lastIndexOf(".")));
        String filePath = path + File.separator + filename1;
        File f = new File(path);
        if (!f.exists()){
            f.mkdirs();
            Files.copy(file.getInputStream(), Paths.get(filePath));
            return name;
        }
        return name;

    }

    @Override
    public InputStream getResource(String path, String filename) throws FileNotFoundException {
        String fullPath = path + File.separator + filename;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }
}
