package org.ncapas.happypawsbackend.services;

import org.ncapas.happypawsbackend.Domain.Entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    Image uploadImage(MultipartFile file) throws IOException, IDException;

    void deleteImage(Image image) throws IOException;
}
