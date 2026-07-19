package com.yusuf.cloudvault.file.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import com.yusuf.cloudvault.file.dto.FileResponseDto;
import java.util.List;

public interface FileService {

    String uploadFile(MultipartFile file);

    ResponseEntity<byte[]> downloadFile(Long fileId);

    String deleteFile(Long fileId);

    List<FileResponseDto> getMyFiles();
}