package com.nasnav.snapshot.entities;

import javax.persistence.*;

@Entity
public class ImageMetadata {

    @Id
    @GeneratedValue
    private long id;

    private String imageName;
    private long imageSize;
    private String imageFormat;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    public ImageMetadata() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public long getImageSize() {
        return imageSize;
    }

    public void setImageSize(long imageSize) {
        this.imageSize = imageSize;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
