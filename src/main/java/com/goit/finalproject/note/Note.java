package com.goit.finalproject.note;

import com.goit.finalproject.access.Access;
import com.goit.finalproject.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id") //TODO added nullable=false (, nullable = false)
    private User user;
}
