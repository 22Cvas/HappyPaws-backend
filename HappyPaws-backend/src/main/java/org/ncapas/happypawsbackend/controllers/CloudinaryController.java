package org.ncapas.happypawsbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.ncapas.happypawsbackend.Domain.Entities.Image;
import org.ncapas.happypawsbackend.Domain.dtos.ImageDTO;
import org.ncapas.happypawsbackend.services.CloudinaryService;
import org.ncapas.happypawsbackend.services.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
public class CloudinaryController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ImageDTO> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            Image image = imageService.uploadImage(file);
            ImageDTO dto = new ImageDTO(image.getName(), image.getImgURL(), image.getImageId());
            return ResponseEntity.ok(dto);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}


