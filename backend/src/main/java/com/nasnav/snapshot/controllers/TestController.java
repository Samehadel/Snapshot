package com.nasnav.snapshot.controllers;

import com.nasnav.snapshot.entities.Image;
import com.nasnav.snapshot.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private ImageService imageService;

    /*@GetMapping("/image/users/image/{id}")
    public ResponseEntity getImage(@PathVariable long id){
        Image image = imageService.getImage(id);

        if(image == null)
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).header("Diagnoses", "Image Not Found")
                    .build();

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(image.getMetadata().getImageFormat()))
                .body(image.getData());
    }*/
}
