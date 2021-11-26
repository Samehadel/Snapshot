package com.nasnav.snapshot.controllers;

import com.nasnav.snapshot.entities.Image;
import com.nasnav.snapshot.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * This controller focused on APIs that the built-in admin can consume.
 * This controller annotated with PreAuthorize to restrict the API consumption to the admin only
 *
 */
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RestController
@RequestMapping("/image/admin")
@CrossOrigin(value = "http://localhost:3000", allowedHeaders = "*", exposedHeaders = "*")
public class ImageAdminController {

    @Autowired
    private ImageService imageService;

    @PutMapping("/accept/{id}")
    public void acceptImage(@PathVariable long id){
        imageService.acceptImage(id);
    }


    @DeleteMapping("/reject/{id}")
    public void rejectImage(@PathVariable long id){
        imageService.rejectImage(id);
    }

    @GetMapping
    public ResponseEntity getAllImagesForAdmin(){
        List<Image> images = imageService.getAllImagesForAdmin();

        for(Image img: images){
            img.getMetadata().setImage(null);
        }

        return ResponseEntity
                .ok()
                .body(images);
    }
}
