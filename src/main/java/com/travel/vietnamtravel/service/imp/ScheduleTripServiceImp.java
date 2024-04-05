package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.scheduletrip.sdi.*;
import com.travel.vietnamtravel.dto.scheduletrip.sdo.*;
import com.travel.vietnamtravel.entity.ScheduleTrip;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.ScheduleTripRepo;
import com.travel.vietnamtravel.service.CommonService;
import com.travel.vietnamtravel.service.ImageService;
import com.travel.vietnamtravel.service.ScheduleTripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.travel.vietnamtravel.constant.Error.ERROR_NOT_EXIT;
import static com.travel.vietnamtravel.util.DataUtil.copyProperties;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleTripServiceImp  implements ScheduleTripService {
    private final ScheduleTripRepo scheduleTripRepo;
    private final CommonService commonService;
    private final ImageService imageService;

    public ScheduleTripCreateSdo create(ScheduleTripCreateSdi req) {

        ScheduleTrip scheduleTrip = copyProperties(req, ScheduleTrip.class);

        Long userId = commonService.getIdLogin();
        scheduleTrip.setUserId(userId);

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
        return res;
    }

    public List<ScheduleTripSelfSdo> mySchedules(){
        Long userId=commonService.getIdLogin();
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
}
