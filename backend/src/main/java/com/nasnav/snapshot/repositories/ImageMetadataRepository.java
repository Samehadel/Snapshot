package com.nasnav.snapshot.repositories;

import com.nasnav.snapshot.entities.ImageMetadata;
import org.springframework.data.repository.CrudRepository;

public interface ImageMetadataRepository extends CrudRepository<ImageMetadata, Long> {
}
