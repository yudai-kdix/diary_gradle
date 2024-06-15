package com.example.diary.repositories;

import com.example.diary.models.DiaryEntries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<DiaryEntries, Integer> {
}
