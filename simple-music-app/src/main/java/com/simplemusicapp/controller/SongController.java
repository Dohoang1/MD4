package com.simplemusicapp.controller;

import com.simplemusicapp.model.Song;
import com.simplemusicapp.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @Value("${file-upload}")
    private String fileUploadPath;

    @GetMapping
    public String listSongs(Model model) {
        List<Song> songs = songService.findAll();
        model.addAttribute("songs", songs);
        return "song/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("song", new Song());
        return "song/add";
    }

    @PostMapping("/add")
    public String addSong(@ModelAttribute Song song, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                Path path = Paths.get(fileUploadPath + fileName);
                Files.write(path, file.getBytes());
                song.setFilePath(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        songService.save(song);
        return "redirect:/songs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Song song = songService.findById(id);
        model.addAttribute("song", song);
        return "song/edit";
    }

    @PostMapping("/edit")
    public String editSong(@ModelAttribute Song song, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                Path path = Paths.get(fileUploadPath + fileName);
                Files.write(path, file.getBytes());
                song.setFilePath(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        songService.update(song);
        return "redirect:/songs";
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable int id) {
        Song song = songService.findById(id);
        if (song != null && song.getFilePath() != null) {
            File file = new File(fileUploadPath + song.getFilePath());
            if (file.exists()) {
                file.delete();
            }
        }
        songService.delete(id);
        return "redirect:/songs";
    }
}