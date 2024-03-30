package com.travel.vietnamtravel.dto.place.sdo;

import com.travel.vietnamtravel.entity.media.Image;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

public class PlaceSelfSdo {@Column(name = "name")
private String name;

    private String provinceCode;

    private String districtCode;

    private String wardCode;

    private String description;

    private Set<Image> images= new HashSet<>();
}
