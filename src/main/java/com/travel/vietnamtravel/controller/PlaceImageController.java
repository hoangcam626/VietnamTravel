package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.placeimage.sdi.PlaceImageCreateSdi;
import com.travel.vietnamtravel.dto.placeimage.sdi.PlaceImageJoinSdi;
import com.travel.vietnamtravel.dto.placeimage.sdo.PlaceImageCreateSdo;
import com.travel.vietnamtravel.service.PlaceImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/place-image")
public class PlaceImageController {

    private final PlaceImageService placeImageService;
    @PostMapping("/create")
    public ResponseEntity<PlaceImageCreateSdo> create(PlaceImageCreateSdi req){
        return ResponseEntity.ok(placeImageService.create(req));
    }
    @PostMapping("/images")
    public ResponseEntity<List<Long>> getImagePlace(PlaceImageJoinSdi req){
        return ResponseEntity.ok(placeImageService.getImagePlace(req));
    }
}
