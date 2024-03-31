package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.place.sdi.PlaceCreateSdi;
import com.travel.vietnamtravel.dto.place.sdi.PlaceDeleteSdi;
import com.travel.vietnamtravel.dto.place.sdi.PlaceSelfSdi;
import com.travel.vietnamtravel.dto.place.sdi.PlaceUpdateSdi;
import com.travel.vietnamtravel.dto.place.sdo.PlaceCreateSdo;
import com.travel.vietnamtravel.dto.place.sdo.PlaceDeleteSdo;
import com.travel.vietnamtravel.dto.place.sdo.PlaceSelfSdo;
import com.travel.vietnamtravel.dto.place.sdo.PlaceUpdateSdo;
import com.travel.vietnamtravel.entity.Place;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.PlaceRepo;
import com.travel.vietnamtravel.service.ImageService;
import com.travel.vietnamtravel.service.PlaceImageService;
import com.travel.vietnamtravel.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.travel.vietnamtravel.constant.Error.ERROR_NOT_EXIT;
import static com.travel.vietnamtravel.util.DataUtil.copyProperties;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceServiceImp implements PlaceService {
    private final PlaceRepo placeRepo;
    private final ImageService imageService;
    private final PlaceImageService placeImageService;

    public PlaceCreateSdo create(PlaceCreateSdi req) {
        Place place = copyProperties(req, Place.class);
        Long imageId = imageService.uploadFile(req.getImage());
        place.setImageId(imageId);
        placeRepo.save(place);
        return PlaceCreateSdo.of(place.getId());
    }

    public PlaceDeleteSdo delete(PlaceDeleteSdi req) {
        Place place = getPlace(req.getId());
        placeRepo.delete(place);
        return PlaceDeleteSdo.of(Boolean.TRUE);
    }

    public PlaceUpdateSdo update(PlaceUpdateSdi req) {

        Place place = getPlace(req.getId());

        if (!req.getImage().isEmpty()) {
            imageService.delete(place.getImageId());
            Long newImageId = imageService.uploadFile(req.getImage());
            place.setImageId(newImageId);
        }

        place.setName(req.getName());
        place.setDescription(req.getDescription());
        place.setWardCode(req.getWardCode());
        place.setDistrictCode(req.getDistrictCode());
        place.setProvinceCode(req.getProvinceCode());
        placeRepo.save(place);
        return PlaceUpdateSdo.of(Boolean.TRUE);

    }

    public PlaceSelfSdo self(PlaceSelfSdi req) {
        Place place = getPlace(req.getId());
        return copyProperties(place, PlaceSelfSdo.class);
    }

    public Place getPlace(Long id) {
        return placeRepo.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
    }

}
