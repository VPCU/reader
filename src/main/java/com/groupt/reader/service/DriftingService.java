package com.groupt.reader.service;

import com.groupt.reader.dto.BookReviewDto;
import com.groupt.reader.dto.UserDto;
import com.groupt.reader.mapper.BookReviewMapper;
import com.groupt.reader.model.BookReviewEntity;
import com.groupt.reader.model.DriftingEntity;
import com.groupt.reader.repository.DriftingRepository;
import com.groupt.reader.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public boolean borrowById(Long id) {
        UserDto userDto = (UserDto) SecurityUtils.getSubject().getPrincipal();
        Optional<DriftingEntity> _drift = driftingRepository.findById(id);
        if(!_drift.isPresent()) return false;
        DriftingEntity drifting = _drift.get();
        if(!drifting.getAvailable()) return false;
        drifting.setAvailable(false);
        drifting.setCurUid(userDto.getUid());
        return true;
    }

    public List<DriftingEntity> getDrifting(int cursor, int limit, boolean desc) {
        List<DriftingEntity> driftings;
        if(!desc) {
            Sort sort = Sort.sort(DriftingEntity.class).by(DriftingEntity::getDriId).ascending();
            Pageable pageable = PageRequest.of(0, limit, sort);
            driftings = driftingRepository.findByDriIdGreaterThanEqual((long) cursor, pageable);
        } else {
            Sort sort = Sort.sort(DriftingEntity.class).by(DriftingEntity::getDriId).descending();
            Pageable pageable = PageRequest.of(0, limit, sort);
            driftings = driftingRepository.findByDriIdLessThanEqual((long) cursor, pageable);
        }
        return driftings;
    }
}
