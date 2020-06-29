package com.groupt.reader.repository;

import com.groupt.reader.model.PermEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermRepository extends JpaRepository<PermEntity, Long> {
}
