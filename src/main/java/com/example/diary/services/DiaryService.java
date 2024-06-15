package com.example.diary.services;

import com.example.diary.models.DiaryEntries;
import com.example.diary.repositories.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    public List<DiaryEntries> findAll() {
        return diaryRepository.findAll();
    }

    public Optional<DiaryEntries> findById(Integer id) {
        return diaryRepository.findById(id);
    }

    public DiaryEntries save(DiaryEntries diary) {
        return diaryRepository.save(diary);
    }

    public void deleteById(Integer id) {
        diaryRepository.deleteById(id);
    }
}
