package com.travel.vietnamtravel.entity;


import com.travel.vietnamtravel.entity.media.Image;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "place")
public class Place extends AbstractAudit{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

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
