package com.groupt.reader.service;

import com.groupt.reader.dto.UserDto;
import com.groupt.reader.model.DriftingEntity;
import com.groupt.reader.repository.DriftingRepository;
import com.groupt.reader.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriftingService {
    @Autowired
    DriftingRepository driftingRepository;
    @Autowired
    UserRepository userRepository;

    public Long newDrifting(String bookName, String bookAuthor, String isbn, String place, String guardian) {
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        DriftingEntity drift = new DriftingEntity();
        drift.setBookName(bookName);
        drift.setAuthor(bookAuthor);
        drift.setIsbn(isbn);
        drift.setCurPosition(place);
        drift.setGuard(guardian);
        drift.setUid(userDto.getUid());
        drift.setAvailable(true);
        return driftingRepository.save(drift).getDriId();
    }

    public List<DriftingEntity> getSelfDrifting() {
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        return driftingRepository.findByUid(userDto.getUid());
    }

    public DriftingEntity getDriftingById(Long id) {
        return driftingRepository.findById(id).get();
    }

}
