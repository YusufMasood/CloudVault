package com.yusuf.cloudvault.storage.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String uploadFile(MultipartFile file);

    void deleteFile(String key);

    byte[] downloadFile(String key);
}