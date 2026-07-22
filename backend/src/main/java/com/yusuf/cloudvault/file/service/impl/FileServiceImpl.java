package com.yusuf.cloudvault.file.service.impl;


import com.yusuf.cloudvault.file.dto.FileResponseDto;
import com.yusuf.cloudvault.file.repository.FileRepository;
import com.yusuf.cloudvault.file.service.FileService;
import com.yusuf.cloudvault.storage.service.StorageService;
import com.yusuf.cloudvault.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.yusuf.cloudvault.user.entity.User;
import com.yusuf.cloudvault.file.entity.FileEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final StorageService storageService;
    private final UserRepository userRepository;

    @Override
    public String uploadFile(MultipartFile file) {

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String storedFileName = storageService.uploadFile(file);

        FileEntity fileEntity = FileEntity.builder()
                .fileName(file.getOriginalFilename())
                .storedFileName(storedFileName)
                .fileType(file.getContentType())
                .fileSize(file.getSize())
                .s3Key(storedFileName)
                .fileUrl(
                        "https://" +
                                "cloudvault-yusuf-masood-2026.s3.ap-south-1.amazonaws.com/" +
                                storedFileName
                )
                .owner(user)
                .build();

        fileRepository.save(fileEntity);

        user.setStorageUsed(user.getStorageUsed() + file.getSize());
        userRepository.save(user);

        return "File uploaded successfully";
    }

    @Override
    public ResponseEntity<byte[]> downloadFile(Long fileId) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        FileEntity file = fileRepository.findByIdAndOwner(fileId, user)
                .orElseThrow(() -> new RuntimeException("File not found"));

        byte[] data = storageService.downloadFile(file.getStoredFileName());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFileName() + "\"")
                .body(data);
    }

    @Override
    public String deleteFile(Long fileId) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        FileEntity file = fileRepository.findByIdAndOwner(fileId, user)
                .orElseThrow(() -> new RuntimeException("File not found"));

        storageService.deleteFile(file.getStoredFileName());

        user.setStorageUsed(user.getStorageUsed() - file.getFileSize());
        userRepository.save(user);

        fileRepository.delete(file);

        return "File deleted successfully";
    }

    @Override
    public List<FileResponseDto> getMyFiles() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return fileRepository.findAllByOwner(user)
                .stream()
                .map(file -> FileResponseDto.builder()
                        .id(file.getId())
                        .fileName(file.getFileName())
                        .fileType(file.getFileType())
                        .fileSize(file.getFileSize())
                        .uploadedAt(file.getUploadedAt())
                        .build())
                .toList();
    }

    @Override
    public List<FileResponseDto> searchFiles(String keyword) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return fileRepository
                .findByOwnerAndFileNameContainingIgnoreCase(user, keyword)
                .stream()
                .map(file -> FileResponseDto.builder()
                        .id(file.getId())
                        .fileName(file.getFileName())
                        .fileType(file.getFileType())
                        .fileSize(file.getFileSize())
                        .uploadedAt(file.getUploadedAt())
                        .build())
                .toList();
    }
}