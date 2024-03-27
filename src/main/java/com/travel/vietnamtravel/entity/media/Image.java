package com.travel.vietnamtravel.entity.media;

import com.travel.vietnamtravel.entity.AbstractAudit;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "image")
public class Image extends AbstractAudit {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name="filename")
    private String filename;

    @Column(name = "type")
    private String type;

}
