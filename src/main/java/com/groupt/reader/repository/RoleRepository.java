package com.groupt.reader.repository;

import com.groupt.reader.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByRval(String rval);
}
