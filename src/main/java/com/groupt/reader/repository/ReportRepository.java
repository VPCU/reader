package com.groupt.reader.repository;

import com.groupt.reader.model.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
    List<ReportEntity> getAllByDone(Boolean done);
}
