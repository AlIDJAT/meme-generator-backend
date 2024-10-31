package com.meme.generator.memegeneratorapp.service;

import com.meme.generator.memegeneratorapp.entity.Meme;
import com.meme.generator.memegeneratorapp.repository.MemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemeService {

    private final MemeRepository memeRepository;

    @Autowired
    public MemeService(MemeRepository memeRepository) {
        this.memeRepository = memeRepository;
    }

    public Meme createMeme(Meme meme) {
        return memeRepository.save(meme);
    }

    public List<Meme> getAllMemes() {
        return memeRepository.findAll();
    }

    public Meme getMemeById(Long id) {
        return memeRepository.findById(id).orElse(null);
    }

    public void deleteMeme(Long id) {
        memeRepository.deleteById(id);
    }
}