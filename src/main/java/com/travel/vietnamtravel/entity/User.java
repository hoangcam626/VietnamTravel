package com.travel.vietnamtravel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table
public class User extends AbstractAudit{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

//    @Column(name = "created_at")
//    private LocalDateTime createAt;
//
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
}
