package com.goit.finalproject.note.plug;


public class NoteDto{
    public Long getId() {
        return id;
    }

    public NoteDto(Long id, String title, String content, Access access) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.access = access;
    }

    public NoteDto() {
    }
    public static NoteDto of(Long id, String title, String content, com.goit.finalproject.note.plug.Access access) {
        return new NoteDto(id, title, content, access);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    Long id;
        String title;
        String content;
        Access access;
}
