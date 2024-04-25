package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.place.sdi.PlaceSelfSdi;
import com.travel.vietnamtravel.dto.placeschedule.sdi.*;
import com.travel.vietnamtravel.dto.placeschedule.sdo.*;
import com.travel.vietnamtravel.entity.relationship.PlaceSchedule;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.PlaceScheduleRepo;
import com.travel.vietnamtravel.service.PlaceScheduleService;
import com.travel.vietnamtravel.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.travel.vietnamtravel.constant.Error.ERROR_NOT_EXIT;
import static com.travel.vietnamtravel.util.DataUtil.copyProperties;
import static com.travel.vietnamtravel.util.DataUtil.isNullObject;
import static com.travel.vietnamtravel.util.DateTimeUtils.*;
import static com.travel.vietnamtravel.util.DateTimeConvert.*;


@Service
@Transactional
@RequiredArgsConstructor
public class PlaceScheduleImp implements PlaceScheduleService {
    private final PlaceScheduleRepo placeScheduleRepo;
    private final PlaceService placeService;

    public PlaceScheduleCreateSdo create(PlaceScheduleCreateSdi req) {
        PlaceSchedule placeSchedule = copyProperties(req, PlaceSchedule.class);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Chuyển đổi chuỗi thời gian sang LocalTime
//        placeSchedule.setScheduleFinishTime( LocalTime.parse(req.getScheduleFinishTime(), formatter));
//        placeSchedule.setScheduleBeginTime( LocalTime.parse(req.getScheduleBeginTime(), formatter));

        placeScheduleRepo.save(placeSchedule);
        return PlaceScheduleCreateSdo.of(placeSchedule.getId());
    }

    public PlaceScheduleUpdateSdo update(PlaceScheduleUpdateSdi req) {
        PlaceSchedule placeSchedule = getPlaceSchedule(req.getId());

        placeService.getPlace(req.getPlaceId());
        placeSchedule.setPlaceId(req.getPlaceId());

        placeSchedule.setDescription(req.getDescription());
        placeSchedule.setScheduleDate(req.getScheduleDate());
        placeScheduleRepo.save(placeSchedule);

        return PlaceScheduleUpdateSdo.of(Boolean.TRUE);
    }

    public PlaceScheduleDeleteSdo delete(PlaceScheduleDeleteSdi req) {
        PlaceSchedule placeSchedule = getPlaceSchedule(req.getId());
        placeScheduleRepo.delete(placeSchedule);
        return PlaceScheduleDeleteSdo.of(Boolean.TRUE);
    }

    public PlaceScheduleSelfSdo self(PlaceScheduleSelfSdi req) {
        PlaceSchedule placeSchedule = getPlaceSchedule(req.getId());
        PlaceScheduleSelfSdo res = copyProperties(placeSchedule, PlaceScheduleSelfSdo.class);
        res.setUpdatedAt(dateTimeToString(placeSchedule.getUpdatedAt(), DATE_TIME_FORMAT));
        res.setCreatedAt(dateTimeToString(placeSchedule.getCreatedAt(), DATE_TIME_FORMAT));
        res.setScheduledDate(dateToString(placeSchedule.getScheduleDate(), DATE_FORMAT));
        res.setScheduleBeginTime(timeToString(placeSchedule.getScheduleBeginTime(), "HH:mm"));
        res.setScheduleBeginTime(timeToString(placeSchedule.getScheduleBeginTime(), "HH:mm"));
        if (!isNullObject(placeSchedule.getPlaceId())) {
            res.setPlace(placeService.shortSelf(placeSchedule.getPlaceId()));
        }
        return res;
    }

    public PlaceCompleteSdo isComplete(PlaceCompleteSdi req) {
        PlaceSchedule placeSchedule = getPlaceSchedule(req.getPlaceScheduleId());
        placeSchedule.setIsComplete(Boolean.TRUE);
        placeScheduleRepo.save(placeSchedule);
        return PlaceCompleteSdo.of(Boolean.TRUE);
    }

    public List<PlaceScheduleSelfSdo> placesInScheduleOnDate(PlaceScheduleJoinSdi req) {

        List<Long> placeScheduleIds = placeScheduleRepo.findByScheduleIdAAndScheduleDate(req.getScheduleId(), req.getDate());
        List<PlaceScheduleSelfSdo> res = new ArrayList<>();
        placeScheduleIds.stream()
                .map(id -> self(PlaceScheduleSelfSdi.of(id)))
                .forEach(res::add);
        return res;
    }

    public PlaceSchedule getPlaceSchedule(Long id) {
        return placeScheduleRepo.findById(id).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
    }
}
