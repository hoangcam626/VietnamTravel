package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.visit.sdi.VisitCreateSdi;
import com.travel.vietnamtravel.dto.visit.sdi.VisitDeleteSdi;
import com.travel.vietnamtravel.dto.visit.sdi.VisitSelfSdi;
import com.travel.vietnamtravel.dto.visit.sdo.VisitCreateSdo;
import com.travel.vietnamtravel.dto.visit.sdo.VisitDeleteSdo;
import com.travel.vietnamtravel.dto.visit.sdo.VisitSelfSdo;

import java.util.List;
public interface VisitService {
    VisitCreateSdo create(VisitCreateSdi req) ;

    VisitDeleteSdo delete(VisitDeleteSdi req) ;

    VisitSelfSdo self(VisitSelfSdi req) ;

    List<VisitSelfSdo> listMyVisit(int month, int year);
}
