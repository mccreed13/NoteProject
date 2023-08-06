package com.goit.finalproject.note;

import com.goit.finalproject.user.User;
import com.goit.finalproject.user.UserService;
import com.goit.finalproject.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private final UserService userService;

    public List<NoteDto> listAll() {
        User user = userService.getUserById(userService.getUserId());
        return noteMapper.mapEntityToDto(noteRepository.findAllByUser(user));
    }

    public void add(NoteDto noteDto, Long userId) {
        if (noteDto.getUserId() == null) {
            if (userId != null) {
                noteDto.setUserId(userId);
            } else {
                noteDto.setUserId(userService.getUserId());
            }
        }
        Validator.validateNoteDto(noteDto);
        Note note = noteMapper.mapDtoToEntity(noteDto);
        noteRepository.save(note);
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    public void update(Note note) {
        Validator.validateNote(note);
        noteRepository.save(note);
    }

    public NoteDto getById(Long id) {
        return noteMapper.mapEntityToDto(noteRepository.findById(id).orElseThrow());
    }

    public void updateNoteDto(NoteDto note) {
        update(noteMapper.mapDtoToEntity(note));
    }
}
