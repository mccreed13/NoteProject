package com.goit.finalproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 200, message = "title too big!")
    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private Access access;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) //TODO added nullable=false
    private User user;
}
