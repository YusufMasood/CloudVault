package com.yusuf.cloudvault.storage.service.impl;

import com.yusuf.cloudvault.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class LocalStorageService implements StorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public String uploadFile(MultipartFile file) {

        System.out.println("Uploading to Local...");

        try {

            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());

            String extension = "";

            int index = originalFileName.lastIndexOf(".");

            if (index != -1) {
                extension = originalFileName.substring(index);
            }

            String storedFileName = UUID.randomUUID() + extension;

            Path destination = uploadPath.resolve(storedFileName);

            Files.copy(file.getInputStream(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING);

            return storedFileName;

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    @Override
    public void deleteFile(String key) {

        try {
            Files.deleteIfExists(Paths.get(uploadDir).resolve(key));
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file", e);
        }

    }

    @Override
    public byte[] downloadFile(String key) {

        try {
            return Files.readAllBytes(Paths.get(uploadDir).resolve(key));
        } catch (IOException e) {
            throw new RuntimeException("Failed to download file", e);
        }

    }
}