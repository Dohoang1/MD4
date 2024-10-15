package com.simpledictionary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DictionaryController {

    private Map<String, String> dictionary;

    public DictionaryController() {
        dictionary = new HashMap<>();
        dictionary.put("hello", "xin chào");
        dictionary.put("world", "thế giới");
        dictionary.put("computer", "máy tính");
        dictionary.put("book", "sách");
        dictionary.put("apple", "tao");
    }

    @GetMapping("/dictionary")
    public String showDictionaryPage() {
        return "dictionary";
    }

    @PostMapping("/search")
    public String searchWord(@RequestParam("word") String word, Model model) {
        String result = searchInDictionary(word);
        model.addAttribute("result", result);
        return "dictionary";
    }

    private String searchInDictionary(String word) {
        word = word.toLowerCase().trim();
        if (dictionary.containsKey(word)) {
            return "Nghĩa của từ '" + word + "' là: " + dictionary.get(word);
        }
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(word)) {
                return "Từ tiếng Anh cho '" + word + "' là: " + entry.getKey();
            }
        }
        return "Không tìm thấy từ hoặc nghĩa '" + word + "' trong từ điển.";
    }
}