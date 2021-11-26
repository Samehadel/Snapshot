package com.nasnav.snapshot.entities;

import javax.persistence.*;

@Entity(name = "image")
public class Image {

    @Id
    @GeneratedValue
    private long id;

    private byte [] data;
    private String description;
    private String category;
    private String url;
    private boolean accepted = false;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE},
              mappedBy = "image")
    private ImageMetadata metadata;



    public Image() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ImageMetadata getMetadata() {
        return metadata;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public void setMetadata(ImageMetadata metadata) {
        this.metadata = metadata;
    }

}
