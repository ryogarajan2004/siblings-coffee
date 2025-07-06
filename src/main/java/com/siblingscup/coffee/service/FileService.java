package com.siblingscup.coffee.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    private final String UPLOAD_DIR="/var/local/siblingscup/product-images";

    public String uploadFile(MultipartFile file){
        try {
            File uploadDir=new File(UPLOAD_DIR);

            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String fileName= UUID.randomUUID()+"_"+file.getOriginalFilename();
            String filePath= Paths.get(UPLOAD_DIR,fileName).toString();

            file.transferTo(new File(filePath));

            return fileName;
        }catch (IOException e){
            throw new RuntimeException("Failed to store file"+e.getMessage());
        }
    }

}
