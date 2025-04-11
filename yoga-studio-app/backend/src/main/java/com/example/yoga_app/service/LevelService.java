package com.example.yoga_app.service;

import com.example.yoga_app.dto.LevelDto;
import com.example.yoga_app.entity.Level;
import com.example.yoga_app.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LevelService {

    private final LevelRepository levelRepository;

    public List<LevelDto> getAllLevels() {
        return levelRepository.findAll().stream()
                .map(LevelDto::fromEntity)
                .toList();
    }

    public Level addLevel(LevelDto dto) {
        Level level = LevelDto.toEntity(dto);
        return levelRepository.save(level);
    }
}
