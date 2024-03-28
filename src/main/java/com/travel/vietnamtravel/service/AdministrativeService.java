package com.travel.vietnamtravel.service;

import com.travel.vietnamtravel.dto.administrative.administrativeregions.sdi.RegionSelfSdi;
import com.travel.vietnamtravel.dto.administrative.administrativeregions.sdo.RegionSelfSdo;
import com.travel.vietnamtravel.dto.administrative.administrativeregions.sdo.RegionShortSelfSdo;
import com.travel.vietnamtravel.dto.administrative.administrativeunit.sdi.UnitSelfSdi;
import com.travel.vietnamtravel.dto.administrative.administrativeunit.sdo.UnitSelfSdo;
import com.travel.vietnamtravel.dto.administrative.administrativeunit.sdo.UnitShortSelfSdo;
import com.travel.vietnamtravel.dto.administrative.districts.sdi.DistrictSelfSdi;
import com.travel.vietnamtravel.dto.administrative.districts.sdo.DistrictSelfSdo;
import com.travel.vietnamtravel.dto.administrative.districts.sdo.DistrictShortSelfSdo;
import com.travel.vietnamtravel.dto.administrative.provinces.sdi.ProvinceSelfSdi;
import com.travel.vietnamtravel.dto.administrative.provinces.sdo.ProvinceSelfSdo;
import com.travel.vietnamtravel.dto.administrative.provinces.sdo.ProvinceShortSelfSdo;
import com.travel.vietnamtravel.dto.administrative.wards.sdi.WardSelfSdi;
import com.travel.vietnamtravel.dto.administrative.wards.sdo.WardSelfSdo;
import com.travel.vietnamtravel.dto.administrative.wards.sdo.WardShortSelfSdo;
import com.travel.vietnamtravel.repository.WardRepo;
import org.springframework.stereotype.Service;

@Service
public interface AdministrativeService {
    RegionSelfSdo self(RegionSelfSdi req);
    UnitSelfSdo self(UnitSelfSdi req);
    ProvinceSelfSdo self(ProvinceSelfSdi req);
    DistrictSelfSdo self(DistrictSelfSdi req);
    WardSelfSdo self(WardSelfSdi req);

    RegionShortSelfSdo shortSelf(RegionSelfSdi req);
    UnitShortSelfSdo shortSelf(UnitSelfSdi req);
    ProvinceShortSelfSdo shortSelf(ProvinceSelfSdi req);
    DistrictShortSelfSdo shortSelf(DistrictSelfSdi req);
    WardShortSelfSdo shortSelf(WardSelfSdi req);
}
