package com.goit.finalproject.repository;

import com.goit.finalproject.models.NoteDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<NoteDto, Long> {

    List<NoteDto> findAllByUserId(Long userId);

}