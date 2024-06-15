package com.example.diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.diary.models.DiaryEntries;
import com.example.diary.services.DiaryService;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("diaries", diaryService.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String addDiaryForm(Model model) {
        model.addAttribute("diary", new DiaryEntries());
        return "add_diary";
    }

    @PostMapping("/save")
    public String saveDiary(@RequestParam("title") String title, @RequestParam("content") String content) {
        DiaryEntries diary = new DiaryEntries(title, content, LocalDateTime.now());
        diaryService.save(diary);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteDiary(@RequestParam("id") Integer id) {
        diaryService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/show")
    public String viewDiary(@RequestParam("id") Integer id, Model model) {
        Optional<DiaryEntries> diary = diaryService.findById(id);
        diary.ifPresent(value -> model.addAttribute("diary", value));
        return diary.isPresent() ? "view_diary" : "redirect:/";
    }

    @GetMapping("/edit")
    public String editDiaryForm(@RequestParam("id") Integer id, Model model) {
        Optional<DiaryEntries> diary = diaryService.findById(id);
        diary.ifPresent(value -> model.addAttribute("diary", value));
        return diary.isPresent() ? "edit_diary" : "redirect:/";
    }

    @PostMapping("/update")
    public String updateDiary(@RequestParam("id") Integer id, @RequestParam("title") String title,
            @RequestParam("content") String content) {
        Optional<DiaryEntries> optionalDiary = diaryService.findById(id);
        if (optionalDiary.isPresent()) {
            DiaryEntries diary = optionalDiary.get();
            diary.setTitle(title);
            diary.setContent(content);
            diaryService.save(diary);
        }
        return "redirect:/";
    }

}
