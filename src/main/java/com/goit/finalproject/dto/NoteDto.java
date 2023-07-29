package com.goit.finalproject.dto;

import com.goit.finalproject.entity.Access;
import lombok.*;

//TODO changed record on normal class
/*
public record NoteDto(
//        Long id,
        Long userId, //TODO to change user_id on id
//        Long countNotes,
        String title,
        String content,
        Access access
) { }
*/
@ToString
@Getter
@Setter
public class NoteDto {
    Long id;
    String title;
    String content;
    Access access;
}