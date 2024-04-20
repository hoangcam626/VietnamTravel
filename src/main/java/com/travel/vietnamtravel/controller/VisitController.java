package com.travel.vietnamtravel.controller;

import com.travel.vietnamtravel.dto.visit.sdi.VisitCreateSdi;
import com.travel.vietnamtravel.dto.visit.sdi.VisitDeleteSdi;
import com.travel.vietnamtravel.dto.visit.sdo.VisitCreateSdo;
import com.travel.vietnamtravel.dto.visit.sdo.VisitDeleteSdo;
import com.travel.vietnamtravel.dto.visit.sdo.VisitSelfSdo;
import com.travel.vietnamtravel.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visit")
@RequiredArgsConstructor
public class VisitController {
    private final VisitService visitService;

    @PostMapping("/create")
    public ResponseEntity<VisitCreateSdo> create(VisitCreateSdi req) {
        return ResponseEntity.ok(visitService.create(req));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<VisitDeleteSdo> delete(VisitDeleteSdi req) {
        return ResponseEntity.ok(visitService.delete(req));
    }

    @PostMapping("/my-visit")
    public ResponseEntity<List<VisitSelfSdo>> myVisit(int mouth, int year) {
        return ResponseEntity.ok(visitService.listMyVisit(mouth, year));
    }
}
