package com.musicplayer.controller;

import com.musicplayer.model.Song;
import com.musicplayer.model.SongForm;
import com.musicplayer.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/songs")
public class SongController {

    @Value("${file-upload}")
    private String upload;

    @Autowired
    private SongService songService;

    @GetMapping
    public String listSongs(Model model) {
        List<Song> songs = songService.getAllSongs();
        model.addAttribute("songs", songs);
        return "song/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("song", new SongForm());
        return "song/add";
    }

    @PostMapping("/add")
    public String addSong(@ModelAttribute Song song, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path uploadPath = Paths.get(upload);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                song.setFilePath(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        songService.addSong(song);
        return "redirect:/songs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Song song = songService.getSongById(id);
        if (song == null) {
            return "redirect:/songs";
        }
        model.addAttribute("song", song);
        return "song/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateSong(@PathVariable int id, @ModelAttribute Song song, @RequestParam("file") MultipartFile file) {
        Song existingSong = songService.getSongById(id);
        if (existingSong == null) {
            return "redirect:/songs";
        }
        if (!file.isEmpty()) {
            try {
                if (existingSong.getFilePath() != null) {
                    Path oldPath = Paths.get(upload + existingSong.getFilePath());
                    Files.deleteIfExists(oldPath);
                }
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path path = Paths.get(upload + fileName);
                Files.write(path, file.getBytes());
                song.setFilePath(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            song.setFilePath(existingSong.getFilePath());
        }
        existingSong.setName(song.getName());
        existingSong.setArtist(song.getArtist());
        existingSong.setGenre(song.getGenre());
        existingSong.setFilePath(song.getFilePath());
        songService.updateSong(existingSong);
        return "redirect:/songs";
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable int id) {
        Song song = songService.getSongById(id);
        if (song != null && song.getFilePath() != null) {
            try {
                Path path = Paths.get(upload + song.getFilePath());
                Files.deleteIfExists(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        songService.deleteSong(id);
        return "redirect:/songs";
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Song getSongInfo(@PathVariable int id) {
        return songService.getSongById(id);
    }
}