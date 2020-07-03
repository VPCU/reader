package com.groupt.reader.repository;

import com.groupt.reader.model.DriftingEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DriftingRepository extends JpaRepository<DriftingEntity, Long> {
    boolean existsByUidAndDriId(Long uid, Long driId);
    List<DriftingEntity> findByUid(Long uid);
    List<DriftingEntity> findByDriIdGreaterThanEqual(Long id, Pageable page);
    List<DriftingEntity> findByDriIdLessThanEqual(Long id, Pageable page);
}
