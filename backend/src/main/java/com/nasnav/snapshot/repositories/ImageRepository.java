package com.nasnav.snapshot.repositories;

import com.nasnav.snapshot.entities.Image;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {

    @Modifying
    @Query("UPDATE image i SET i.accepted=true WHERE i.id=:id")
    public void acceptImage(@Param("id") long id);

    @Modifying
    @Query("DELETE FROM image i WHERE i.id=:id")
    public void deleteImageById(@Param("id") long id);

    @Query("SELECT img FROM image img WHERE img.accepted=True")
    public List<Image> getAcceptedImages();

    @Query("SELECT img FROM image img WHERE img.accepted=false")
    public List<Image> getPendingImages();

}
