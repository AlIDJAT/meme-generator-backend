package com.meme.generator.memegeneratorapp.repository;

import com.meme.generator.memegeneratorapp.entity.Meme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemeRepository extends JpaRepository<Meme, Long> {
}
