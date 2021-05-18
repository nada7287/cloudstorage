package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotesMapper {

    @Select("SELECT * FROM NOTES")
    List<Notes> getAllNotes();


    @Insert("INSERT INTO NOTES ( notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int addNote(Notes notes);

    @Select("SELECT * FROM NOTES WHERE noteid=#{noteId}")
    Notes getNote(Integer noteId);

    @Delete("DELETE FROM NOTES WHERE noteid=#{noteId}")
    void deleteNote( Integer noteId);

    @Update("UPDATE NOTES SET notetitle=#{note.noteTitle} ,notedescription=#{note.noteDescription},userid=#{note.userId} WHERE noteid=#{note.noteId}")
    void updateNote(@Param("note") Notes note);


}

