package com.nasnav.snapshot.services;

import com.nasnav.snapshot.entities.Image;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ImageService{
    public long uploadImage(MultipartFile uploadedFile, String description, String category);
    public void acceptImage(long id);
    public void rejectImage(long id);
    public Image getImage(long id);
    public List<Image> getAllImagesForAdmin();
    public List<Image> getAllImagesForUsers();

}
