package com.vutheduyet.exercise03.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.vutheduyet.exercise03.entity.Gallery;
import com.vutheduyet.exercise03.repository.GalleryRepository;
import com.vutheduyet.exercise03.service.GalleryService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GalleryServiceImpl implements GalleryService {
    private GalleryRepository galleryRepository;

    @Override
    public Gallery createGallery(Gallery gallery) {
        return galleryRepository.save(gallery);
    }

    @Override
    public Gallery getGalleryById(String galleryId) {
        Optional<Gallery> optionalGallery = galleryRepository.findById(galleryId);
        return optionalGallery.orElse(null);
    }

    @Override
    public List<Gallery> getAllGalleries() {
        return galleryRepository.findAll();
    }

    @Override
    public Gallery updateGallery(Gallery gallery) {
        Optional<Gallery> optionalExistingGallery = galleryRepository.findById(gallery.getGalleryId());
        if (optionalExistingGallery.isPresent()) {
            Gallery existingGallery = optionalExistingGallery.get();
            existingGallery.setImagePath(gallery.getImagePath());
            existingGallery.setThumbnail(gallery.isThumbnail());
            existingGallery.setPlaceholder(gallery.getPlaceholder());
 

            existingGallery.setUpdatedAt(LocalDateTime.now());

            Gallery updatedGallery = galleryRepository.save(existingGallery);
            return updatedGallery;
        }

        return null;
    }

    @Override
    public void deleteGallery(String galleryId) {
        galleryRepository.deleteById(galleryId);
    }

    @Override
    public void deleteGalleriesByProductId(String productId) {
        List<Gallery> galleries = galleryRepository.findByProductProductId(productId);
        galleryRepository.deleteAll(galleries);
    }

    @Override
    public List<Gallery> getGalleriesByProductId(String productId) {
        return galleryRepository.findByProductProductId(productId);
    }

}
