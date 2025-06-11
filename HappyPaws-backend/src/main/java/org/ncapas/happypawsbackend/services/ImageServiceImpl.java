package org.ncapas.happypawsbackend.services;

import com.cloudinary.Cloudinary;
import org.ncapas.happypawsbackend.Domain.Entities.Image;
import org.ncapas.happypawsbackend.repositories.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService{

    private final CloudinaryService CloudinaryService;

    private final ImageRepository imageRepository;
    private final CloudinaryService cloudinaryService;

    private ImageServiceImpl(CloudinaryService CloudinaryService, ImageRepository imageRepository, CloudinaryService cloudinaryService) {
        this.CloudinaryService = CloudinaryService;
        this.imageRepository = imageRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public Image uploadImage(MultipartFile file) throws IDException{
        Map uploadResult = cloudinaryService.upload(file);
        String imageURL = (String) uploadResult.get("url");
        String image = (String) uploadResult.get("public_id");
        Image image = new Image(file.getOriginalFilename(), imageURL, imageId);
        return imageRepository.save(image);
    }

    @Override
    public void deleteImage(Image image) throws IDException {
        cloudinaryService.delete(image.getImageId());
        imageRepository.delete(image);
    }

}
