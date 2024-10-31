package com.meme.generator.memegeneratorapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.UUID;

@Service
public class ImageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String saveImageWithText(MultipartFile file, String topText, String bottomText) throws IOException {
        String fileName = UUID.randomUUID() + "_" + cleanPath(file.getOriginalFilename());
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Charger l'image et ajouter du texte
        try (InputStream inputStream = file.getInputStream()) {
            BufferedImage image = ImageIO.read(inputStream);

            // Si l'image a été chargée correctement
            if (image != null) {
                Graphics2D g = image.createGraphics();
                g.setFont(new Font("Arial", Font.BOLD, 36));
                g.setColor(Color.WHITE);
                g.setStroke(new BasicStroke(2));

                // Dessiner le texte en haut
                g.drawString(topText, image.getWidth() / 4, 50);

                // Dessiner le texte en bas
                g.drawString(bottomText, image.getWidth() / 4, image.getHeight() - 30);

                g.dispose();

                // Sauvegarder l'image modifiée
                Path filePath = uploadPath.resolve(fileName);
                ImageIO.write(image, "png", filePath.toFile());

                // Retourner l'URL publique
                return "http://localhost:8080/" + fileName;
            } else {
                throw new IOException("Impossible de lire le fichier image.");
            }
        } catch (IOException e) {
            throw new IOException("Impossible de sauvegarder le fichier image : " + fileName, e);
        }
    }


    private String cleanPath(String path) {
        if (path == null) {
            return "";
        }
        return StringUtils.cleanPath(path);
    }
}
