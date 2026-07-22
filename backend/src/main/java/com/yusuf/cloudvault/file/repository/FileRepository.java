package com.yusuf.cloudvault.file.repository;

import com.yusuf.cloudvault.file.entity.FileEntity;
import com.yusuf.cloudvault.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.util.Optional;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

    Optional<FileEntity> findByIdAndOwner(Long id, User owner);

    List<FileEntity> findAllByOwner(User owner);

    List<FileEntity> findByOwnerAndFileNameContainingIgnoreCase(User owner, String keyword);

    long countByOwner(User owner);

    List<FileEntity> findTop5ByOwnerOrderByUploadedAtDesc(User owner);

}