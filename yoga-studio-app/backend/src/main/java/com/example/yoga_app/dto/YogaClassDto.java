package com.example.yoga_app.dto;

import com.example.yoga_app.entity.YogaClass;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class YogaClassDto {
    private UUID id;
    private String name;
    private String description;
    private String levelName;

    public static YogaClassDto fromEntity(YogaClass yogaClass) {
        return YogaClassDto.builder()
                .id(yogaClass.getId())
                .name(yogaClass.getName())
                .description(yogaClass.getDescription())
                .levelName(yogaClass.getLevel().getName())
                .build();
    }
}
