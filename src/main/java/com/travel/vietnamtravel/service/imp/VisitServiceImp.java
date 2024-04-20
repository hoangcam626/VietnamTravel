package com.travel.vietnamtravel.service.imp;

import com.travel.vietnamtravel.dto.place.sdi.PlaceSelfSdi;
import com.travel.vietnamtravel.dto.visit.sdi.VisitCreateSdi;
import com.travel.vietnamtravel.dto.visit.sdi.VisitDeleteSdi;
import com.travel.vietnamtravel.dto.visit.sdi.VisitSelfSdi;
import com.travel.vietnamtravel.dto.visit.sdo.VisitCreateSdo;
import com.travel.vietnamtravel.dto.visit.sdo.VisitDeleteSdo;
import com.travel.vietnamtravel.dto.visit.sdo.VisitSelfSdo;
import com.travel.vietnamtravel.entity.relationship.Visit;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.VisitRepo;
import com.travel.vietnamtravel.service.CommonService;
import com.travel.vietnamtravel.service.PlaceService;
import com.travel.vietnamtravel.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.travel.vietnamtravel.constant.Error.ERROR_NOT_EXIT;
import static com.travel.vietnamtravel.util.DataUtil.copyProperties;
import static com.travel.vietnamtravel.util.DateTimeConvert.dateTimeToString;
import static com.travel.vietnamtravel.util.DateTimeConvert.dateToString;
import static com.travel.vietnamtravel.util.DateTimeUtils.DATE_FORMAT;

@Service
@RequiredArgsConstructor
public class VisitServiceImp implements VisitService {
    private final VisitRepo visitRepo;
    private final PlaceService placeService;
    private final CommonService commonService;

    public VisitCreateSdo create(VisitCreateSdi req) {
        if(req.getTime().isAfter(LocalDateTime.now())){
            throw new CustomException("ERROR: Time after now");
        }
        Visit visit = copyProperties(req, Visit.class);
        Long loginId = commonService.getIdLogin();
        visit.setUserId(loginId);
        visitRepo.save(visit);
        return VisitCreateSdo.of(visit.getId());
    }

    public VisitDeleteSdo delete(VisitDeleteSdi req) {
        Visit visit = visitRepo.findById(req.getId()).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
        visitRepo.delete(visit);
        return VisitDeleteSdo.of(Boolean.TRUE);
    }

    public VisitSelfSdo self(VisitSelfSdi req) {
        Visit visit = visitRepo.findById(req.getId()).orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
        VisitSelfSdo res = copyProperties(visit, VisitSelfSdo.class);
        res.setPlace(placeService.self(PlaceSelfSdi.of(visit.getPlaceId())));
        res.setDate(dateTimeToString(visit.getTime(), DATE_FORMAT));
        return res;
    }

    public List<VisitSelfSdo> listMyVisit(int month, int year) {
        Long loginId = commonService.getIdLogin();
        List<Visit> visits = visitRepo.findByUserIdInMonth(loginId, month, year);

        return visits.stream().map(visit -> {
            VisitSelfSdo res = copyProperties(visit, VisitSelfSdo.class);
            res.setPlace(placeService.self(PlaceSelfSdi.of(visit.getPlaceId())));
            res.setDate(dateTimeToString(visit.getTime(), DATE_FORMAT));
            return res;
        }).collect(Collectors.toList());
    }
}
