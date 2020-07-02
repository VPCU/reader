package com.groupt.reader.service;

import com.groupt.reader.model.PermEntity;
import com.groupt.reader.repository.PermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    @Autowired
    private PermRepository permRepository;

    public void newPermission(Long id, String name, String val) {
        PermEntity p = new PermEntity();
        if(id != null) p.setPid(id);
        p.setPname(name);
        p.setPval(val);
        permRepository.save(p);
    }
}
