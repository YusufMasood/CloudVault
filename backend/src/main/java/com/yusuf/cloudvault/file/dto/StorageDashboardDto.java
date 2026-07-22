package com.yusuf.cloudvault.file.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StorageDashboardDto {

    private long totalFiles;

    private long storageUsed;

    private long storageLimit;

    private long remainingStorage;

    private List<FileResponseDto> recentFiles;
}