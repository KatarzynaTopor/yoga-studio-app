package com.example.yoga_app.repository;

import com.example.yoga_app.entity.YogaClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface YogaClassRepository extends JpaRepository<YogaClass, UUID> {
    Optional<YogaClass> findByName(String name);
}
