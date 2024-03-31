package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.place.sdi.PlaceDeleteSdi;
import com.travel.vietnamtravel.dto.placeimage.sdi.PlaceImageCreateSdi;
import com.travel.vietnamtravel.dto.placeimage.sdi.PlaceImageUpdateSdi;
import com.travel.vietnamtravel.dto.placeimage.sdo.PlaceImageCreateSdo;
import com.travel.vietnamtravel.dto.placeimage.sdo.PlaceImageDeleteSdo;
import com.travel.vietnamtravel.dto.placeimage.sdo.PlaceImageUpdateSdo;
import com.travel.vietnamtravel.entity.relationship.PlaceImage;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.PlaceImageRepo;
import com.travel.vietnamtravel.service.PlaceImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.travel.vietnamtravel.constant.Error.*;
import static com.travel.vietnamtravel.util.DataUtil.copyProperties;

@Service
@RequiredArgsConstructor
public class PlaceImageServiceImp implements PlaceImageService {
    private final PlaceImageRepo placeImageRepo;

    public PlaceImageCreateSdo create(PlaceImageCreateSdi req) {
        if (placeImageRepo.existsByImageId(req.getImageId())) {
            throw new CustomException(ERROR_ALREADY_EXIT);
        }
        PlaceImage placeImage = copyProperties(req, PlaceImage.class);
        placeImageRepo.save(placeImage);
        return PlaceImageCreateSdo.of(placeImage.getId());
    }

    public PlaceImageUpdateSdo update(PlaceImageUpdateSdi req) {
        PlaceImage placeImage = getPlaceImage(req.getId());
        placeImage.setPlaceId(req.getPlaceId());
        placeImageRepo.save(placeImage);
        return PlaceImageUpdateSdo.of(Boolean.TRUE);
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
