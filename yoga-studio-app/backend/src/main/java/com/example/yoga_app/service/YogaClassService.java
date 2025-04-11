package com.example.yoga_app.service;

import com.example.yoga_app.dto.YogaClassDto;
import com.example.yoga_app.entity.YogaClass;
import com.example.yoga_app.repository.YogaClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YogaClassService {

    private final YogaClassRepository yogaClassRepository;

    public List<YogaClassDto> getAll() {
        return yogaClassRepository.findAll()
                .stream()
                .map(YogaClassDto::fromEntity)
                .toList();
    }

    public YogaClass save(YogaClass yogaClass) {
        return yogaClassRepository.save(yogaClass);
    }
}
