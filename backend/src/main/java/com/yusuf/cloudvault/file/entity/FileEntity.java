package com.yusuf.cloudvault.file.entity;

import com.yusuf.cloudvault.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Original file name
    @Column(nullable = false)
    private String fileName;

    // Unique name stored in S3
    @Column(nullable = false, unique = true)
    private String storedFileName;

    // MIME type
    @Column(nullable = false)
    private String fileType;

    // Size in bytes
    @Column(nullable = false)
    private Long fileSize;

    // S3 Object Key
    @Column(nullable = false, unique = true)
    private String s3Key;

    // Public/Presigned URL
    private String fileUrl;

    @CreationTimestamp
    private LocalDateTime uploadedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;
}