package com.yusuf.cloudvault.file.controller;

import com.yusuf.cloudvault.file.dto.FileResponseDto;
import com.yusuf.cloudvault.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file
    ) {

        return ResponseEntity.ok(fileService.uploadFile(file));

    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {

        return fileService.downloadFile(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        return ResponseEntity.ok(fileService.deleteFile(id));
    }

    @GetMapping
    public ResponseEntity<List<FileResponseDto>> getMyFiles() {

        return ResponseEntity.ok(fileService.getMyFiles());
    }

    @GetMapping("/search")
    public ResponseEntity<List<FileResponseDto>> searchFiles(
            @RequestParam String keyword) {

        return ResponseEntity.ok(fileService.searchFiles(keyword));
    }

}