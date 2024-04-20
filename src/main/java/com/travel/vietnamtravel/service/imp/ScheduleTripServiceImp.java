package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.place.sdi.PlaceSelfSdi;
import com.travel.vietnamtravel.dto.scheduletrip.sdi.*;
import com.travel.vietnamtravel.dto.scheduletrip.sdo.*;
import com.travel.vietnamtravel.dto.placeschedule.sdi.*;
import com.travel.vietnamtravel.dto.placeschedule.sdo.*;
import com.travel.vietnamtravel.entity.ScheduleTrip;
import com.travel.vietnamtravel.entity.relationship.PlaceSchedule;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.PlaceScheduleRepo;
import com.travel.vietnamtravel.repository.ScheduleTripRepo;
import com.travel.vietnamtravel.service.CommonService;
import com.travel.vietnamtravel.service.ImageService;
import com.travel.vietnamtravel.service.PlaceService;
import com.travel.vietnamtravel.service.ScheduleTripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.travel.vietnamtravel.constant.Error.*;
import static com.travel.vietnamtravel.util.DataUtil.copyProperties;
import static com.travel.vietnamtravel.util.DataUtil.isNullOrEmpty;
import static com.travel.vietnamtravel.util.DateTimeUtils.*;
import static com.travel.vietnamtravel.util.DateTimeConvert.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleTripServiceImp implements ScheduleTripService {
    private final ScheduleTripRepo scheduleTripRepo;
    private final PlaceScheduleRepo placeScheduleRepo;
    private final CommonService commonService;
    private final ImageService imageService;
    private final PlaceService placeService;

    public ScheduleTripCreateSdo create(ScheduleTripCreateSdi req) {

        ScheduleTrip scheduleTrip = copyProperties(req, ScheduleTrip.class);

        Long userId = commonService.getIdLogin();
        scheduleTrip.setCreatedBy(userId);

        if (req.getImageLabel() != null) {
            Long imageId = imageService.uploadFile(req.getImageLabel());
            scheduleTrip.setImageLabelId(imageId);
        }

        scheduleTripRepo.save(scheduleTrip);
        return ScheduleTripCreateSdo.of(scheduleTrip.getId());
    }

    public ScheduleTripUpdateSdo update(ScheduleTripUpdateSdi req) {

        ScheduleTrip scheduleTrip = getScheduleTrip(req.getId());
        scheduleTrip.setNameSchedule(req.getNameSchedule());
        scheduleTrip.setDescription(req.getDescription());
        scheduleTrip.setStartDate(req.getStartDate());
        scheduleTrip.setEndDate(req.getEndDate());

        if (req.getImageLabel() == null && scheduleTrip.getImageLabelId() != null) {
            imageService.delete(scheduleTrip.getImageLabelId());
            scheduleTrip.setImageLabelId(null);
        } else if (req.getImageLabel() != null) {
            Long imageId = imageService.uploadFile(req.getImageLabel());
            scheduleTrip.setImageLabelId(imageId);
        }

        scheduleTripRepo.save(scheduleTrip);
        return ScheduleTripUpdateSdo.of(Boolean.TRUE);
    }

    public ScheduleTripDeleteSdo delete(ScheduleTripDeleteSdi req) {
        ScheduleTrip scheduleTrip = getScheduleTrip(req.getId());
        scheduleTripRepo.save(scheduleTrip);
        return ScheduleTripDeleteSdo.of(Boolean.TRUE);
    }

    public ScheduleTripSelfSdo self(ScheduleTripSelfSdi req) {
        ScheduleTrip scheduleTrip = getScheduleTrip(req.getId());
        ScheduleTripSelfSdo res = copyProperties(scheduleTrip, ScheduleTripSelfSdo.class);
        List<Long> placeScheduleIds = placeScheduleRepo.findByScheduleId(scheduleTrip.getId());
        if (!isNullOrEmpty(placeScheduleIds)) {
            List<PlaceScheduleSelfSdo> places = new ArrayList<>();
            placeScheduleIds.stream()
                    .map(id -> selfPlace(PlaceScheduleSelfSdi.of(id)))
                    .forEach(places::add);
            res.setPlaces(places);
        }
        res.setStartDate(dateToString(scheduleTrip.getStartDate(), DATE_FORMAT));
        res.setUpdatedAt(dateTimeToString(scheduleTrip.getUpdatedAt(), DATE_TIME_FORMAT));
        res.setCreatedAt(dateTimeToString(scheduleTrip.getCreatedAt(), DATE_TIME_FORMAT));
        res.setUpdatedAt(dateTimeToString(scheduleTrip.getUpdatedAt(), DATE_TIME_FORMAT));
        return res;
    }

    public List<ScheduleTripSelfSdo> mySchedules() {
        Long userId = commonService.getIdLogin();
        List<Long> scheduleTripIds = scheduleTripRepo.findByUserId(userId);
        List<ScheduleTripSelfSdo> res = new ArrayList<>();
        scheduleTripIds.stream()
                .map(id -> self(ScheduleTripSelfSdi.of(id)))
                .forEach(res::add);
        return res;
    }

    public ScheduleTrip getScheduleTrip(Long id) {
        return scheduleTripRepo.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
    }

    public PlaceScheduleCreateSdo createPlace(PlaceScheduleCreateSdi req) {
        PlaceSchedule placeSchedule = copyProperties(req, PlaceSchedule.class);
        placeScheduleRepo.save(placeSchedule);
        return PlaceScheduleCreateSdo.of(placeSchedule.getId());
    }

    public PlaceScheduleUpdateSdo updatePlace(PlaceScheduleUpdateSdi req) {
        PlaceSchedule placeSchedule = getPlaceSchedule(req.getId());
        placeSchedule.setScheduleDate(req.getScheduleDate());
        placeSchedule.setPlaceId(req.getPlaceId());
        placeSchedule.setDescription(req.getDescription());
        placeScheduleRepo.save(placeSchedule);
        return PlaceScheduleUpdateSdo.of(Boolean.TRUE);
    }

    public PlaceScheduleDeleteSdo deletePlace(PlaceScheduleDeleteSdi req) {
        PlaceSchedule placeSchedule = getPlaceSchedule(req.getId());
        placeScheduleRepo.delete(placeSchedule);
        return PlaceScheduleDeleteSdo.of(Boolean.TRUE);
    }

    public PlaceScheduleSelfSdo selfPlace(PlaceScheduleSelfSdi req) {
        PlaceSchedule placeSchedule = getPlaceSchedule(req.getId());

        PlaceScheduleSelfSdo res = copyProperties(placeSchedule, PlaceScheduleSelfSdo.class);
        res.setPlaceSelf(placeService.self(PlaceSelfSdi.of(placeSchedule.getPlaceId())));
        return res;
    }

    public PlaceSchedule getPlaceSchedule(Long id) {
        return placeScheduleRepo.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));

    }
}
