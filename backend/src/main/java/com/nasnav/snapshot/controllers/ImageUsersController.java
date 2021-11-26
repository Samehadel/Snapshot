package com.nasnav.snapshot.controllers;

import com.nasnav.snapshot.entities.Image;
import com.nasnav.snapshot.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/*
    * This controller focused on APIs that the registered users can consume.
    * This controller annotated with PreAuthorize to restrict the API consumption to users only
    *
 */


@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/image/users")
public class ImageUsersController {

    @Autowired
    private ImageService imageService;

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/upload")
    public ResponseEntity postImage(@RequestParam("file") MultipartFile upFile,
                                    @RequestParam("description") String description,
                                    @RequestParam("category") String category){

      long check = imageService.uploadImage(upFile, description, category);

      if(check > 0) {
          return ResponseEntity
                  .ok()
                  .body(check);
      }
      else if (check == -100) {
          return ResponseEntity
                  .status(HttpStatus.BAD_REQUEST)
                  .header("Diagnoses", "Image Format Must Be jpg, png or gif").build();
      }
      else {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
    }


    @GetMapping("/image/{id}")
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
    }



    @GetMapping
    public ResponseEntity getAllImagesForUsers(){
        List<Image> images = imageService.getAllImagesForUsers();

        for(Image img: images){
            img.getMetadata().setImage(null);
        }

        return ResponseEntity
                .ok()
                .body(images);
    }
}
