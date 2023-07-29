package com.goit.finalproject.note;

import com.goit.finalproject.access.Access;

public record NoteDto(
//        Long id,
        Long userId,
//        Long countNotes,
        String title,
        String content,
        Access access
) { }