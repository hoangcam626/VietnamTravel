package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.place.sdi.PlaceDeleteSdi;
import com.travel.vietnamtravel.dto.placeimage.sdi.PlaceImageCreateSdi;
import com.travel.vietnamtravel.dto.placeimage.sdo.PlaceImageCreateSdo;
import com.travel.vietnamtravel.dto.placeimage.sdo.PlaceImageDeleteSdo;
import com.travel.vietnamtravel.entity.relationship.PlaceImage;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.PlaceImageRepo;
import com.travel.vietnamtravel.service.ImageService;
import com.travel.vietnamtravel.service.PlaceImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.travel.vietnamtravel.constant.Error.*;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceImageServiceImp implements PlaceImageService {
    private final PlaceImageRepo placeImageRepo;
    private final ImageService imageService;
    public PlaceImageCreateSdo create(PlaceImageCreateSdi req) {
        List<Long> imageIds = imageService.uploadFiles(req.getImages());
        imageIds.stream()
                .map(id->PlaceImage.builder()
                        .imageId(id)
                        .placeId(req.getPlaceId())
                        .build())
                .forEach(placeImageRepo::save);
        return PlaceImageCreateSdo.of(Boolean.TRUE);
    }



    public PlaceImageDeleteSdo delete(PlaceDeleteSdi req) {
        PlaceImage placeImage = getPlaceImage(req.getId());
        placeImageRepo.delete(placeImage);
        return PlaceImageDeleteSdo.of(Boolean.TRUE);
    }

    public PlaceImage getPlaceImage(Long id) {
        return placeImageRepo.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
    }
}
