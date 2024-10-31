package com.meme.generator.memegeneratorapp.controller;

import com.meme.generator.memegeneratorapp.entity.Meme;
import com.meme.generator.memegeneratorapp.service.ImageService;
import com.meme.generator.memegeneratorapp.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/memes")
public class MemeController {

    private final MemeService memeService;
    private final ImageService imageService;

    @Autowired
    public MemeController(MemeService memeService, ImageService imageService) {
        this.memeService = memeService;
        this.imageService = imageService;
    }

    @PostMapping(consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meme> createMeme(@RequestBody Meme meme) {
        Meme createdMeme = memeService.createMeme(meme);
        return new ResponseEntity<>(createdMeme, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Meme>> getAllMemes() {
        List<Meme> memes = memeService.getAllMemes();
        return new ResponseEntity<>(memes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meme> getMemeById(@PathVariable Long id) {
        Meme meme = memeService.getMemeById(id);
        if (meme != null) {
            return new ResponseEntity<>(meme, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeme(@PathVariable Long id) {
        memeService.deleteMeme(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadImage(
            @RequestParam("image") MultipartFile file,
            @RequestParam("topText") String topText,
            @RequestParam("bottomText") String bottomText) {
        try {
            String imageUrl = imageService.saveImageWithText(file, topText, bottomText);

            Map<String, String> response = new HashMap<>();
            response.put("imageUrl", imageUrl);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Échec du téléchargement de l'image"));
        }
    }


}