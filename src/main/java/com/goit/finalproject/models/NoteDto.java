package com.goit.finalproject.models;

import com.goit.finalproject.enums.Access;

public record NoteDto(
        Long id,
        Long countNotes,
        String title,
        String content,
        Access access
) { }