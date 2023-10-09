package com.lemoncat.tbusersservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class WebpService {

    public String convertToWebp(MultipartFile image) throws IOException {
        return this.convertToWebp(ImageIO.read(image.getInputStream()));
    }

    public String convertToWebp(BufferedImage inputImage) throws IOException {
        String filename = UUID.randomUUID() + ".webp";
        File outputDir = this.getOutputDir(filename);
        File outputImage = new File(outputDir.getAbsolutePath() + "/" + filename);

        ImageIO.write(inputImage, "webp", outputImage);

        return filename;
    }

    private File getOutputDir(String filename) {
        File directory = new File("tb-users-service/src/main/resources/images/" + filename.substring(0,3));

        if(!directory.exists()) {
            if(!directory.mkdir()) {
                throw new RuntimeException("Cant create folder to save image" + directory.getAbsolutePath());
            }
        }

        return directory;
    }

    public void deleteImage(String filename) {
        File directory = this.getOutputDir(filename);

        File image = new File(directory.getAbsolutePath() + "/" + filename);

        if(!image.delete()) {
            throw new RuntimeException("Cant delete image");
        }
    }
}
