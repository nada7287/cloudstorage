package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class NotesService {

    private NotesMapper notesMapper;

    public NotesService(NotesMapper notesMapper) {
        this.notesMapper = notesMapper;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating NotesService bean");
    }

    public void addNotes(Notes note) {

        Notes newNote = note;

        newNote.setNoteId(note.getNoteId());

        newNote.setNoteTitle(note.getNoteTitle());

        newNote.setNoteDescription(note.getNoteDescription());

        newNote.setUserId(note.getUserId());

        notesMapper.addNote(newNote);

    }

    public void deleteNoteById(Integer id){

        notesMapper.deleteNote(id);

    }

    public Notes findNoteById(Integer id){

       return  notesMapper.getNote(id);


    }

    public void updateNote(Notes note){

        notesMapper.updateNote(note);

    }

    public List<Notes> getNotes() {

        return notesMapper.getAllNotes();

    }

}
