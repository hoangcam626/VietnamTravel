package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.image.sdo.ImageSdo;
import com.travel.vietnamtravel.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/api/v1/image/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("req") MultipartFile req) {

        Long imgId = imageService.uploadFile(req);
        return ResponseEntity.ok("Success: " + imgId);
    }

    @PostMapping("/api/v1/image/upload-images")
    public ResponseEntity<List<Long>> uploadImages(@RequestParam("reqs") MultipartFile[] reqs) {

        return ResponseEntity.ok(imageService.uploadFiles(reqs));
    }

    @GetMapping("/api/image")
    public ResponseEntity<Resource> getImage(@RequestParam("imageId") Long id) throws IOException {

        ImageSdo imageSdo = imageService.getResource(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, imageSdo.getMediaType())
                .body(imageSdo.getResource());
    }
}
