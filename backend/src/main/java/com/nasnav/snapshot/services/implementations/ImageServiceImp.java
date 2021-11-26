package com.nasnav.snapshot.services.implementations;

import com.nasnav.snapshot.entities.Image;
import com.nasnav.snapshot.entities.ImageMetadata;
import com.nasnav.snapshot.entities.User;
import com.nasnav.snapshot.repositories.ImageMetadataRepository;
import com.nasnav.snapshot.repositories.ImageRepository;
import com.nasnav.snapshot.repositories.UserRepository;
import com.nasnav.snapshot.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImp implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageMetadataRepository imageMetadataRepository;

    @Override
    public long uploadImage(MultipartFile uploadedFile, String description, String category) {
        Image image = new Image();
        ImageMetadata metadata = new ImageMetadata();

        String originalFilename = uploadedFile.getOriginalFilename();

        if (originalFilename.equals(""))
            return -1;

        // Reject images with other formats
        if (!uploadedFile.getContentType().equals("image/jpg") &&
                !uploadedFile.getContentType().equals("image/jpeg") &&
                !uploadedFile.getContentType().equals("image/png") &&
                !uploadedFile.getContentType().equals("image/gif"))
            return -100;


        metadata.setImageName(StringUtils.cleanPath(originalFilename));
        metadata.setImageSize(uploadedFile.getSize());
        metadata.setImageFormat(uploadedFile.getContentType());

        try {
            byte[] data = uploadedFile.getBytes();

            image.setData(data);
            image.setDescription(description);
            image.setCategory(category);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        image.setMetadata(metadata);
        metadata.setImage(image);

        image = imageRepository.save(image);
        imageMetadataRepository.save(metadata);

        return image.getId();
    }

    @Override
    public void acceptImage(long id) {
        Optional<Image> optionalImage = imageRepository.findById(id);

        if (optionalImage.isPresent()) {
            Image image = optionalImage.get();
            image.setAccepted(true);
            image.setUrl("http://localhost:8082/image/users/image/" + image.getId());

            imageRepository.save(image);
        }
    }

    @Override
    @Transactional
    public void rejectImage(long id) {
        Optional<Image> optionalImage = imageRepository.findById(id);

        if (optionalImage.isPresent()) {
            Image image = optionalImage.get();
            image.getMetadata().setImage(null);
            image.setMetadata(null);

            imageRepository.delete(optionalImage.get());
        }
    }

    @Override
    public Image getImage(long id) {
        Optional<Image> optionalImage = imageRepository.findById(id);

        if (optionalImage.isPresent())
            return optionalImage.get();

        return null;
    }

    @Override
    public List<Image> getAllImagesForAdmin() {
        return imageRepository.getPendingImages();
    }
    @Override
    public List<Image> getAllImagesForUsers() {
        return (List<Image>) imageRepository.getAcceptedImages();
    }

}
