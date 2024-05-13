package com.vutheduyet.exercise03.service;

import java.util.List;

import com.vutheduyet.exercise03.entity.Gallery;

public interface GalleryService {
    Gallery createGallery(Gallery gallery);
    Gallery getGalleryById(String galleryId);
    List<Gallery> getAllGalleries();
    List<Gallery> getGalleriesByProductId(String productId);
    Gallery updateGallery(Gallery gallery);
    void deleteGallery(String galleryId);
    void deleteGalleriesByProductId(String productId);
}