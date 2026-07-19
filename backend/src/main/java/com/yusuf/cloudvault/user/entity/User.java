package com.yusuf.cloudvault.user.entity;

import jakarta.persistence.*;
import lombok.*;
import com.yusuf.cloudvault.file.entity.FileEntity;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private static final long FREE_STORAGE_LIMIT = 10L * 1024 * 1024 * 1024;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder.Default
    @Column(nullable = false)
    private Long storageUsed = 0L;

    @Builder.Default
    @Column(nullable = false)
    private Long storageLimit = FREE_STORAGE_LIMIT;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "owner")
    private List<FileEntity> files = new ArrayList<>();
}