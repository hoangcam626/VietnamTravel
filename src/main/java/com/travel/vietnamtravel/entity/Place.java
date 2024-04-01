package com.travel.vietnamtravel.entity;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "place")
public class Place extends AbstractAudit{

    @Column(name = "name")
    private String name;

    @Column(name = "province_code")
    private String provinceCode;

    @Column(name = "district_code")
    private String districtCode;

    @Column(name = "ward_code")
    private String wardCode;

    @Column(name = "description")
    private String description;

    @Column(name = "typical_image_id")
    private Long imageId;
}
