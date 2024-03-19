package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.entity.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageService {
    Image getImage(Long id);

    String getPathImage(Long id);

    Long saveUploadedFile(MultipartFile file);

    void deleteImage(Long id);
}
