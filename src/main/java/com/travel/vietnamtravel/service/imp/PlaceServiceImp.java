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
import com.travel.vietnamtravel.util.DataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.travel.vietnamtravel.constant.Error.ERROR_NOT_EXIT;

@Service
@RequiredArgsConstructor
public class PlaceServiceImp {
    private final PlaceRepo placeRepo;

    public PlaceCreateSdo create(PlaceCreateSdi req) {

    }

    public PlaceDeleteSdo delete(PlaceDeleteSdi req) {
        Place place = getPlace(req.getId());
        placeRepo.delete(place);
        return PlaceDeleteSdo.of(Boolean.TRUE);
    }

    public PlaceUpdateSdo update(PlaceUpdateSdi req) {
        Place place = DataUtil.copyProperties(req, Place.class);
        return PlaceUpdateSdo.of(Boolean.TRUE);

    }

    public PlaceSelfSdo self(PlaceSelfSdi req) {
        Place place = getPlace(req.getId());

    }

    public Place getPlace(Long id) {
        return placeRepo.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
    }

}
