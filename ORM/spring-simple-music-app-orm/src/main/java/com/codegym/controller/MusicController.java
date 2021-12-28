package com.codegym.controller;

import com.codegym.model.Music;
import com.codegym.model.MusicForm;
import com.codegym.service.IMusicService;
import com.codegym.service.MusicService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/music")
public class MusicController {
    private final IMusicService musicService = new MusicService();

    @Value("${file-upload}")
    private String fileUpload;

    @GetMapping("")
    public String home(Model model) {
        List<Music> musicList = musicService.findAll();
        model.addAttribute("musicList",musicList);
        return "/index";
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        response.setContentType("text/html/charset=UTF-8");
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("musicForm", new MusicForm());
        String[] styles = new String[]{"Nhạc vàng", "Nhạc đỏ", "Nhạc cách mạng", "Nhạc trẻ", "Nhạc thiếu nhi"};
        modelAndView.addObject("styles", styles);
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveProduct(@ModelAttribute MusicForm musicForm) {
        ModelAndView modelAndView = new ModelAndView("/create");
        MultipartFile multipartFile = musicForm.getFilePath();
        String fileName = multipartFile.getOriginalFilename();
        String[] prefix = new String[]{".mp3",".wav",".ogg",".mp4"};
        boolean isMusicFile = false;
        for (String str: prefix
        ) {
            if (fileName.substring(fileName.indexOf(".")).equals(str)) {
                isMusicFile = true;
            }
        }
        if (!isMusicFile) {
            modelAndView.addObject("message", "File is not music file !");
        } else {
            try {
                FileCopyUtils.copy(musicForm.getFilePath().getBytes(), new File(fileUpload + fileName));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Music music = new Music( musicForm.getName(),
                    musicForm.getStyle(), musicForm.getSinger(), fileName);
            Music saveMusic = musicService.save(music);
            System.out.println(saveMusic.getFilePath());
            modelAndView.addObject("musicForm",musicForm);
            modelAndView.addObject("message", "Created new music successfully !");
        }

        return modelAndView;
    }

}
