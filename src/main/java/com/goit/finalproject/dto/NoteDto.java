package com.goit.finalproject.dto;

import com.goit.finalproject.entity.Access;

public record NoteDto(
        Long id,
        Long userId,
        Long countNotes,
        String title,
        String content,
        Access access
) { }