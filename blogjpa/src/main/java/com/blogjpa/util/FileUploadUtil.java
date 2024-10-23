package com.blogjpa.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileUploadUtil {

    @Value("${file-upload}")
    private String fileUpload;

    public String saveFile(MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            try {
                Path uploadPath = Paths.get(fileUpload);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath);
                return fileName;
            } catch (IOException e) {
                throw new IOException("Could not save file: " + file.getOriginalFilename(), e);
            }
        }
        return null;
    }

    public void deleteFile(String fileName) throws IOException {
        if (fileName != null && !fileName.isEmpty()) {
            Path path = Paths.get(fileUpload + fileName);
            if (Files.exists(path)) {
                Files.delete(path);
            }
        }
    }
}