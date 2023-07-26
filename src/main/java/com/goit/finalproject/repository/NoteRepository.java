package com.goit.finalproject.repository;

import com.goit.finalproject.models.NoteDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<NoteDto, Long> {

}