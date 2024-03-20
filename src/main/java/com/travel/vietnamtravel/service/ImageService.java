package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.entity.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ImageService {
    Image getImage(Long id);

    String getPathImage(Long id);

    Long uploadFile(MultipartFile req);
    List<Long> uploadFiles(MultipartFile[] reqs);

    void deleteImage(Long id);
}
