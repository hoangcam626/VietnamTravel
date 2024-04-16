package com.travel.vietnamtravel.service.imp;

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
import com.travel.vietnamtravel.entity.administrative.*;
import com.travel.vietnamtravel.exception.CustomException;
import com.travel.vietnamtravel.repository.*;
import com.travel.vietnamtravel.service.AdministrativeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.travel.vietnamtravel.constant.Error.*;
import static com.travel.vietnamtravel.util.DataUtil.copyProperties;

@Service
@RequiredArgsConstructor
public class AdministrativeServiceImp implements AdministrativeService {

    private final AdministrativeRegionRepo administrativeRegionRepo;
    private final AdministrativeUnitRepo administrativeUnitRepo;
    private final ProvinceRepo provinceRepo;
    private final DistrictRepo districtRepo;
    private final WardRepo wardRepo;

    public RegionSelfSdo self(RegionSelfSdi req) {
        AdministrativeRegions region = administrativeRegionRepo.findById(req.getId())
                .orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
        return copyProperties(region, RegionSelfSdo.class);
    }

    public UnitSelfSdo self(UnitSelfSdi req) {
        AdministrativeUnit unit = administrativeUnitRepo.findById(req.getId())
                .orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
        return copyProperties(unit, UnitSelfSdo.class);
    }

    public ProvinceSelfSdo self(ProvinceSelfSdi req) {
        Province province = provinceRepo.findByCode(req.getCode());
        ProvinceSelfSdo res = copyProperties(province, ProvinceSelfSdo.class);
        return res;
    }

    public DistrictSelfSdo self(DistrictSelfSdi req) {
        District district = districtRepo.findByCode(req.getCode());
        DistrictSelfSdo res = copyProperties(district, DistrictSelfSdo.class);
        return res;
    }

    public WardSelfSdo self(WardSelfSdi req) {
        Ward ward = wardRepo.findByCode(req.getCode());
        WardSelfSdo res = copyProperties(ward, WardSelfSdo.class);
        return res;
    }

    public RegionShortSelfSdo shortSelf(RegionSelfSdi req) {
        AdministrativeRegions region = administrativeRegionRepo.findById(req.getId())
                .orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
        return RegionShortSelfSdo.of(region.getId(), region.getName());
    }

    public UnitShortSelfSdo shortSelf(UnitSelfSdi req) {
        AdministrativeUnit unit = administrativeUnitRepo.findById(req.getId())
                .orElseThrow(() -> new CustomException(ERROR_NOT_EXIT));
        return UnitShortSelfSdo.of(unit.getId(), unit.getShortName());
    }

    public ProvinceShortSelfSdo shortSelf(ProvinceSelfSdi req) {
        Province province = provinceRepo.findByCode(req.getCode());
        String name = shortSelf(UnitSelfSdi.of(province.getAdministrativeUnitId())).getShortName()
                +" "+province.getName();
        return ProvinceShortSelfSdo.of(province.getCode(), name);
    }

    public DistrictShortSelfSdo shortSelf(DistrictSelfSdi req) {
        District district = districtRepo.findByCode(req.getCode());
        String name = shortSelf(UnitSelfSdi.of(district.getAdministrativeUnitId())).getShortName()
                +" "+district.getName();
        return DistrictShortSelfSdo.of(district.getCode(), name);
    }

    public WardShortSelfSdo shortSelf(WardSelfSdi req) {
        Ward ward = wardRepo.findByCode(req.getCode());
        String name = shortSelf(UnitSelfSdi.of(ward.getAdministrativeUnitId())).getShortName()
                +" "+ward.getName();
        return WardShortSelfSdo.of(ward.getCode(), name);
    }

}
