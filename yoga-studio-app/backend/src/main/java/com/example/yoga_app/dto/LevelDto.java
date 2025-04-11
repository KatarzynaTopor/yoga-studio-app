package com.example.yoga_app.dto;

import com.example.yoga_app.entity.Level;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LevelDto {
    private UUID id;
    private String name;
    private String description;

    public static LevelDto fromEntity(Level level) {
        return LevelDto.builder()
                .id(level.getId())
                .name(level.getName())
                .description(level.getDescription())
                .build();
    }

    public static Level toEntity(LevelDto dto) {
        Level level = new Level();
        level.setName(dto.getName());
        level.setDescription(dto.getDescription());
        return level;
    }
}
