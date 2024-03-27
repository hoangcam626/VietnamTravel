package com.travel.vietnamtravel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "user_info")
public class UserInfo extends AbstractAudit{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "birth_of_date")
    private LocalDate birthOfDate;

    @Column(name = "avatar_id")
    private Long avatarId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "level")
    private int level;

//    @Column(name = "created_at")
//    private LocalDateTime createAt;
//
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
}
