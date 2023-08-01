package com.goit.finalproject.note;

import com.goit.finalproject.access.Access;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class NoteDto {
    Long id;
    String title;
    String content;
    Access access;
    Long user_id;

    public NoteDto(Long id, String title, String content, Access access, Long user_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.access = access;
        this.user_id = user_id;
    }

    public NoteDto(String title, String content, Access access, Long user_id) {
        this.title = title;
        this.content = content;
        this.access = access;
        this.user_id = user_id;
    }

    public NoteDto() {
    }
}